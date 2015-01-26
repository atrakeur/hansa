package fr.univ_rouen.hansa.gameboard.cities;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.pawns.Pawn;

public interface IKontor<E extends Pawn> {

    boolean isEmpty();
    public void pullPawn(E pawn);
    public E pushPawn();
    Privillegium getPrivillegium();
    
}
