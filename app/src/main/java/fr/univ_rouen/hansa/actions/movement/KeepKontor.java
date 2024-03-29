package fr.univ_rouen.hansa.actions.movement;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.exceptions.EndOfGameException;
import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.exceptions.NoPlaceException;
import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusState;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class KeepKontor implements IMovement {
    private final IHTPlayer player;
    private final IVillage village;
    private final IKontor kontor;
    private final List<Pawn> pawns;
    private final ICity city;

    private IBonusMarker bonusMarker;
    private IBonusMarker tinPlate;
    private boolean actionDone;

    public KeepKontor(IHTPlayer player, ICity city, IVillage village) {
        this.player = player;
        this.village = village;

        if (city.isCompletedCity()) {
            throw new NoPlaceException("City is complete");
        }

        if (village.isEmpty()) {
            throw new GameException("Village has empty");
        }

        this.kontor = city.getNextKontor();

        pawns = Lists.newArrayList();
        this.city = city;
    }

    @Override
    public boolean isDone() {
        return actionDone;
    }

    @Override
    public Actions getActionDone() {
        return Actions.keepKontor;
    }

    @Override
    public void doMovement() {
        if (pawns.size() > 0 || actionDone) {
            throw new IllegalStateException();
        }

        if (!player.equals(village.getOwner())) {
            throw new GameException("Owner of the village is not the player of the action");
        }

        Privillegium playerPrivi = player.getEscritoire().privilegiumLevel();
        Pawn pawn = village.pullPawn();

        if (!playerPrivi.isBetterThan(kontor.getPrivillegium())) {
            throw new GameException("Player have no enought privilegium for keep this kontor");
        }

        if (!kontor.getPawnClass().equals(pawn.getClass())) {
            village.pushPawn(pawn);

            throw new GameException("Wrong pawn type for take the kontor");
        }


        if (kontor.hasVictoryPoint()) {
            player.increaseScore();
        }
        for (ICity city : village.getRoute().getCities()) {
            if (city.getOwner() != null) {
                city.getOwner().increaseScore();
            }
        }
        kontor.pushPawn(pawn);

        for (IVillage otherVillage : village.getRoute().getVillages()) {
            if (!otherVillage.equals(village)) {
                pawns.add(otherVillage.pullPawn());
            }
        }
        player.getEscritoire().getStock().addPawns(pawns);

        actionDone = true;

        for (ICity city : village.getRoute().getCities()) {
            if (city.getOwner() != null) {
                if (city.getOwner().getScore() >= 20) {
                    throw new EndOfGameException();
                }
            }
        }

        bonusMarker = village.getRoute().popBonusMarker();
        if (bonusMarker != null) {
            bonusMarker.setState(BonusState.onHand);
            player.getEscritoire().addBonusMarker(bonusMarker);

            tinPlate = GameBoardFactory.getGameBoard().drawBonusMarker();
            tinPlate.setState(BonusState.inPlate);
            player.getEscritoire().addTinPlate(tinPlate);
        }

        if (city.isCompletedCity()) {
            GameBoardFactory.getGameBoard().increaseCityCompleted();
        }

    }

    @Override
    public void doRollback() {
        if (!actionDone || !village.isEmpty() || kontor.isEmpty()) {
            throw new IllegalStateException();
        }

        village.pushPawn(kontor.popPawn());

        player.getEscritoire().getStock().removePawns(pawns);

        Iterator<Pawn> pawnIterator = pawns.iterator();

        for (IVillage otherVillage : village.getRoute().getVillages()) {
            if (!otherVillage.equals(village)) {
                otherVillage.pushPawn(pawnIterator.next());
            }
        }
        if (kontor.hasVictoryPoint()) {
            player.decreaseScore();
        }

        pawns.clear();
        for (ICity city : village.getRoute().getCities()) {
            if (city.getOwner() != null) {
                city.getOwner().decreaseScore();
            }
        }


        if(bonusMarker != null){
            bonusMarker.setState(BonusState.onBoard);
            player.getEscritoire().removeBonusMarker(bonusMarker);
            village.getRoute().pushBonusMarker(bonusMarker);

            player.getEscritoire().removeTinPlate(tinPlate);
            tinPlate.setState(BonusState.unused);
            GameBoardFactory.getGameBoard().putBackBonusMarker(tinPlate);
        }

        actionDone = false;
    }

    @Override
    public Pawn getPawnToReplace() {
        return null;
    }

    @Override
    public int getMergeableMove() {
        return 0;
    }
}
