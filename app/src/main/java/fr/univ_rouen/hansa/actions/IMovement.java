package fr.univ_rouen.hansa.actions;

public interface IMovement {

    /**
     * Allow to know if the movement is done in the game or not
     *
     * @return true if the movement is done
     */
    public boolean isDone();

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
