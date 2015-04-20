package fr.univ_rouen.hansa.save.gameboard;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.cities.ICity;

public class GameBoardDao {

    private List<CityDao> cities;

    public GameBoardDao(GameBoard gameBoard) {
        cities = Lists.newArrayList();

        for (ICity city : gameBoard.getCities()) {
            cities.add(new CityDao(city));
        }
    }
}
