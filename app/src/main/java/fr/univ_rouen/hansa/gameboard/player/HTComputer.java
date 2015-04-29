package fr.univ_rouen.hansa.gameboard.player;

import fr.univ_rouen.hansa.ai.ComputerStrategy;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;

public class HTComputer extends HTPlayer {

    private ComputerStrategy strategy;

    /**
     * Init a AI from a safeguard
     *
     * @param color of the AI
     * @param escritoire of the AI
     * @param action number of action playable of the AI
     * @param strategy of the AI
     */
    public HTComputer(PlayerColor color, IEscritoire escritoire, int action, ComputerStrategy strategy) {
        super(color, escritoire, action);

        this.strategy = strategy;
        this.strategy.setPlayer(this);
    }

    /**
     * Init a AI for a new game
     *
     * @param color of the AI
     * @param startingPlace of the AI
     * @param strategy of the AI
     */
    public HTComputer(PlayerColor color, int startingPlace, ComputerStrategy strategy) {
        super(color, startingPlace);

        this.strategy = strategy;
        this.strategy.setPlayer(this);
    }

    public ComputerStrategy getStrategy() {
        return strategy;
    }

}
