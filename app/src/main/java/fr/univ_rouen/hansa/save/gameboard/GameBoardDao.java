package fr.univ_rouen.hansa.save.gameboard;

import java.util.ArrayList;
import java.util.List;

import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.cities.ICity;

public class GameBoardDao {

    private List<CityDao> cities;

    public GameBoardDao(GameBoard gameBoard) {
        cities = new ArrayList<>();

        for (ICity city : gameBoard.getCities()) {
            cities.add(new CityDao(city));
        }
    }
}
