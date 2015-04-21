package fr.univ_rouen.hansa.save.dao.gameboard.routes;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.save.dao.gameboard.BonusMarkerDao;
import fr.univ_rouen.hansa.save.dao.gameboard.PositionDao;

public class RouteDao {
    private List<VillageDao> villages;
    private PositionDao[] cities;
    private PositionDao tavernPosition;
    private BonusMarkerDao bonusMarker;

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
}
