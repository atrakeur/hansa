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
    BonusState getState();

    /**
     * Set the state of the bonusmarker
     * @param state
     * the new state
     */
    void setState(BonusState state);

    /**
     * Do the action of the bonusmarkers
     */
    void doAction();

    /**
     * Undo the action of the bonusmarkers
     */
    void undoAction();

    String getType();

    /**
     * It just call the method IVisitorBonusMarker.visit
     *
     * This method is used for the pattern Visitor
     * It aims at finding the class that implements the IBonusMarker
     * @param visitorBonusMarker the object that wants to inspect the current implementation of the interface
     */
    void accept(IVisitorBonusMarker visitorBonusMarker);
}
