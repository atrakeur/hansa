package fr.univ_rouen.hansa.gameboard.player.pawns;

import java.io.Serializable;

import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

public interface Pawn extends Serializable {
    public IHTPlayer getPlayer();
    public void setPlayer (IHTPlayer player);
}
