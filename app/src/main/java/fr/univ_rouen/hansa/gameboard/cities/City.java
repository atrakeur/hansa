package fr.univ_rouen.hansa.gameboard.cities;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.view.IPosition;
import fr.univ_rouen.hansa.view.display.HansaCityDrawer;
import fr.univ_rouen.hansa.view.display.IDrawer;
import fr.univ_rouen.hansa.view.interactions.HansaCityClickableArea;
import fr.univ_rouen.hansa.view.interactions.HansaPowerClickableArea;
import fr.univ_rouen.hansa.view.interactions.IClickable;
import fr.univ_rouen.hansa.view.interactions.IClickableArea;

public class City implements ICity {

    private final IDrawer drawer;
    private final IClickableArea cityClickableArea;
    private final IPosition position;
    private final Power power;
    private final List<IKontor<? extends Pawn>> kontors;
    private final List<IKontor<? extends Pawn>> additionalKontors;
    private final List<IRoute> routes;

    public City(IPosition position, Power power, List<IKontor<? extends Pawn>> kontors) {
        if (kontors == null) {
            throw new IllegalArgumentException();
        }
        this.additionalKontors = Lists.newArrayList();

        this.drawer = new HansaCityDrawer(this);

        this.cityClickableArea = new HansaCityClickableArea(this);

        this.position = position;
        this.power = power;
        this.kontors = kontors;

        this.routes = Lists.newArrayList();

    }

    @Override
    public void setRoute(IRoute route) {
        routes.add(route);
    }

    @Override
    public List<IRoute> getRoutes() {
        return Lists.newArrayList(routes);
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
        return Lists.newArrayList(kontors);
    }

    @Override
    public List<IKontor<? extends Pawn>> getAdditionalKontors() {
        return additionalKontors;
    }

    @Override
    public void pushAdditionalKontors(IKontor<? extends Pawn> kontor) {
        if (kontor == null || kontor.isEmpty()) {
            throw new IllegalArgumentException("additionnalKontor null or empty");
        }

        additionalKontors.add(kontor);
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
        return this.cityClickableArea;
    }


    @Override
    public IHTPlayer getOwner(){
        if (this.getKontors().size() == 0){
            return null;
        }
        IHTPlayer cityOwner = null;
        int kontorsOwnedMax = 0;
        Map<IHTPlayer,Integer> score = Maps.newHashMap();
        for (IKontor<? extends Pawn> kontor : getAdditionalKontors()){
            IHTPlayer kontorOwner = kontor.getOwner();
            Integer kontorsOwned = score.get(kontorOwner);
            if (kontorsOwned == null){
                kontorsOwned = 1;
                score.put(kontorOwner,kontorsOwned);
            } else {
                kontorsOwned++;
            }
            score.put(kontorOwner,kontorsOwned);
            if(kontorsOwned >= kontorsOwnedMax){
                cityOwner = kontorOwner;
                kontorsOwnedMax = kontorsOwned;
            }
        }
        for (IKontor<? extends Pawn> kontor : getKontors()){
            if(!kontor.isEmpty()) {
                IHTPlayer kontorOwner = kontor.getOwner();
                Integer kontorsOwned = score.get(kontorOwner);
                if (kontorsOwned == null) {
                    kontorsOwned = 1;
                    score.put(kontorOwner, kontorsOwned);
                } else {
                    kontorsOwned++;
                }
                score.put(kontorOwner, kontorsOwned);
                if (kontorsOwned >= kontorsOwnedMax) {
                    cityOwner = kontorOwner;
                    kontorsOwnedMax = kontorsOwned;
                }
            }
        }
        return cityOwner;
    }
}
