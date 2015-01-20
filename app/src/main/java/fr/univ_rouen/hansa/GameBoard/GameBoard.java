package fr.univ_rouen.hansa.GameBoard;

import java.util.List;

import fr.univ_rouen.hansa.GameBoard.Cities.ICity;
import fr.univ_rouen.hansa.GameBoard.Routes.IRoute;

public class GameBoard {

    List<ICity> cities;
    List<IRoute> routes;
    ITurnManager manager;

}
