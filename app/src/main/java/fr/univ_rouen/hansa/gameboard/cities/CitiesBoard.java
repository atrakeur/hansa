package fr.univ_rouen.hansa.gameboard.cities;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

import fr.univ_rouen.hansa.gameboard.BasicBoard;
import fr.univ_rouen.hansa.gameboard.cities.ICity;

public abstract class CitiesBoard extends BasicBoard {

    List<ICity> cities;

    public CitiesBoard()
    {
        cities = Lists.newArrayList();
    }

    protected void addCity(ICity city)
    {
        cities.add(city);
    }

    public List<ICity> getCities() {
        return Collections.unmodifiableList(cities);
    }

}
