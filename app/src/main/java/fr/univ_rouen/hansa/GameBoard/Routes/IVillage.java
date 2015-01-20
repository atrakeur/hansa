package fr.univ_rouen.hansa.GameBoard.Routes;

import fr.univ_rouen.hansa.GameBoard.Pawns.Pawn;

public interface IVillage {

    public boolean isEmpty();
    public void setPawn(Pawn pawn);
    public Pawn pushPawn();

}
