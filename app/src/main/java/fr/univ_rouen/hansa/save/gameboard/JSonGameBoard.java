package fr.univ_rouen.hansa.save.gameboard;

import java.util.ArrayList;
import java.util.List;

import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.cities.ICity;

public class JSonGameBoard {

    private List<JSonCity> cities;

    public JSonGameBoard(GameBoard gameBoard) {
        cities = new ArrayList<>();

        for (ICity city : gameBoard.getCities()) {
            cities.add(new JSonCity(city));
        }
    }
}
