package fr.univ_rouen.hansa.actions.movement;

import fr.univ_rouen.hansa.actions.Actions;

public class MovePawnStoR implements IMovement {
    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Actions getActionDone() {
        return null;
    }

    @Override
    public void doMovement() {

    }

    @Override
    public void doRollback() {

    }
}
