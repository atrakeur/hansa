package fr.univ_rouen.hansa.gameboard.board;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;

public abstract class RouteBoard extends CitiesBoard {

    private List<IRoute> routes;
    private List<IBonusMarker> bonusStack;

    public RouteBoard() {
        super();

        routes = Lists.newArrayList();
        bonusStack = Lists.newArrayList();
    }

    protected void addRoute(IRoute route)
    {
        ICity[] cities = route.getCities();
        if (cities.length != 2 || getRoute(cities[0], cities[1]) != null)
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
            ICity[] cities = route.getCities();

            if (cities[0] == city1 && cities[1] == city2) {
                return route;
            }

            if (cities[1] == city1 && cities[0] == city2) {
                return route;
            }
        }

        return null;
    }

    public List<IBonusMarker> getBonusStack() {
        return bonusStack;
    }

    public void setBonusStack(List<IBonusMarker> bonusStack) {
        this.bonusStack = bonusStack;
    }
}
