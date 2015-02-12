package fr.univ_rouen.hansa.actions.movement;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class ReplaceMovedPawn implements IMovement {
    private final IHTPlayer player;
    private final IVillage source;
    private final IVillage destination;
    private final Class<? extends Pawn> pawnType;

    private boolean actionDone;

    public ReplaceMovedPawn(IHTPlayer player, IVillage source, IVillage destination, Class<? extends Pawn> pawnType) {
        this.player = player;
        this.source = source;
        this.destination = destination;
        this.pawnType = pawnType;

        actionDone = false;
    }

    @Override
    public boolean isDone() {
        return actionDone;
    }

    @Override
    public Actions getActionDone() {
        return Actions.replaceMovedPawn;
    }

    @Override
    public void doMovement() {
        if (source.isEmpty() && !destination.isEmpty()) {
            throw new IllegalStateException();
        }

        if (!source.getOwner().equals(player)) {
            throw new IllegalStateException("Wrong player");
        }

        destination.pushPawn(source.pullPawn());

        actionDone = true;
    }

    @Override
    public void doRollback() {
        if (!source.isEmpty() && destination.isEmpty()) {
            throw new IllegalStateException();
        }

        source.pushPawn(destination.pullPawn());

        actionDone = false;
    }
}
