package fr.univ_rouen.hansa.gameboard.cities;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;

public class Kontor<E extends Pawn> implements IKontor<E> {

    private final Class<E> pawnClass;
    private final boolean victoryPoint;
    private E pawn;
    private Privillegium privillegium;

    public Kontor(Class<E> pawnClass, boolean victoryPoint, Privillegium privillegium) {
        this.pawnClass = pawnClass;
        this.victoryPoint = victoryPoint;
        this.privillegium = privillegium;

        this.pawn = null;
    }

    public Class<E> getPawnClass() {
        return this.pawnClass;
    }

    public boolean isEmpty() {
        return pawn == null;
    }

    public E popPawn() {
        E p = pawn;
        pawn = null;
        return p;
    }

    public Privillegium getPrivillegium() {
        return privillegium;
    }

    public void pushPawn(E pawn) {
        if (pawn == null) {
            throw new IllegalArgumentException();
        }

        if (!this.isEmpty()) {
            throw new IllegalStateException();
        }

        this.pawn = pawn;
    }

    public IHTPlayer getOwner() {
        if (isEmpty()) {
            return null;
        }

        return this.pawn.getPlayer();
    }

    public boolean hasVictoryPoint(){
        return victoryPoint;
    }

}
