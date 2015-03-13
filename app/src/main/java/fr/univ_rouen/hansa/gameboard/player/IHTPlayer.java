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
     * Return the number of action for the player
     * @return
     * The number of action that can be play by the player
     */
    public int getActionNumber();

    /**
     * Set the number of action
     * @param i
     * Number of action to add to the previous actionnumber
     */
    public void setActionNumber(int i);
}
