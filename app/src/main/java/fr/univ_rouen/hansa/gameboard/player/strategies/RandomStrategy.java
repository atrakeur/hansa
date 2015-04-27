package fr.univ_rouen.hansa.gameboard.player.strategies;

import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.actions.movement.MovePawnRtoGB;
import fr.univ_rouen.hansa.actions.movement.MovePawnRtoS;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.player.ComputerStrategy;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class RandomStrategy implements ComputerStrategy {

    private IHTPlayer player;
    private Strategy strategy;

    public RandomStrategy() {
        strategy = Strategy.RandomStrategy;
    }

    @Override
    public IMovement[] compute(GameBoard board) {
        if (player.getEscritoire().getSupply().getTraderCount() == 0) {
            int pawnThatCanBeMovedToStock = Math.min(player.getEscritoire().bursaLevel(), player.getEscritoire().getStock().getTraderCount());
            IMovement movement = new MovePawnRtoS(this.getPlayer(), 0, pawnThatCanBeMovedToStock);

            return new IMovement[] {movement};
        } else {
            int randomRoute = (int)(Math.random() * board.getRoutes().size());
            IRoute route = board.getRoutes().get(randomRoute);

            int randomVillage = (int)(Math.random() * route.getVillages().size());
            IVillage village = route.getVillages().get(randomVillage);

            IMovement movement = new MovePawnRtoGB(this.getPlayer(), village, Trader.class);

            return new IMovement[] {movement};
        }
    }

    @Override
    public IHTPlayer getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(IHTPlayer player) {
        this.player = player;
    }

    @Override
    public Strategy getStrategy() {
        return strategy;
    }
}
