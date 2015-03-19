package fr.univ_rouen.hansa.gameboard.cities;


import java.util.ArrayList;
import java.util.List;

import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.view.IPosition;
import fr.univ_rouen.hansa.view.display.HansaCityDrawer;
import fr.univ_rouen.hansa.view.display.IDrawer;
import fr.univ_rouen.hansa.view.interactions.HansaCityClickableArea;
import fr.univ_rouen.hansa.view.interactions.IClickableArea;

public class City implements ICity {

    private static IDrawer drawer;
    private final IClickableArea clickableArea;

    private final IPosition position;
    private final Power power;
    private final List<IKontor<? extends Pawn>> kontors;
    private final List<IRoute> routes;

    public City(IPosition position, Power power, List<IKontor<? extends Pawn>> kontors) {
        if (kontors == null) {
            throw new IllegalArgumentException();
        }

        this.drawer = new HansaCityDrawer(this);
        this.clickableArea = new HansaCityClickableArea(this);

        this.position = position;
        this.power = power;
        this.kontors = kontors;

        this.routes = new ArrayList<>();
    }
    public void setDrawer(IDrawer drawer){
        if(drawer ==null){
            throw new IllegalArgumentException();
        }
        this.drawer = drawer;

    }

    @Override
    public void setRoute(IRoute route) {
        routes.add(route);
    }

    @Override
    public List<IRoute> getRoutes() {
        return new ArrayList<>(routes);
    }

    @Override
    public IKontor<? extends Pawn> getKontor(int i) {
        if (i < 0 || i > kontors.size()) {
            throw new IllegalArgumentException();
        }

        return kontors.get(i);
    }

    @Override
    public IKontor<? extends Pawn> getNextKontor() {
        for (IKontor<? extends Pawn> kontor : kontors) {
            if (kontor.isEmpty()) {
                return kontor;
            }
        }

        return null;
    }

    @Override
    public IPosition getPosition() {
        return position;
    }

    @Override
    public List<IKontor<? extends Pawn>> getKontors() {
        return new ArrayList<>(kontors);
    }

    @Override
    public Power getPower() {
        return power;
    }

    @Override
    public boolean isCompletedCity() {
        for (IKontor<? extends Pawn> k : kontors) {
            if (k.isEmpty()) {
                return false;
            }

        }

        return true;
    }

    @Override
    public IDrawer getDrawer() {
        return this.drawer;
    }

    @Override
    public IClickableArea getClickableArea() {
        return this.clickableArea;
    }
}
