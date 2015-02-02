package fr.univ_rouen.hansa.gameboard.player;

import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;

public interface IHTPlayer {
    public IEscritoire getEscritoire();

    public PlayerColor getPlayerColor();

    public int getCount();

    public int getActionNumber();

    public void setActionNumber(int i);
}
