package fr.univ_rouen.hansa.gameboard.routes;

import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.view.IPosition;

public interface IVillage {

    /**
     * Allow you to know if the village have a pawn or not
     *
     * @return true if the village have no pawn
     */
    public boolean isEmpty();

    /**
     * Getter for the position of the village
     *
     * @return an object who represent the position in the map
     */
    public IPosition getPosition();

    /**
     * For get the owner of the village
     *
     * @return the owner of the village, null if the village is empty
     */
    public IHTPlayer getOwner();

    /**
     * Add a pawn to the village
     *
     * @param pawn the pawn add to the village
     * @pre isEmpty == true;
     */
    public void pushPawn(Pawn pawn);

    /**
     * Remove and return the pawn inside the village
     *
     * @return the pawn inside the village
     * @pre isEmpty == false
     */
    public Pawn pullPawn();

}
