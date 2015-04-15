package fr.univ_rouen.hansa.gameboard.cities;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
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
    void setRoute(IRoute route);

    /**
     * Return all routes near the city
     *
     * @return a set of IRoute
     */
    List<IRoute> getRoutes();

    /**
     * Return the kontor at the position i int the city
     *
     * @param i kontor position
     * @return the kontor selected
     */
    IKontor getKontor(int i);

    /**
     * Return the kontor who can be keep
     *
     * @return the kontor selected
     */
    IKontor<? extends Pawn> getNextKontor();

    /**
     * Getter for the position of the city
     *
     * @return an object who represent the position of the city in percent of the map
     */
    IPosition getPosition();

    /**
     * Getter for the kontors of the city
     *
     * @return the list of kontors of the city
     */
    List<IKontor<? extends Pawn>> getKontors();

    /**
     * Additional Kontors created by Bonus BonusKontor.
     *
     * @return Additional Kontors List
     */
    List<IKontor<? extends Pawn>> getAdditionalKontors();

    /**
     * push an additional kontor
     *
     * @param kontor : kontor != null && !kontor.isEmpty()
     */
    void pushAdditionalKontors(IKontor<? extends Pawn> kontor);

    /**
     * Getter for the power of the city
     *
     * @return the power of the city
     */
    Power getPower();

    /**
     * Allow you to know if all the kontors of the city are taken
     *
     * @return true if the city is completed, false else;
     */
    boolean isCompletedCity();

    /**
     * The city's owner is the player with the most Kontor in this city.
     * In case of a tie, the one that occupies the house higher value (the one furthest
     * to the right) controls the city.
     *
     * @return the city's owner
     */
    IHTPlayer getOwner();
}
