package fr.univ_rouen.hansa.gameboard;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;

public class GameBoard {

    private static GameBoard ourInstance = new GameBoard();

    List<ICity> cities;
    List<IRoute> routes;
    TurnManager manager;

    public static GameBoard getInstance() {
        return ourInstance;
    }

    private GameBoard() {
        cities = Lists.newArrayList();
        routes = Lists.newArrayList();
        manager = TurnManager.getInstance();
    }

}
