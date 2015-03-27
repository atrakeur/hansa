package fr.univ_rouen.hansa.gameboard.bonusmarkers;

/**
 * Represent a Bonus Pawn.
 * <p/>
 * A Bonus Pawn can be used at the player's turn, once picked up, except the turn which he picked up
 * the bonus Pawn
 */
public interface IBonusMarker {
    /**
     * Get the bonusState
     * @return
     * The bonusState of the bonusmarker
     */
    public BonusState getState();

    /**
     * Set the state of the bonusmarker
     * @param state
     * the new state
     */
    public void setState(BonusState state);

    /**
     * Do the action of the bonusmarkers
     */
    public void doAction();

    /**
     * Undo the action of the bonusmarkers
     */
    public void undoAction();
}
