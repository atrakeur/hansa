package fr.univ_rouen.hansa.gameboard.player.strategies;

import fr.univ_rouen.hansa.gameboard.player.ComputerStrategy;

public enum Strategy {
    RandomStrategy(new RandomStrategy());

    private final ComputerStrategy computerStrategy;

    private Strategy(ComputerStrategy computerStrategy) {
        this.computerStrategy = computerStrategy;
    }

    public ComputerStrategy getComputerStrategy() {
        return computerStrategy;
    }
}
