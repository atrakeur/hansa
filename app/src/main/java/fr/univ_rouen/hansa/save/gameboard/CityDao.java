package fr.univ_rouen.hansa.save.gameboard;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.Power;

public class CityDao {

    private final List<KontorDao> kontors;
    private final List<KontorDao> additionalKontors;
    private final Power power;
    private final PositionDao position;

    public CityDao(ICity city) {
        this.kontors = Lists.newArrayList();
        this.additionalKontors = Lists.newArrayList();

        for (IKontor kontor : city.getKontors()) {
            kontors.add(new KontorDao(kontor));
        }

        for (IKontor kontor : city.getAdditionalKontors()) {
            additionalKontors.add(new KontorDao(kontor));
        }

        this.power = city.getPower();
        this.position = new PositionDao(city.getPosition());
    }
}
