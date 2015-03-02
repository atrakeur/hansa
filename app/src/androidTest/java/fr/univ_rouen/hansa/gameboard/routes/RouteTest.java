package fr.univ_rouen.hansa.gameboard.routes;

import com.google.common.collect.Lists;

import junit.framework.TestCase;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusActiones;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.City;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.Kontor;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.util.CityPositions;
import fr.univ_rouen.hansa.util.TavernPositions;
import fr.univ_rouen.hansa.util.VillagePositions;
import fr.univ_rouen.hansa.view.display.HansaRouteDrawer;

public class RouteTest extends TestCase {
    private IHTPlayer player = new HTPlayer(PlayerColor.yellow, 5);
    private IRoute route;
    private List<IVillage> villages;
    private ICity[] cities;
    private List<IKontor<? extends Pawn>> kontors;

    private void initialize() {
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.GRONINGEN_EMDEN_1));
        villages.add(new Village(VillagePositions.GRONINGEN_EMDEN_2));
        villages.add(new Village(VillagePositions.GRONINGEN_EMDEN_3));
        kontors = Lists.newArrayList();
        kontors.add(new Kontor(Merchant.class, false, Privillegium.White));
        kontors.add(new Kontor(Trader.class, false, Privillegium.Pink));
        ICity c1 = new City(CityPositions.EMDEN, Power.Null, kontors);
        kontors = Lists.newArrayList();
        kontors.add(new Kontor(Trader.class, true, Privillegium.White));
        kontors.add(new Kontor(Merchant.class, false, Privillegium.Orange));
        ICity c2 = new City(CityPositions.GRONINGEN, Power.LiberSophiae, kontors);
        cities = new ICity[]{c1, c2};

    }

    public void testConsRoute1() throws Exception {
        try {
            route = new Route(null, null, TavernPositions.GRONINGEN_EMDEN);
            fail("Invalid Affectation");

        } catch (IllegalArgumentException e) {
        }
    }

    public void testConsRoute2() throws Exception {

        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.HAMBURG_LUNEBURG_1));
        villages.add(new Village(VillagePositions.HAMBURG_LUNEBURG_2));
        kontors = Lists.newArrayList();
        kontors.add(new Kontor(Trader.class, true, Privillegium.White));
        kontors.add(new Kontor(Trader.class, false, Privillegium.Orange));
        ICity c = new City(CityPositions.HALLE, Power.ClavisUrbis, kontors);
        cities = new ICity[]{c};
        try {
            route = new Route(villages, cities, TavernPositions.GRONINGEN_EMDEN);
            fail("Invalid Affectation");

        } catch (IllegalArgumentException e) {
        }

    }

    public void testConsRoute3() throws Exception {

        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.GRONINGEN_EMDEN_1));
        villages.add(new Village(VillagePositions.GRONINGEN_EMDEN_2));
        villages.add(new Village(VillagePositions.GRONINGEN_EMDEN_3));
        kontors = Lists.newArrayList();
        kontors.add(new Kontor(Trader.class, false, Privillegium.White));
        kontors.add(new Kontor(Trader.class, false, Privillegium.Orange));

        ICity c1 = new City(CityPositions.HALLE, Power.ClavisUrbis, kontors);
        kontors = Lists.newArrayList();
        kontors.add(new Kontor(Merchant.class, false, Privillegium.White));
        kontors.add(new Kontor(Trader.class, false, Privillegium.Pink));
        ICity c2 = new City(CityPositions.EMDEN, Power.Null, kontors);
        kontors = Lists.newArrayList();
        kontors.add(new Kontor(Trader.class, true, Privillegium.White));
        kontors.add(new Kontor(Merchant.class, false, Privillegium.Orange));
        ICity c3 = new City(CityPositions.GRONINGEN, Power.LiberSophiae, kontors);
        cities = new ICity[]{c1, c2, c3};
        try {
            route = new Route(villages, cities, TavernPositions.GRONINGEN_EMDEN);
            fail("Invalid Affectation");

        } catch (IllegalArgumentException e) {
        }

    }

    public void testGetTavernPosition() throws Exception {
        initialize();
        route = new Route(villages, cities, TavernPositions.GRONINGEN_EMDEN);
        assertNotNull(route.getTavernPosition());
        assertTrue(TavernPositions.GRONINGEN_EMDEN == route.getTavernPosition());
    }

    public void testGetVillages() throws Exception {
        initialize();
        route = new Route(villages, cities, TavernPositions.GRONINGEN_EMDEN);
        assertNotNull(route.getVillages());
        assertTrue(villages == route.getVillages());

    }

    public void testGetVillage() throws Exception {
        initialize();
        route = new Route(villages, cities, TavernPositions.GRONINGEN_EMDEN);
        assertNotNull(route.getVillage(0));
        int i = 0;
        for (IVillage v : villages) {
            assertTrue(v == route.getVillage(i));
            assertNotNull(route.getVillage(i));
            ++i;
        }

    }

    public void testGetVillageInf() throws Exception {
        initialize();
        route = new Route(villages, cities, TavernPositions.GRONINGEN_EMDEN);
        try {
            route.getVillage(-1);
            fail("Invalid Affectation");

        } catch (IllegalArgumentException e) {
        }

    }

    public void testGetVillageSup() throws Exception {
        initialize();
        route = new Route(villages, cities, TavernPositions.GRONINGEN_EMDEN);
        try {
            route.getVillage(route.getVillages().size() + 1);
            fail("Invalid Affectation");

        } catch (IllegalArgumentException e) {
        }

    }

    public void testGetCities() throws Exception {
        initialize();
        route = new Route(villages, cities, TavernPositions.GRONINGEN_EMDEN);
        assertNotNull(route.getCities());
        assertTrue(cities == route.getCities());
        assertFalse(route.getCities() == null);
    }

    public void testIsEmpty() throws Exception {
        initialize();
        route = new Route(villages, cities, TavernPositions.GRONINGEN_EMDEN);
        assertNotNull(route.getVillages());
        assertTrue(route.isEmpty());
        Pawn p = new Trader(player);
        route.getVillage(0).pushPawn(p);
        assertFalse(route.isEmpty());
        route.getVillage(0).pullPawn();
        assertTrue(route.isEmpty());

    }

    public void testIsTradeRoute() throws Exception {
        initialize();
        route = new Route(villages, cities, TavernPositions.GRONINGEN_EMDEN);
        assertNotNull(route.getVillages());
        Pawn p = new Trader(player);
        Pawn p1 = new Trader(player);
        Pawn p2 = new Merchant(player);
        route.getVillage(0).pushPawn(p);
        route.getVillage(1).pushPawn(p1);
        route.getVillage(2).pushPawn(p2);
        assertTrue(route.isTradeRoute());
        route.getVillage(2).pullPawn();
        assertFalse(route.isTradeRoute());

    }

    public void testGetBonusMarker() throws Exception {
        initialize();
        IBonusMarker bm = new BonusActiones();
        route = new Route(villages, cities, TavernPositions.GRONINGEN_EMDEN, bm);
        assertNotNull(route.getBonusMarker());
        assertTrue(bm == route.getBonusMarker());
        assertTrue(route.getBonusMarker().getClass() == BonusActiones.class);
    }

    public void testIsYourCityNull() throws Exception {
        initialize();
        route = new Route(villages, cities, TavernPositions.GRONINGEN_EMDEN);
        try {
            route.isYourCity(null);
            fail("Invalid Affectation");

        } catch (IllegalArgumentException e) {
        }
    }

    public void testIsYourCity() throws Exception {
        initialize();
        route = new Route(villages, cities, TavernPositions.GRONINGEN_EMDEN);
        assertTrue(route.isYourCity(cities[0]));
        assertTrue(route.isYourCity(cities[1]));

        kontors = Lists.newArrayList();
        kontors.add(new Kontor(Merchant.class, false, Privillegium.White));
        kontors.add(new Kontor(Trader.class, false, Privillegium.Pink));
        ICity c = new City(CityPositions.BREMEN, Power.Null, kontors);
        assertFalse(route.isYourCity(c));
    }

    public void testGetDrawer() throws Exception {
        initialize();
        route = new Route(villages, cities, TavernPositions.GRONINGEN_EMDEN);
        assertNotNull(route.getDrawer());
        assertTrue(route.getDrawer().getClass().equals(HansaRouteDrawer.class));
    }
}