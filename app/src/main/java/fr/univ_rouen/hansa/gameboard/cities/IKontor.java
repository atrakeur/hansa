package fr.univ_rouen.hansa.gameboard.cities;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;

public interface IKontor<E extends Pawn> {

    boolean isEmpty();
    public void setPawn(E pawn);
    public void pushPawn(E pawn);
    Privillegium getPrivillegium();

}
