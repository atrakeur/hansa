package fr.univ_rouen.hansa.gameboard.player;

import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.activity.GameActivity;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;

public class HTComputer extends HTPlayer {

    private volatile Thread runThread;
    private volatile boolean run;

    private ComputerStrategy strategy;

    public HTComputer(PlayerColor color, int startingPlace, ComputerStrategy strategy) {
        super(color, startingPlace);

        this.strategy = strategy;
        this.strategy.setPlayer(this);
    }

    public ComputerStrategy getStrategy() {
        return strategy;
    }
}
