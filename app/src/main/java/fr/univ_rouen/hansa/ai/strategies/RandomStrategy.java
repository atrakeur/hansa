package fr.univ_rouen.hansa.ai.strategies;

import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.actions.movement.MovePawnRtoGB;
import fr.univ_rouen.hansa.actions.movement.MovePawnRtoS;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.ai.ComputerStrategy;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class RandomStrategy extends BaseStrategy {

    @Override
    public IMovement[] compute(GameBoard board) {
        int randomRoute = (int)(Math.random() * board.getRoutes().size());
        IRoute route = board.getRoutes().get(randomRoute);

        int randomVillage = (int)(Math.random() * route.getVillages().size());
        IVillage village = route.getVillages().get(randomVillage);

        int neededTraders = getNeededTraderCount(village);
        if (hasEnoughTrader(neededTraders)) {
            IMovement movement = MovementFactory.getInstance().makeMovement(village.getClickableArea(), null);
            return new IMovement[] {movement};
        } else {
            IMovement movement = new MovePawnRtoS(this.getPlayer(), 0, getPawnThatCanBeMovedToStock());
            return new IMovement[] {movement};
        }
    }
}
