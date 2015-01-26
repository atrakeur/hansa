package fr.univ_rouen.hansa.gameboard.cities;


import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.pawns.Pawn;

public class Kontor<E extends Pawn> implements IKontor<E> {

    private E pawn;
    private Privillegium privillegium;

    public Kontor(Privillegium p) {
        pawn = null;
        privillegium = p;
    }

    public boolean isEmpty() {
        return pawn == null;
    }

    public E pushPawn() {
        E p = pawn;
        pawn = null;
        return p;
    }

    public Privillegium getPrivillegium() {
        return privillegium;
    }


    public void pullPawn(E p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (!this.isEmpty()){
            throw new IllegalStateException();
        }
        pawn = p;
    }

}
