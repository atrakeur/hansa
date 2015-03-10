package fr.univ_rouen.hansa.gameboard.bonusmarkers;

/**
 * Represent a Bonus Pawn.
 * <p/>
 * A Bonus Pawn can be used at the player's turn, once picked up, except the turn which he picked up
 * the bonus Pawn
 */
public interface IBonusMarker {
    public BonusState getState();

    public void setState(BonusState state);

    public void doAction();

    public void undoAction();
}
