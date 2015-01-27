package fr.univ_rouen.hansa.gameboard.routes;

import fr.univ_rouen.hansa.gameboard.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class Village implements IVillage {

    private final int id;
    private Pawn pawn;

    public Village(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean isEmpty() {
        return pawn == null;
    }

    @Override
    public void setPawn(Pawn pawn) {
        if (!isEmpty()) {
            //TODO change that!
            throw new RuntimeException("Village allready taken");
        }

        this.pawn = pawn;
    }

    @Override
    public Pawn pushPawn() {
        Pawn pawn = this.pawn;
        this.pawn = null;
        return pawn;
    }
}
