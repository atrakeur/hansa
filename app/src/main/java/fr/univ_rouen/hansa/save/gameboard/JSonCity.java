package fr.univ_rouen.hansa.save.gameboard;

import java.util.ArrayList;
import java.util.List;

import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.Power;

public class JSonCity {

    private List<JSonKontor> kontors;
    private Power power;

    public JSonCity(ICity city) {
        kontors = new ArrayList<>();

        for (IKontor kontor : city.getKontors()) {
            kontors.add(new JSonKontor(kontor));
        }
    }
}
