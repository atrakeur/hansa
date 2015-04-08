package fr.univ_rouen.hansa.gameboard.routes;

import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.view.IPosition;
import fr.univ_rouen.hansa.view.interactions.IClickable;

public interface IVillage extends IClickable {

    /**
     * Allow to know the route of the village
     *
     * @return the route of the village
     */
    public IRoute getRoute();

    /**
     * Set the route attach to the village
     *
     * @param route the route of the village
     */
    public void setRoute(IRoute route);

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
     * For know the type of pawn in the village
     *
     * @return the class who represent the type of pawn
     */
    public Class<? extends Pawn> getPawnType();

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
