package fr.univ_rouen.hansa.gameboard.routes;

import fr.univ_rouen.hansa.gameboard.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.view.IPosition;

public class Village implements IVillage {

    private final IPosition position;
    private Pawn pawn;

    public Village(IPosition position) {
        this.position = position;
    }

    @Override
    public boolean isEmpty() {
        return pawn == null;
    }

    @Override
    public IPosition getPosition() {
        return position;
    }

    @Override
    public IHTPlayer getOwner() {
        if (pawn != null) {
            return pawn.getPlayer();
        }

        return null;
    }

    @Override
    public void pushPawn(Pawn pawn) {
        if (!isEmpty()) {
            throw new IllegalStateException("Village already taken");
        }

        this.pawn = pawn;
    }

    @Override
    public Pawn pullPawn() {
        if (isEmpty()) {
            throw new IllegalStateException("Village has no pawn");
        }

        Pawn pawn = this.pawn;
        this.pawn = null;

        return pawn;
    }
}
