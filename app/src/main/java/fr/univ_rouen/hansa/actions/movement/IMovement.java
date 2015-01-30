package fr.univ_rouen.hansa.actions.movement;

import fr.univ_rouen.hansa.actions.Actions;

public interface IMovement {

    /**
     * Allow to know if the movement is done in the game or not
     *
     * @return true if the movement is done
     */
    public boolean isDone();

    /**
     * return the action type done by the movement
     *
     * @return Actions done represent by Actions enum
     */
    public Actions getActionDone();

    /**
     * Do the movement in the gameboard model
     *
     * @pre isDone == false
     */
    public void doMovement();

    /**
     * Undo the movement in the gameboard model
     *
     * @pre isDone == true
     */
    public void doRollback();
}
