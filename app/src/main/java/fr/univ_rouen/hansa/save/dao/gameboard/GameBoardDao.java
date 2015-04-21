package fr.univ_rouen.hansa.save.dao.gameboard;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.save.dao.gameboard.cities.CityDao;
import fr.univ_rouen.hansa.save.dao.gameboard.routes.RouteDao;

public class GameBoardDao {

    private List<CityDao> cities;
    private List<RouteDao> routes;

    public GameBoardDao() {
    }

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

    public List<CityDao> getCities() {
        return cities;
    }

    public void setCities(List<CityDao> cities) {
        this.cities = cities;
    }

    public List<RouteDao> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteDao> routes) {
        this.routes = routes;
    }
}
