package fr.univ_rouen.hansa.actions.movement;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class LiberSophia implements IMovement {
    private final IVillage source;
    private final IVillage destination;

    private boolean actionDone;

    public LiberSophia(IVillage source, IVillage destination) {
        this.source = source;
        this.destination = destination;

        actionDone = false;
    }

    @Override
    public boolean isDone() {
        return actionDone;
    }

    @Override
    public Actions getActionDone() {
        return Actions.liberSophia;
    }

    @Override
    public void doMovement() {
        if (!destination.isEmpty()) {
            throw new GameException("Village destination is not empty");
        }

        if (source.isEmpty()) {
            throw new GameException("Village source is empty");
        }

        destination.pushPawn(source.pullPawn());

        actionDone = true;
    }

    @Override
    public void doRollback() {
        if (destination.isEmpty()) {
            throw new GameException("Village destination is empty");
        }

        if (!source.isEmpty()) {
            throw new GameException("Village source is not empty");
        }

        source.pushPawn(destination.pullPawn());

        actionDone = false;
    }
}
