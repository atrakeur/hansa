package fr.univ_rouen.hansa.save.gameboard;

import java.util.ArrayList;
import java.util.List;

import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.Power;

public class CityDao {

    private List<KontorDao> kontors;
    private Power power;

    public CityDao(ICity city) {
        kontors = new ArrayList<>();

        for (IKontor kontor : city.getKontors()) {
            kontors.add(new KontorDao(kontor));
        }
    }
}
