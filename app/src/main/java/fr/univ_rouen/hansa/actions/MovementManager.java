package fr.univ_rouen.hansa.actions;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.LinkedList;
import java.util.Map;

import fr.univ_rouen.hansa.actions.actions.ActionFactory;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.actions.movement.PlaceBonusMarker;
import fr.univ_rouen.hansa.actions.movement.PlayBonus;
import fr.univ_rouen.hansa.exceptions.FinishedRoundException;
import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.VictoryCoellen;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;

public class MovementManager {

    private static MovementManager instance = new MovementManager();

    private final Stack<IMovement> stack;
    private ActionFactory actionFactory;

    private MovementManager() {
        stack = new Stack<IMovement>();

        actionFactory = new ActionFactory();
    }

    public static MovementManager getInstance() {
        return instance;
    }

    public void doMove(IMovement m) {
        if (m.isDone()) {
            throw new IllegalStateException("Can't do the same Movement twice");
        }

        int playerAction = TurnManager.getInstance().getCurrentPlayer().getActionNumber();
        int actionsDone = this.actionCounter();

        if (hasPawnToReplace() || actionsDone < playerAction || m instanceof PlayBonus || m instanceof PlaceBonusMarker) {
            m.doMovement();
            stack.push(m);
        } else {
            throw new FinishedRoundException();
        }
    }

    public IMovement rollbackMove() {
        IMovement m = stack.pop();
        if (!m.isDone()) {
            stack.push(m);
            throw new IllegalStateException("Can't rollback the same Movement twice");
        }

        m.doRollback();
        return m;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void nextTurn() {
        stack.clear();

    }

    public int actionCounter() {
        return actionFactory.getActions(stack.getStackContent()).size();
    }

    public Pawn getPawnToReplace() {
        return this.actionFactory.getPawnToReplace(stack.getStackContent());
    }

    public boolean hasPawnToReplace() {
        return this.actionFactory.hasPawnToReplace(stack.getStackContent());
    }


    public void addEndGamePoints() {
        for (IHTPlayer player : TurnManager.getInstance().getPlayers()) {
            //skill upgrade to max : +4 point (not for clavis urbis)
            if (player.getEscritoire().actionesLevel() == 5) {
                player.setScore(player.getScore() + 4);
            }
            if (player.getEscritoire().bursaLevel() == Integer.MAX_VALUE) {
                player.setScore(player.getScore() + 4);
            }
            if (player.getEscritoire().liberSophiaLevel() == 5) {
                player.setScore(player.getScore() + 4);
            }
            if (player.getEscritoire().privilegiumLevel() == Privillegium.Black) {
                player.setScore(player.getScore() + 4);
            }

            //points allowed for bonus markers
            switch (player.getEscritoire().getBonusMarker().size()) {
                case 0:
                    break;
                case 1:
                    player.setScore(player.getScore() + 1);
                    break;
                case 2:
                case 3:
                    player.setScore(player.getScore() + 3);
                    break;
                case 4:
                case 5:
                    player.setScore(player.getScore() + 6);
                    break;
                case 6:
                case 7:
                    player.setScore(player.getScore() + 10);
                    break;
                case 8:
                case 9:
                    player.setScore(player.getScore() + 15);
                    break;
                default:  // <= 10
                    player.setScore(player.getScore() + 21);
                    break;
            }

            VictoryCoellen coellen = VictoryCoellen.getInstance();
            for (Privillegium privillegium : Privillegium.values()) {
                if (coellen.getPawn(privillegium) != null && coellen.getPawn(privillegium).getPlayer() == player) {
                    player.setScore(player.getScore() + coellen.getValue(privillegium));
                }
            }

            //used for biggest network
            Map<ICity, Boolean> visited = Maps.newHashMap();

            //for each city the owner gain 2 points + init visited
            for (ICity city : GameBoardFactory.getGameBoard().getCities()) {
                if (city.getOwner() == player) {
                    player.setScore(player.getScore() + 2);
                }
                visited.put(city, false);
            }

            //add highestNetwork size * clavis urbis
            int highestNetworkSize = 0;
            for (ICity city : GameBoardFactory.getGameBoard().getCities()) {
                if (!visited.get(city) && city.numberOfKontorsOwned(player) != 0) {
                    LinkedList<ICity> network = Lists.newLinkedList();
                    int networkSize = 0;
                    network.add(city);

                    while (!network.isEmpty()) {
                        ICity tmpCity = network.getFirst();
                        network.removeFirst();
                        networkSize += tmpCity.numberOfKontorsOwned(player);
                        visited.put(tmpCity, true);
                        for (IRoute route : tmpCity.getRoutes()) {
                            for (ICity crossRoad : route.getCities()) {
                                if (crossRoad != tmpCity && !visited.get(crossRoad) && crossRoad.numberOfKontorsOwned(player) != 0) {
                                    network.add(crossRoad);
                                }
                            }
                        }
                    }
                    if (highestNetworkSize < networkSize) {
                        highestNetworkSize = networkSize;
                    }
                }
            }
            player.setScore(player.getScore() + player.getEscritoire().clavisUrbisLevel() * highestNetworkSize);
        }
    }

}
