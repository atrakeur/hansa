package fr.univ_rouen.hansa.gameboard.routes;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

import fr.univ_rouen.hansa.gameboard.cities.CitiesBoard;
import fr.univ_rouen.hansa.gameboard.cities.ICity;

public abstract class RouteBoard extends CitiesBoard {

    private List<IRoute> routes;

    public RouteBoard()
    {
        routes = Lists.newArrayList();
    }

    public List<IRoute> getRoutes() {
        return Collections.unmodifiableList(routes);
    }

    public IRoute getRoute(ICity city1, ICity city2) {
        for(IRoute route: routes) {
            List<ICity> cities = route.getCities();
            if (cities.contains(city1) && cities.contains(city2)) {
                return route;
            }
        }

        return null;
    }

}
