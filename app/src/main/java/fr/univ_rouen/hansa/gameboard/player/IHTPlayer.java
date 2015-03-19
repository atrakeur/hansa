package fr.univ_rouen.hansa.gameboard.player;

import java.io.Serializable;

import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;

public interface IHTPlayer extends IScorePlayer, Serializable {

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
}
