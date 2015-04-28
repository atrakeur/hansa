package fr.univ_rouen.hansa.save.dao.gameboard.cities;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.cities.City;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.save.dao.Dao;
import fr.univ_rouen.hansa.save.dao.gameboard.PositionDao;
import fr.univ_rouen.hansa.view.IPosition;

public class CityDao implements Dao<ICity> {

    private List<KontorDao> kontors;
    private List<KontorDao> additionalKontors;
    private Power power;
    private PositionDao position;

    public CityDao() {
    }

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

    @Override
    public ICity daoToEntity() {
        ICity cityEntity;
        IPosition positionEntity = position.daoToEntity();
        List<IKontor<? extends Pawn>> kontorsEntities = Lists.newArrayList();

        for (KontorDao kontorDao : kontors) {
            kontorsEntities.add(kontorDao.daoToEntity());
        }

        cityEntity = new City(positionEntity, power, kontorsEntities);

        for (KontorDao kontorDao : additionalKontors) {
            cityEntity.pushAdditionalKontors(kontorDao.daoToEntity());
        }

        return cityEntity;
    }

    public List<KontorDao> getKontors() {
        return kontors;
    }

    public void setKontors(List<KontorDao> kontors) {
        this.kontors = kontors;
    }

    public List<KontorDao> getAdditionalKontors() {
        return additionalKontors;
    }

    public void setAdditionalKontors(List<KontorDao> additionalKontors) {
        this.additionalKontors = additionalKontors;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public PositionDao getPosition() {
        return position;
    }

    public void setPosition(PositionDao position) {
        this.position = position;
    }
}
