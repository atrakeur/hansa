package fr.univ_rouen.hansa.GameBoard.Cities;

import fr.univ_rouen.hansa.GameBoard.Pawns.Pawn;
import fr.univ_rouen.hansa.GameBoard.Privillegium;

public interface IKontor<E extends Pawn> {

    boolean isEmpty();
    public void setPawn(E pawn);
    public void pushPawn(E pawn);
    Privillegium getPrivillegium();

}
