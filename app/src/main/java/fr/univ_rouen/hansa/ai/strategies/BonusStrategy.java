package fr.univ_rouen.hansa.ai.strategies;

import android.util.Log;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.actions.movement.KeepKontor;
import fr.univ_rouen.hansa.actions.movement.KeepRoute;
import fr.univ_rouen.hansa.actions.movement.MovePawnRtoS;
import fr.univ_rouen.hansa.ai.StrategyType;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

/**
 * Bonus Strategy
 *
 * Le but de cette strategie est de recolter le maximum de bonus au cours de la partie.
 * Pour cela l'ordinateur va tenter de prendre:
 *      - soit des actiones
 *      - soit des bonus markers
 *
 * Lorsque qu'un bonus marker est sur le point d'etre pris (route pleine)
 * L'IA optimise en prennant un comptoir si il est libre et qu'elle a le privillege qu'il faut
 *
 * L'IA choisi en random si elle prend une action ou un bonus (proba de 0.75 pour le bonus)
 * L'IA ne tente plus de prendre des actions si actiones >= 4
 *
 * TODO L'IA pourrais essayer de jouer ses jetons bonus (notamment L'actiones + 3 ou + 4
 */
public class BonusStrategy extends BaseStrategy {

    private enum State {
        ACTIONES,
        TAKING_BONUSES
    }

    private int stateRemain = 0;
    private State state = State.ACTIONES;

    private IRoute targetRoute = null;

    public BonusStrategy() {
        super(StrategyType.bonusStrategy);
    }

    @Override
    public IMovement[] compute(GameBoard board) {
        if (TurnManager.getInstance().getCurrentPlayingPlayer() != TurnManager.getInstance().getCurrentPlayer()) {
            //Cas ou on en remplaces un pion
            Log.w("AI", "AIThread must replace pawn");
            List<IVillage> remplaceVillages = MovementManager.getInstance().getVillageReplace().getAdjacentsVillages();

            int randVillage = (int)(Math.random() * remplaceVillages.size());
            return takeVillage(remplaceVillages.get(randVillage));
        }

        stateRemain--;
        if (stateRemain <= 0) {
            if (getPlayer().getActionNumber() <= 4 && Math.random() > 0.75) {
                state = State.ACTIONES;
                stateRemain = 2;
            } else {
                state = State.TAKING_BONUSES;
                stateRemain = 4;
            }
        }

        if (state == State.ACTIONES) {
            return takePower(Power.Actiones);
        } else {
            //no current target, or target taken? find one another
            if (targetRoute == null || targetRoute.getBonusMarker() == null) {
                //Get all routes that has a bonus marker on it
                List<IRoute> potentialTargets = Lists.newArrayList();
                for (IRoute route : GameBoardFactory.getGameBoard().getRoutes()) {
                    if (route.getBonusMarker() != null) {
                        potentialTargets.add(route);
                        if (potentialTargets.size() == 3) {
                            break;
                        }
                    }
                }

                //Search the route that cost less to take
                int lessExpensiveRouteCost = Integer.MAX_VALUE;
                IRoute lessExpensiveRoute = null;
                for (IRoute route: potentialTargets) {
                    int cost = getNeededTraderCount(route);
                    if (cost < lessExpensiveRouteCost) {
                        lessExpensiveRouteCost = cost;
                        lessExpensiveRoute = route;
                    }
                }

                //definitely select it as a target
                this.targetRoute = lessExpensiveRoute;
            }

            //Take route (take all villages, and return null when done)
            IMovement[] moves = fullfillRoute(targetRoute);
            if (moves != null) {
                return moves;
            }

            //Take one of the kontor if possible, just take route otherwise
            for (ICity city: targetRoute.getCities()) {
                if (
                        getPlayer().getEscritoire().privilegiumLevel().isBetterThan(
                                city.getNextKontor().getPrivillegium()
                        ) && city.getNextKontor().getPawnClass() == Trader.class) {
                    IMovement movement = new KeepKontor(getPlayer(), city, targetRoute.getVillage(0));
                    return new IMovement[] {movement};
                }
            }

            IMovement movement = new KeepRoute(getPlayer(), targetRoute);
            return new IMovement[] {movement};
        }
    }

}
