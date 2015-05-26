package fr.univ_rouen.hansa.gameboard.board;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

import fr.univ_rouen.hansa.exceptions.EndOfGameException;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.Power;

public abstract class CitiesBoard extends BasicBoard {

    List<ICity> cities;
    private int citiesCompleted;

    public CitiesBoard() {
        super();
        citiesCompleted = 0;
        cities = Lists.newArrayList();
    }

    public void addCity(ICity city)
    {
        cities.add(city);
    }

    public void increaseCityCompleted() {
        citiesCompleted++;
        if (citiesCompleted >=   10) {
            throw new EndOfGameException();
        }
    }

    public int getCityCompleted() {
        return citiesCompleted;
    }

    public List<ICity> getCities() {
        return Collections.unmodifiableList(cities);
    }

    public ICity getCityByPower(Power power) {
        for (ICity city: cities) {
            if (city.getPower() == power) {
                return city;
            }
        }

        throw new RuntimeException("No city found for power "+power);
    }

}
