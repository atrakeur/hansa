package fr.univ_rouen.hansa.save.gameboard.routes;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.save.gameboard.PositionDao;

public class RouteDao {
    private List<VillageDao> villages;
    private PositionDao[] cities;
    private PositionDao tavernPosition;

    public RouteDao(IRoute route) {
        this.villages = Lists.newArrayList();

        for (IVillage village : route.getVillages()) {
            this.villages.add(new VillageDao(village));
        }

        cities = new PositionDao[2];
        cities[0] = new PositionDao(route.getCities()[0].getPosition());
        cities[1] = new PositionDao(route.getCities()[1].getPosition());

        this.tavernPosition = new PositionDao(route.getTavernPosition());
    }
}
