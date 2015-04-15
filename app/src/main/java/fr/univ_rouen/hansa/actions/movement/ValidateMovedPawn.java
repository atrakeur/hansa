package fr.univ_rouen.hansa.actions.movement;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;

public class ValidateMovedPawn implements IMovement {

    private boolean actionDone;

    @Override
    public boolean isDone() {
        return actionDone;
    }

    @Override
    public Actions getActionDone() {
        return Actions.validateMovedPawn;
    }

    @Override
    public void doMovement() {
        actionDone = true;
    }

    @Override
    public void doRollback() {
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
