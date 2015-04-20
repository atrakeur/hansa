package fr.univ_rouen.hansa.save.gameboard;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.save.gameboard.cities.CityDao;
import fr.univ_rouen.hansa.save.gameboard.routes.RouteDao;

public class GameBoardDao {

    private List<CityDao> cities;
    private List<RouteDao> routes;

    public GameBoardDao(GameBoard gameBoard) {
        cities = Lists.newArrayList();
        routes = Lists.newArrayList();

        for (ICity city : gameBoard.getCities()) {
            cities.add(new CityDao(city));
        }

        for (IRoute route : gameBoard.getRoutes()) {
            routes.add(new RouteDao(route));
        }
    }
}
