package fr.univ_rouen.hansa.GameBoard.Player;

import fr.univ_rouen.hansa.GameBoard.Player.Escritoire.IEscritoire;

public interface IHTPlayer {
    public IEscritoire getEscritoire();
    public PlayerColor getPlayerColor();
    public int getCount();
}
