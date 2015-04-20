package fr.univ_rouen.hansa.gameboard.player;

import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;

public interface IHTPlayer extends IScorePlayer {

    /**
     * Access to the escritoire and all the informations of the player
     *
     * @return the escritoire
     */
    IEscritoire getEscritoire();

    /**
     * Return the color of the player, equivalent of his ID
     *
     * @return PlayerColor, an enum who represent the color
     */
    PlayerColor getPlayerColor();

    /**
     * Get the real actionNumber of the player
     *
     * @return The new number action
     */
    int getActionNumber();

    /**
     * Set the real actionNumber (Use with the bonusmarkers)
     *
     * @param i The value to add to the actionNumber
     */
    void setActionNumber(int i);

    /**
     * Reinit actions count for a new turn
     */
    void newTurn();
}
