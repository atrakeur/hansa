package fr.univ_rouen.hansa.save.dao.gameboard;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.save.dao.Dao;
import fr.univ_rouen.hansa.save.dao.gameboard.cities.CityDao;
import fr.univ_rouen.hansa.save.dao.gameboard.routes.RouteDao;

public class GameBoardDao implements Dao<GameBoard> {

    private List<CityDao> cities;
    private List<RouteDao> routes;
    private List<BonusMarkerDao> bonusMarkers;
    private int playerGameBoard;

    public GameBoardDao() {
    }

    public GameBoardDao(GameBoard gameBoard) {
        cities = Lists.newArrayList();
        routes = Lists.newArrayList();
        bonusMarkers = Lists.newArrayList();

        for (ICity city : gameBoard.getCities()) {
            cities.add(new CityDao(city));
        }

        for (IRoute route : gameBoard.getRoutes()) {
            routes.add(new RouteDao(route));
        }

        for (IBonusMarker bonusMarker : gameBoard.getBonusStack()) {
            bonusMarkers.add(new BonusMarkerDao(bonusMarker));
        }

        playerGameBoard = gameBoard.getBackground();
    }

    @Override
    public GameBoard daoToEntity() {
        GameBoard gameBoard = new GameBoard(playerGameBoard);

        List<ICity> citiesEntities = Lists.newArrayList();
        for (CityDao city : cities) {
            citiesEntities.add(city.daoToEntity());
        }

        List<IRoute> routesEntities = Lists.newArrayList();
        for (RouteDao route : routes) {
            routesEntities.add(route.daoToEntity(citiesEntities));
        }

        List<IBonusMarker> bonusMarkersEntities = Lists.newArrayList();
        for (BonusMarkerDao bonusMarker : bonusMarkers) {
            bonusMarkersEntities.add(bonusMarker.daoToEntity());
        }

        return gameBoard;
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

    public List<BonusMarkerDao> getBonusMarkers() {
        return bonusMarkers;
    }

    public void setBonusMarkers(List<BonusMarkerDao> bonusMarkers) {
        this.bonusMarkers = bonusMarkers;
    }

    public int getPlayerGameBoard() {
        return playerGameBoard;
    }

    public void setPlayerGameBoard(int playerGameBoard) {
        this.playerGameBoard = playerGameBoard;
    }
}
