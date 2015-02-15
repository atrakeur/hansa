package fr.univ_rouen.hansa.actions.movement;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.exceptions.NoPlaceException;
import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class KeepKontor implements IMovement {
    private final IHTPlayer player;
    private final IVillage village;
    private final IKontor<Pawn> kontor;
    private final List<Pawn> pawns;

    private boolean actionDone;

    public KeepKontor(IHTPlayer player, ICity city, IVillage village) {
        this.player = player;
        this.village = village;

        this.actionDone = false;

        if (city.isCompletedCity()) {
            throw new NoPlaceException("City is complete");
        }

        if (village.isEmpty()) {
            throw new GameException("Village has empty");
        }

        if (city.isCompletedCity()) {
            throw new NoPlaceException("City is complete");
        }

        this.kontor = city.getNextKontor();

        pawns = Lists.newArrayList();
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

        kontor.pushPawn(pawn);

        for (IVillage otherVillage : village.getRoute().getVillages()) {
            if (!otherVillage.equals(village)) {
                pawns.add(otherVillage.pullPawn());
            }
        }

        player.getEscritoire().getStock().addPawns(pawns);

        actionDone = true;
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

        actionDone = false;
    }
}
