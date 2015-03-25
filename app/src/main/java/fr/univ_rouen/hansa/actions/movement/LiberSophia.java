package fr.univ_rouen.hansa.actions.movement;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.exceptions.NotAvailableActionException;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class LiberSophia implements IMovement {
    private final IHTPlayer player;
    private final IVillage source;
    private final IVillage destination;

    private boolean actionDone;

    public LiberSophia(IHTPlayer player, IVillage source, IVillage destination) {
        this.player = player;
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
            throw new NotAvailableActionException("Village destination is not empty");
        }

        if (source.isEmpty()) {
            throw new GameException("Village source is empty");
        }

        if (!source.getOwner().equals(player)) {
            throw new GameException("Wrong player in the village");
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

    @Override
    public int getPawnReplaceMove() {
        return 0;
    }

    @Override
    public int getMergeableMove() {
        return this.player.getEscritoire().liberSophiaLevel();
    }
}
