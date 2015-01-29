package fr.univ_rouen.hansa.gameboard.routes;

import fr.univ_rouen.hansa.gameboard.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class Village implements IVillage {

    private Pawn pawn;

    @Override
    public boolean isEmpty() {
        return pawn == null;
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
