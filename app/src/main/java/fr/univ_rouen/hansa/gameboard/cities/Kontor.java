package fr.univ_rouen.hansa.gameboard.cities;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.pawns.Pawn;

public class Kontor<E extends Pawn> implements IKontor<E> {

    private final boolean victoryPoint;
    private E pawn;
    private Privillegium privillegium;

    public Kontor(boolean victoryPoint, Privillegium privillegium) {
        this.victoryPoint = victoryPoint;
        this.privillegium = privillegium;

        this.pawn = null;
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

    public void pullPawn(E pawn) {
        if (pawn == null) {
            throw new IllegalArgumentException();
        }

        if (!this.isEmpty()) {
            throw new IllegalStateException();
        }

        this.pawn = pawn;

        if (victoryPoint) {
            TurnManager.getInstance().getCurrentPlayer().increaseScore();
        }
    }

}
