package fr.univ_rouen.hansa.gameboard.cities;

import com.google.common.collect.Lists;

import junit.framework.TestCase;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.util.CityPositions;
import fr.univ_rouen.hansa.view.display.HansaCityDrawer;

public class CityTest extends TestCase {
    private IHTPlayer player;
    private List<IKontor<? extends Pawn>> kontors;
    private ICity city;

    private void initialize() {
        player = new HTPlayer(PlayerColor.green, 2);
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(Trader.class, true, Privillegium.White));
        kontors.add(new Kontor<Trader>(Trader.class, false, Privillegium.Orange));
        kontors.add(new Kontor<Trader>(Trader.class, false, Privillegium.Pink));
    }

    public void testConsCity() throws Exception {
        try {
            city = new City(CityPositions.STENDAL, Power.Null, null);
            fail("Invalid Affectation");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testGetKontorInf() throws Exception {
        initialize();
        city = new City(CityPositions.STENDAL, Power.Null, kontors);
        try {
            city.getKontor(-1);
            fail("Invalid Affectation");
        } catch (IllegalArgumentException e) {
        }

    }

    public void testGetKontorSup() throws Exception {
        initialize();
        city = new City(CityPositions.STENDAL, Power.Null, kontors);
        try {
            city.getKontor(city.getKontors().size() + 1);
            fail("Invalid Affectation");
        } catch (IllegalArgumentException e) {
        }

    }

    public void testGetKontor() throws Exception {
        initialize();
        city = new City(CityPositions.STENDAL, Power.Null, kontors);
        assertNotNull("The kontor 0 can't be null", city.getKontor(0));
        assertEquals("The kontor returned by city.getKontor(0)is the same at the beginning add kontor 0", kontors.get(0), city.getKontor(0));
        assertNotNull(city.getKontor(1));
        assertEquals(kontors.get(1), city.getKontor(1));
        assertNotNull(city.getKontor(2));
        assertEquals(kontors.get(2), city.getKontor(2));
    }

    public void testGetPosition() throws Exception {

        initialize();
        city = new City(CityPositions.LUBECK, Power.Bursa, kontors);
        assertNotNull(city.getPosition());
        assertTrue(CityPositions.LUBECK == city.getPosition());

    }

    public void testGetKontors() throws Exception {

        initialize();
        city = new City(CityPositions.OSNABRUCK, Power.Null, kontors);

        assertNotNull(city.getKontors());
        assertEquals(kontors, city.getKontors());
        assertFalse(city.getKontors().isEmpty());
        assertTrue(city.getKontors().size() == 3);
    }

    public void testGetPower() throws Exception {
        initialize();
        city = new City(CityPositions.STADE, Power.Privillegium, kontors);
        assertNotNull(city.getPower());
        assertTrue(Power.Privillegium == city.getPower());


    }

    public void testIsCompletedCity() throws Exception {
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(Trader.class, false, Privillegium.White));
        kontors.add(new Kontor<Trader>(Trader.class, false, Privillegium.Orange));
        city = new City(CityPositions.HALLE, Power.ClavisUrbis, kontors);
        assertFalse(city.isCompletedCity());
        Pawn t = new Trader(player);
        Pawn t1 = new Trader(player);

        assertNotNull(city.getKontors());
        assertNotNull(city.getKontor(0));
        city.getKontor(0).pushPawn(t);
        assertFalse(city.isCompletedCity());
        assertNotNull(city.getKontor(1));
        city.getKontor(1).pushPawn(t1);
        assertTrue(city.isCompletedCity());

    }

    public void testGetDrawer() throws Exception {
        initialize();
        city = new City(CityPositions.BREMEN, Power.Null, kontors);
        assertNotNull(city.getDrawer());
        assertTrue(city.getDrawer().getClass().equals(HansaCityDrawer.class));

    }
}