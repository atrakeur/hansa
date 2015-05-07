package fr.univ_rouen.hansa.gameboard.player;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.LinkedList;
import java.util.Map;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.VictoryCoellen;
import fr.univ_rouen.hansa.gameboard.player.escritoire.Escritoire;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;

public class HTPlayer extends ScorePlayer implements IHTPlayer {
    private final PlayerColor color;
    private final IEscritoire escritoire;

    private int action;

    /**
     * Init player from a saveguard
     *
     * @param color the color of the player
     * @param escritoire of the player
     * @param action number of action the player can make
     */
    public HTPlayer(PlayerColor color, IEscritoire escritoire, int action) {
        super();

        this.color = color;
        this.escritoire = escritoire;
        this.action = action;
    }

    /**
     * Init a player for a new game
     *
     * @param color the color of the player
     * @param startingPlace the place of the player at the begining of the party (1 to x)
     */
    public HTPlayer(PlayerColor color, int startingPlace) {
        super();

        this.color = color;
        this.escritoire = new Escritoire(this, startingPlace);

        this.action = 2;
    }

    @Override
    public IEscritoire getEscritoire() {
        return escritoire;
    }

    @Override
    public PlayerColor getPlayerColor() {
        return color;
    }

    @Override
    public int getActionNumber() {
        return action;
    }

    @Override
    public void setActionNumber(int i) {
        action = action + i;
    }

    @Override
    public void newTurn() {
        action = escritoire.actionesLevel();
    }


    @Override
    public int getFinalScore() {
        int finalscore = getScore();

        //skill upgrade to max : +4 point (not for clavis urbis)
        if (getEscritoire().actionesLevel() == 5) {
            finalscore += 4;
        }
        if (getEscritoire().bursaLevel() == Integer.MAX_VALUE) {
            finalscore += 4;
        }
        if (getEscritoire().liberSophiaLevel() == 5) {
            finalscore += 4;
        }
        if (getEscritoire().privilegiumLevel() == Privillegium.Black) {
            finalscore += 4;
        }

        //points allowed for bonus markers
        switch (getEscritoire().getBonusMarker().size()) {
            case 0:
                break;
            case 1:
                finalscore += 1;
                break;
            case 2:
            case 3:
                finalscore += 3;
                break;
            case 4:
            case 5:
                finalscore += 6;
                break;
            case 6:
            case 7:
                finalscore +=  10;
                break;
            case 8:
            case 9:
                finalscore += 15;
                break;
            default:  // <= 10
                finalscore += 21;
                break;
        }

        VictoryCoellen coellen = VictoryCoellen.getInstance();
        for (Privillegium privillegium : Privillegium.values()) {
            if (coellen.getPawn(privillegium) != null && coellen.getPawn(privillegium).getPlayer() == this) {
                finalscore +=  coellen.getValue(privillegium);
            }
        }

        //used for biggest network
        Map<ICity, Boolean> visited = Maps.newHashMap();

        //for each city the owner gain 2 points + init visited
        for (ICity city : GameBoardFactory.getGameBoard().getCities()) {
            if (city.getOwner() == this) {
                finalscore += 2;
            }
            visited.put(city, false);
        }

        //add highestNetwork size * clavis urbis
        int highestNetworkSize = 0;
        for (ICity city : GameBoardFactory.getGameBoard().getCities()) {
            if (!visited.get(city) && city.numberOfKontorsOwned(this) != 0) {
                LinkedList<ICity> network = Lists.newLinkedList();
                int networkSize = 0;
                network.add(city);

                while (!network.isEmpty()) {
                    ICity tmpCity = network.getFirst();
                    network.removeFirst();
                    networkSize += tmpCity.numberOfKontorsOwned(this);
                    visited.put(tmpCity, true);
                    for (IRoute route : tmpCity.getRoutes()) {
                        for (ICity crossRoad : route.getCities()) {
                            if (crossRoad != tmpCity && !visited.get(crossRoad) && crossRoad.numberOfKontorsOwned(this) != 0) {
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
        finalscore += getEscritoire().clavisUrbisLevel() * highestNetworkSize;

        return finalscore;
    }

}
