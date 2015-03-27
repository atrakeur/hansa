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
     * Return the number of actions left for the current turn
     *
     * @return the number of actions left
     */
    public int getActionNumber();

    /**
     * Add action to the current turn
     *
     * @param i the number of action
     */
    public void setActionNumber(int i);

    /**
     * Reinit actions count for a new turn
     */
    public void newTurn();
}
