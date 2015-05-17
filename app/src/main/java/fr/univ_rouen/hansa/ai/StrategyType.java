package fr.univ_rouen.hansa.ai;

import fr.univ_rouen.hansa.ai.strategies.BonusStrategy;
import fr.univ_rouen.hansa.ai.strategies.RandomStrategy;

public enum StrategyType {

    randomStrategy(RandomStrategy.class),
    bonusStrategy(BonusStrategy.class);

    private final Class<? extends ComputerStrategy> strategy;

    private StrategyType(Class<? extends ComputerStrategy> strategy) {
        this.strategy = strategy;
    }

    public ComputerStrategy getInstance() {
        try {
            return strategy.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
