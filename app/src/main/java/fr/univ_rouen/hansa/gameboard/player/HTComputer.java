package fr.univ_rouen.hansa.gameboard.player;

import fr.univ_rouen.hansa.ai.ComputerStrategy;

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
