package fr.univ_rouen.hansa.gameboard.routes;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.view.IPosition;
import fr.univ_rouen.hansa.view.display.IDrawable;

public interface IRoute extends IDrawable {

    /**
     * getter on the position of the tavern in the road
     *
     * @return an object who represent the position of the tavern in the map
     */
    IPosition getTavernPosition();

    /**
     * get a village to a specifique position
     *
     * @param i the position
     * @return the village to the position i in the list
     */
    IVillage getVillage(int i);

    /**
     * get all the village contains by the road
     *
     * @return all the village in a List
     */
    List<IVillage> getVillages();

    /**
     * get the two city concomitant of the road
     *
     * @return an array with the two cities
     */
    ICity[] getCities();

    /**
     * Allow to know if all the village of a route is empty
     *
     * @return true if the route is empty
     */
    boolean isEmpty();

    /**
     * Allow to know if all the village of a route is taken by a player
     *
     * @return true if the route is complete
     */
    boolean isTradeRoute();

    /**
     * Allow to know if all the village of a route is taken by a specific player
     *
     * @param player the player to check
     * @return true if the route is complete
     */
    boolean isTradeRoute(IHTPlayer player);

    /**
     * return the bonus marker in the tavern of the road
     *
     * @return the bonus marker, null if the road don't have any bonus marker
     */
    IBonusMarker getBonusMarker();


    /**
     * return the bonus marker in the tavern of the road and remove it from the road
     *
     * @return the bonus marker, null if the road don't have any bonus marker
     */
    public IBonusMarker popBonusMarker();

    /**
     * push bonusMarker on the Road
     *
     * @param bonusMarker
     * @throws java.lang.IllegalStateException if this.getBonusMarker != null
     */
    public void pushBonusMarker(IBonusMarker bonusMarker);

    /**
     * Allow to know if the city is concomitant to the road
     *
     * @param city the ICity you want to check
     * @return true if the city is concomitant to the road, false else
     */
    boolean isYourCity(ICity city);

}
