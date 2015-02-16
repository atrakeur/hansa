package fr.univ_rouen.hansa.gameboard.cities;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.view.IPosition;
import fr.univ_rouen.hansa.view.display.IDrawable;
import fr.univ_rouen.hansa.view.interactions.IClickable;

public interface ICity extends IDrawable, IClickable {

    /**
     * Set a route near the city
     *
     * @param route IRoute near the city
     */
    public void setRoute(IRoute route);

    /**
     * Return all routes near the city
     *
     * @return a set of IRoute
     */
    public List<IRoute> getRoutes();

    /**
     * Return the kontor at the position i int the city
     *
     * @param i kontor position
     * @return the kontor selected
     */
    public IKontor getKontor(int i);

    /**
     * Return the kontor who can be keep
     *
     * @return the kontor selected
     */
    public IKontor<? extends Pawn> getNextKontor();

    /**
     * Getter for the position of the city
     *
     * @return an object who represent the position of the city in percent of the map
     */
    public IPosition getPosition();

    /**
     * Getter for the kontors of the city
     *
     * @return the list of kontors of the city
     */
    public List<IKontor<? extends Pawn>> getKontors();

    /**
     * Getter for the power of the city
     *
     * @return the power of the city
     */
    public Power getPower();

    /**
     * Allow you to know if all the kontors of the city are taken
     *
     * @return true if the city is completed, false else;
     */
    public boolean isCompletedCity();

}
