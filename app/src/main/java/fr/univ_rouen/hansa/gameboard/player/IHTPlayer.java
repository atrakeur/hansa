package fr.univ_rouen.hansa.gameboard.player;

import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;

public interface IHTPlayer extends IScorePlayer {

    /**
     * Access to the escritoire and all the informations of the player
     *
     * @return the escritoire
     */
    public IEscritoire getEscritoire();

    /**
     * Return the color of the player, equivalent of his ID
     *
     * @return PlayerColor, an enum who represent the color
     */
    public PlayerColor getPlayerColor();

    /**
     * Get the real actionNumber of the player
     *
     * @return The new number action
     */
    public int getActionNumber();

    /**
     * Set the real actionNumber (Use with the bonusmarkers)
     *
     * @param i The value to add to the actionNumber
     */
    public void setActionNumber(int i);

    /**
     * Reinit actions count for a new turn
     */
    public void newTurn();
}
