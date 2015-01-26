package fr.univ_rouen.hansa.gameboard.routes;

import java.util.ArrayList;
import java.util.List;

import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.pawns.Pawn;


public class Route implements IRoute {

    private List<IVillage> villages;
    private List<ICity> cities;
    private IBonusMarker bonusMarker;

    public Route(List<IVillage> vs, List<ICity> cs, IBonusMarker b) {
        if (cs == null || vs == null) {
            throw new IllegalArgumentException();
        }
        villages = vs;
        cities = cs;
        bonusMarker = b;
    }

    public IVillage getVillage(int i) {
        if (i < 0 || i > villages.size()) {
            throw new IllegalArgumentException();
        }
        return villages.get(i);
    }

    public List<ICity> getCities() {
        return cities;
    }

    public boolean isTradeRoute() {
        for (IVillage v : villages) {
            if (v.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public IBonusMarker getBonusMarker() {
        return bonusMarker;
    }


    public boolean isYourCity(ICity city) {
        if (city == null) {
            throw new IllegalArgumentException();
        }
        return cities.contains(city);
    }

    public List<Pawn> getPawns() {
        List<Pawn> l = new ArrayList();
        for (IVillage v : villages) {
            if (!v.isEmpty()) {
                l.add(v.pushPawn());
            }
        }

        return l;
    }
}
