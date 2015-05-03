package fr.univ_rouen.hansa.ai;

import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

public interface ComputerStrategy {

    public IMovement[] compute(GameBoard board);

    public IHTPlayer getPlayer();

    public void setPlayer(IHTPlayer player);

    public StrategyType getStrategyType();

}
