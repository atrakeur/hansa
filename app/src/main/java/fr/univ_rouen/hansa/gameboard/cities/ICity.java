package fr.univ_rouen.hansa.gameboard.cities;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.pawns.Pawn;
import fr.univ_rouen.hansa.view.IPosition;

public interface ICity {

    /**
     * Return the kontor at the position i int the city
     *
     * @param i kontor position
     * @return the kontor selected
     */
    public IKontor getKontor(int i);

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
