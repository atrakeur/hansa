package fr.univ_rouen.hansa.gameboard.routes;

import fr.univ_rouen.hansa.gameboard.pawns.Pawn;

public interface IVillage {

    /**
     * Allow you to know if the village have a pawn or not
     * @return true if the village have no pawn
     */
    public boolean isEmpty();

    /**
     * Add a pawn to the village
     * @pre isEmpty == true;
     * @param pawn the pawn add to the village
     */
    public void pushPawn(Pawn pawn);

    /**
     * Remove and return the pawn inside the village
     * @pre isEmpty == false
     * @return the pawn inside the village
     */
    public Pawn pullPawn();

}
