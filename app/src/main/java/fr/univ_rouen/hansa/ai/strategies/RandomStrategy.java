package fr.univ_rouen.hansa.ai.strategies;

import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.actions.movement.MovePawnRtoS;
import fr.univ_rouen.hansa.ai.StrategyType;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class RandomStrategy extends BaseStrategy {

    public RandomStrategy() {
        super(StrategyType.randomStrategy);
    }

    @Override
    public IMovement[] compute(GameBoard board) {
        return takePower(Power.Actiones);
    }
}
