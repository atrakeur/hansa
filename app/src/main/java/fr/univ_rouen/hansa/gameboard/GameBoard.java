package fr.univ_rouen.hansa.gameboard;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;

public class GameBoard {

    List<ICity> cities;
    List<IRoute> routes;
    ITurnManager manager;

}
