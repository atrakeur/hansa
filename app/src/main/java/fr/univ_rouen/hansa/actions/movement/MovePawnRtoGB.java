package fr.univ_rouen.hansa.actions.movement;

import com.google.common.collect.Lists;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.exceptions.NoPlaceException;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class MovePawnRtoGB implements IMovement {
    private final IHTPlayer player;
    private final IVillage village;
    private final Class<? extends Pawn> type;

    private boolean actionDone;

    public MovePawnRtoGB(IHTPlayer player, IVillage village, Class<? extends Pawn> type) {
        this.player = player;
        this.village = village;
        this.type = type;

        actionDone = false;
    }

    @Override
    public boolean isDone() {
        return actionDone;
    }

    @Override
    public Actions getActionDone() {
        return Actions.movePawnRtoGB;
    }

    @Override
    public void doMovement() {
        if (actionDone) {
            throw new IllegalStateException();
        }

        if (!village.isEmpty()) {
            throw new NoPlaceException();
        }

        Pawn pawn;

        if (type.equals(Trader.class)) {
            pawn = player.getEscritoire().popFromSupply(0, 1).get(0);
        } else {
            pawn = player.getEscritoire().popFromSupply(1, 0).get(0);
        }

        village.pushPawn(pawn);

        actionDone = true;
    }

    @Override
    public void doRollback() {
        if (!actionDone) {
            throw new IllegalStateException();
        }

        if (village.isEmpty()) {
            throw new NoPlaceException();
        }

        player.getEscritoire().addToSupply(Lists.newArrayList(village.pullPawn()));

        actionDone = false;
    }
}
