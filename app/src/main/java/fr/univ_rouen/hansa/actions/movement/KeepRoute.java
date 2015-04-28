package fr.univ_rouen.hansa.actions.movement;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.exceptions.EndOfGameException;
import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class KeepRoute implements IMovement {
    private final IHTPlayer player;
    private final IRoute route;
    private final List<Pawn> pawns;

    private boolean actionDone;

    public KeepRoute(IHTPlayer player, IRoute route) {
        this.player = player;
        this.route = route;

        this.pawns = Lists.newArrayList();

        actionDone = false;
    }

    @Override
    public boolean isDone() {
        return actionDone;
    }

    @Override
    public Actions getActionDone() {
        return Actions.keepRoute;
    }

    @Override
    public void doMovement() {
        if (pawns.size() > 0 || actionDone) {
            throw new IllegalStateException();
        }

        if (!route.isTradeRoute(player)) {
            throw new GameException("Action not available, the root didn't own by the player");
        }

        for(ICity city : route.getCities()){
            if (city.getOwner() != null) {
                city.getOwner().increaseScore();
            }
        }
        for (IVillage village : route.getVillages()) {
            pawns.add(village.pullPawn());
        }

        player.getEscritoire().getStock().addPawns(pawns);


        IBonusMarker bonusMarker = route.popBonusMarker();
        if(bonusMarker != null){
            player.getEscritoire().getBonusMarker().add(bonusMarker);
            player.getEscritoire().getTinPlateContent().add(GameBoardFactory.getGameBoard().drawBonusMarker());
        }

        actionDone = true;

        for (ICity city : route.getCities()) {
            if(city.getOwner() != null) {
                if(city.getOwner().getScore() >= 20){
                    throw  new EndOfGameException();
                }
            }
        }
    }

    @Override
    public void doRollback() {
        if (!actionDone) {
            throw new IllegalStateException();
        }

        Iterator<Pawn> pawnIterator = pawns.iterator();

        player.getEscritoire().getStock().removePawns(pawns);

        for (IVillage village : route.getVillages()) {
            village.pushPawn(pawnIterator.next());
        }

        pawns.clear();
        for(ICity city : route.getCities()){
            if (city.getOwner() != null) {
                city.getOwner().decreaseScore();
            }
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
