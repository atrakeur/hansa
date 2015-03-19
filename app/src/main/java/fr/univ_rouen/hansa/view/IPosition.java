package fr.univ_rouen.hansa.view;

import java.io.Serializable;

public interface IPosition extends Serializable {

    /**
     * Get the position in percent of X with the corner on the top left
     *
     * @return a number between 0 and 1 who represent the position
     */
    public float getX();

    /**
     * Get the position in percent of Y with the corner on the top left
     *
     * @return a number between 0 and 1 who represent the position
     */
    public float getY();

    /**
     * Get the position in percent of X and Y with the corner on the top left
     *
     * @return an array who contain X and Y
     */
    public float[] getPosition();

}
