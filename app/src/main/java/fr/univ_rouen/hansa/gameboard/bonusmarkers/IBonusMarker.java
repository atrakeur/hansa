package fr.univ_rouen.hansa.gameboard.bonusmarkers;

/**
 * Represent a Bonus Pawn.
 * <p/>
 * A Bonus Pawn can be used at the player's turn, once picked up, except the turn which he picked up
 * the bonus Pawn
 */
public interface IBonusMarker {
    /**
     * Return the bonusState
     * @return
     * The bonusstate
     */
    public BonusState getState();

    /**
     * Set the state of the bonusmarker
     * @param state
     * New state of the bonus marker
     */
    public void setState(BonusState state);

    /**
     * Do the action of the bonusmarker
     */
    public void doAction();

    /**
     * Undo the action of the bonusmarker
     */
    public void undoAction();
}
