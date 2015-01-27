package fr.univ_rouen.hansa.actions;

public class KeepRoute implements IMovement {
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
