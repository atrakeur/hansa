package fr.univ_rouen.hansa.gameboard.routes;

import java.util.ArrayList;
import java.util.List;

import fr.univ_rouen.hansa.exceptions.NotAvailableActionException;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;


public class Route implements IRoute {

    private List<IVillage> villages;
    private ICity[] cities;
    private IBonusMarker bonusMarker;

    public Route(List<IVillage> villages, ICity[] cities) {
        if (villages == null || cities == null || cities.length != 2) {
            throw new IllegalArgumentException();
        }

        this.villages = villages;
        this.cities = cities;
    }

    public Route(List<IVillage> villages, ICity[] cities, IBonusMarker bonusMarker) {
        this(villages, cities);

        this.bonusMarker = bonusMarker;
    }

    public List<IVillage> getVillages() {
        return villages;
    }

    public IVillage getVillage(int i) {
        if (i < 0 || i > villages.size()) {
            throw new IllegalArgumentException();
        }

        return villages.get(i);
    }

    public ICity[] getCities() {
        return cities;
    }

    public boolean isEmpty() {
        for (IVillage village : villages) {
            if (!village.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public boolean isTradeRoute() {
        IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();

        for (IVillage village : villages) {
            if (village.getOwner() == null || player.equals(village.getOwner())) {
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

        return cities[0].equals(city) || cities[1].equals(city);
    }
}
