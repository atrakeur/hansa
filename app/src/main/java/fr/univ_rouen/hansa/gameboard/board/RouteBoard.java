package fr.univ_rouen.hansa.gameboard.board;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

import fr.univ_rouen.hansa.gameboard.board.CitiesBoard;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;

public abstract class RouteBoard extends CitiesBoard {

    private List<IRoute> routes;

    public RouteBoard()
    {
        routes = Lists.newArrayList();
    }

    protected void addRoute(IRoute route)
    {
        List<ICity> cities = route.getCities();
        if (cities.size() != 2 || getRoute(cities.get(0), cities.get(1)) != null)
        {
            throw new IllegalArgumentException("addRoute need a route that link two cities together.");
        }

        routes.add(route);
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
