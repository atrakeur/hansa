package fr.univ_rouen.hansa.gameboard.player;

import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;

public interface ComputerStrategy {

    public IMovement[] compute(GameBoard board);

    public IHTPlayer getPlayer();
    public void setPlayer(IHTPlayer player);

}
