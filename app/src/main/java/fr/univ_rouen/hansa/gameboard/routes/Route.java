package fr.univ_rouen.hansa.gameboard.routes;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.view.IPosition;
import fr.univ_rouen.hansa.view.display.HansaRouteDrawer;
import fr.univ_rouen.hansa.view.display.IDrawer;


public class Route implements IRoute {

    private final IDrawer drawer;

    private final List<IVillage> villages;
    private final ICity[] cities;
    private final IPosition tavernPosition;

    private IBonusMarker bonusMarker;

    public Route(List<IVillage> villages, ICity[] cities, IPosition tavernPosition) {
        if (villages == null || cities == null || cities.length != 2) {
            throw new IllegalArgumentException();
        }

        this.drawer = new HansaRouteDrawer(this);

        this.villages = villages;
        this.cities = cities;
        this.tavernPosition = tavernPosition;

        for (ICity city : cities) {
            city.setRoute(this);
        }

        for (IVillage village : villages) {
            village.setRoute(this);
        }

        this.bonusMarker = null;
    }

    public Route(List<IVillage> villages, ICity[] cities, IPosition tavernPosition, IBonusMarker bonusMarker) {
        this(villages, cities, tavernPosition);

        this.bonusMarker = bonusMarker;
    }

    @Override
    public IPosition getTavernPosition() {
        return tavernPosition;
    }

    @Override
    public List<IVillage> getVillages() {
        return villages;
    }

    @Override
    public IVillage getVillage(int i) {
        if (i < 0 || i > villages.size()) {
            throw new IllegalArgumentException();
        }

        return villages.get(i);
    }

    @Override
    public ICity[] getCities() {
        return cities;
    }

    @Override
    public boolean isEmpty() {
        for (IVillage village : villages) {
            if (!village.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isTradeRoute() {
        IHTPlayer player = villages.get(0).getOwner();

        if (player != null) {
            for (IVillage village : villages) {
                if (village.getOwner() == null || !village.getOwner().equals(player)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean isTradeRoute(IHTPlayer player) {
        for (IVillage village : villages) {
            if (village.getOwner() == null || !village.getOwner().equals(player)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public IBonusMarker getBonusMarker() {
        return bonusMarker;
    }

    @Override
    public IBonusMarker popBonusMarker() {
        IBonusMarker tmp = bonusMarker;
        bonusMarker = null;
        return tmp;
    }

    @Override
    public void pushBonusMarker(IBonusMarker bonusMarker) {
        if (getBonusMarker() != null) {
            throw new IllegalStateException();
        }
        this.bonusMarker = bonusMarker;
    }

    @Override
    public boolean isYourCity(ICity city) {
        if (city == null) {
            throw new IllegalArgumentException();
        }

        return cities[0].equals(city) || cities[1].equals(city);
    }

    public List<Pawn> getPawns() {
        List<Pawn> l = Lists.newArrayList();
        for (IVillage v : villages) {
            if (!v.isEmpty()) {
                Pawn p = v.pullPawn();
                l.add(p);
                v.pushPawn(p);
            }
        }

        return l;
    }

    @Override
    public IDrawer getDrawer() {
        return this.drawer;
    }
}
