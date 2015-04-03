package fr.univ_rouen.hansa.actions.movement;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;

/**
 * Represents a movement that is done by a player on the GameBoard
 * The player can be anything (real player or AI)
 * Any movement can be undone/redone and track it's state internally.
 */
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

    /**
     * Do this movement force a replace move later?
     * @return
     */
    public Pawn getPawnToReplace();

    /**
     * How many movements of this type can be merged inside one action
     * @return
     */
    public int getMergeableMove();
}
