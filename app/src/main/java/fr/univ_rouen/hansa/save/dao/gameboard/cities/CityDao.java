package fr.univ_rouen.hansa.save.dao.gameboard.cities;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.save.dao.gameboard.PositionDao;

public class CityDao {

    private List<KontorDao> kontors;
    private List<KontorDao> additionalKontors;
    private Power power;
    private PositionDao position;

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
