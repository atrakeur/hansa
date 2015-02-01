package fr.univ_rouen.hansa.gameboard;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;

public class GameBoard {
    private List<ICity> cities;
    private List<IRoute> routes;
    private TurnManager manager;

    GameBoard() {
        cities = Lists.newArrayList();
        routes = Lists.newArrayList();
        manager = TurnManager.getInstance();
    }

    void addCity(ICity city) {
        cities.add(city);
    }

    void addRoutes(IRoute route) {
        routes.add(route);
    }

}
