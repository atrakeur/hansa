package fr.univ_rouen.hansa.gameboard.cities;


import java.util.List;

import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.view.IPosition;
import fr.univ_rouen.hansa.view.display.HansaCityDrawer;
import fr.univ_rouen.hansa.view.display.IDrawer;
import fr.univ_rouen.hansa.view.interactions.HansaCityClickableArea;
import fr.univ_rouen.hansa.view.interactions.IClickableArea;

public class City implements ICity {

    private final IDrawer drawer;
    private final IClickableArea clickableArea;

    private final IPosition position;
    private final Power power;
    private final List<IKontor<? extends Pawn>> kontors;

    public City(IPosition position, Power power, List<IKontor<? extends Pawn>> kontors) {
        if (kontors == null) {
            throw new IllegalArgumentException();
        }

        this.drawer = new HansaCityDrawer(this);

        this.position = position;
        this.power = power;
        this.kontors = kontors;
        clickableArea = new HansaCityClickableArea(this);
    }

    @Override
    public IKontor getKontor(int i) {
        if (i < 0 || i > kontors.size()) {
            throw new IllegalArgumentException();
        }

        return kontors.get(i);
    }

    @Override
    public IPosition getPosition() {
        return position;
    }

    @Override
    public List<IKontor<? extends Pawn>> getKontors() {
        return kontors;
    }

    @Override
    public Power getPower() {
        return power;
    }

    @Override
    public boolean isCompletedCity() {
        for (IKontor k : kontors) {
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
