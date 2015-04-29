package fr.univ_rouen.hansa.save.dao.gameboard.routes;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.gameboard.routes.Route;
import fr.univ_rouen.hansa.save.dao.gameboard.BonusMarkerDao;
import fr.univ_rouen.hansa.save.dao.gameboard.PositionDao;
import fr.univ_rouen.hansa.view.IPosition;

public class RouteDao {
    private List<VillageDao> villages;
    private PositionDao[] cities;
    private PositionDao tavernPosition;
    private BonusMarkerDao bonusMarker;

    public RouteDao() {
    }

    public RouteDao(IRoute route) {
        this.villages = Lists.newArrayList();

        for (IVillage village : route.getVillages()) {
            this.villages.add(new VillageDao(village));
        }

        cities = new PositionDao[2];
        cities[0] = new PositionDao(route.getCities()[0].getPosition());
        cities[1] = new PositionDao(route.getCities()[1].getPosition());

        this.tavernPosition = new PositionDao(route.getTavernPosition());

        IBonusMarker routeBM = route.getBonusMarker();

        if (routeBM != null) {
            this.bonusMarker = new BonusMarkerDao(route.getBonusMarker());
        } else {
            this.bonusMarker = null;
        }
    }

    public IRoute daoToEntity(List<ICity> citiesEntitiesList) {
        List<IVillage> villagesEntitites = Lists.newArrayList();
        ICity[] citiesEntities = new ICity[2];
        IPosition tavernEntity = tavernPosition.daoToEntity();
        IBonusMarker bonusEntity = bonusMarker.daoToEntity();

        for (VillageDao village : villages) {
            villagesEntitites.add(village.daoToEntity());
        }

        for (ICity city : citiesEntitiesList) {
            if (city.getPosition().getX() == cities[0].getX()
                    && city.getPosition().getY() == cities[0].getY()) {
                citiesEntities[0] = city;
            } else if (city.getPosition().getX() == cities[1].getX()
                    && city.getPosition().getY() == cities[1].getY()) {
                citiesEntities[1] = city;
            }
        }

        return new Route(villagesEntitites, citiesEntities, tavernEntity, bonusEntity);
    }

    public List<VillageDao> getVillages() {
        return villages;
    }

    public void setVillages(List<VillageDao> villages) {
        this.villages = villages;
    }

    public PositionDao[] getCities() {
        return cities;
    }

    public void setCities(PositionDao[] cities) {
        this.cities = cities;
    }

    public PositionDao getTavernPosition() {
        return tavernPosition;
    }

    public void setTavernPosition(PositionDao tavernPosition) {
        this.tavernPosition = tavernPosition;
    }

    public BonusMarkerDao getBonusMarker() {
        return bonusMarker;
    }

    public void setBonusMarker(BonusMarkerDao bonusMarker) {
        this.bonusMarker = bonusMarker;
    }

}
