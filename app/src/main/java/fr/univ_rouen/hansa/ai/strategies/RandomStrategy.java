package fr.univ_rouen.hansa.ai.strategies;

import android.util.Log;

import java.util.List;

import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.actions.actions.ActionFactory;
import fr.univ_rouen.hansa.actions.movement.IMovement;
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
 * Random Strategy
 *
 * Le but de cette stratégie est de prendre des villes (kontoirs) au hasard
 * Pour cela l'ordinateur va tenter de prendre:
 *      - soit des actiones
 *      - soit des kontoirs
 *
 * L'IA prend un comptoir, ou alors des actiones (proba de 0.50)
 * L'IA prend automatiquement un privliegium si sa cible est d'un privillegium plus important
 *
 */
public class RandomStrategy extends BaseStrategy {

    private enum State {
        ACTIONES,
        TAKING_KONTORS
    }

    private int stateRemain = 0;
    private State state = State.ACTIONES;

    private ICity targetCity = null;
    private IKontor targetKontor = null;

    public RandomStrategy() {
        super(StrategyType.randomStrategy);
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
            if (getPlayer().getActionNumber() >= 4 && Math.random() > 0.50) {
                state = State.ACTIONES;
                stateRemain = 3;
            } else {
                state = State.TAKING_KONTORS;
                stateRemain = 6;
            }
        }


        if (state == State.ACTIONES) {
            return takePower(Power.Actiones);
        } else {
            //no current target, or target taken? find one another
            if (targetCity == null || targetKontor.getOwner() != null) {
                //Select three cities, and take the less expensive
                ICity lessExpensiveCity = null;
                int lessExpensiveCityVal = Integer.MAX_VALUE;
                for (int i = 0; i < 3; i++) {
                    //Select a city so that the next kontor is a trader
                    ICity city;
                    do {
                        int randomCity = (int)(Math.random() * GameBoardFactory.getGameBoard().getCities().size());
                        city = GameBoardFactory.getGameBoard().getCities().get(randomCity);
                    } while (city.getNextKontor().getPawnClass() == Trader.class);

                    //Calculate the less expensive path to get that kontor
                    IRoute route = getLessExpensiveRouteToCity(city);
                    int neededTrader = getNeededTraderCount(route);

                    //Less expensive city? select that one
                    if (lessExpensiveCityVal >= neededTrader) {
                        lessExpensiveCityVal = neededTrader;
                        lessExpensiveCity = city;
                    }
                }

                //definitely select it as a target
                this.targetCity = lessExpensiveCity;
                this.targetKontor = lessExpensiveCity.getNextKontor();
            }

            //Take kontor (take care of taking privillegium and etceaterea)
            return takeKontor(targetCity);
        }
    }
}
