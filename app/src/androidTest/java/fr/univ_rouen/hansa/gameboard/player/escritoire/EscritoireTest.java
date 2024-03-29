package fr.univ_rouen.hansa.gameboard.player.escritoire;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;

public class EscritoireTest extends ApplicationTestCase<Application> {
    public EscritoireTest() {
        super(Application.class);
    }

    public void testClavisUrbis() throws Exception {
        IHTPlayer p = new HTPlayer(PlayerColor.green, 1);
        Escritoire escritoire = new Escritoire(p, 1);

        assertEquals(escritoire.clavisUrbisLevel(), 1);

        escritoire.increasePower(Power.ClavisUrbis);
        assertEquals(escritoire.clavisUrbisLevel(), 2);

        escritoire.increasePower(Power.ClavisUrbis);
        assertEquals(escritoire.clavisUrbisLevel(), 2);

        escritoire.increasePower(Power.ClavisUrbis);
        assertEquals(escritoire.clavisUrbisLevel(), 3);

        escritoire.increasePower(Power.ClavisUrbis);
        assertEquals(escritoire.clavisUrbisLevel(), 4);

        try {
            escritoire.increasePower(Power.ClavisUrbis);
            fail("Missing exception");
        } catch (Exception ignored){
        }
    }

    public void testActiones() throws Exception {
        IHTPlayer p = new HTPlayer(PlayerColor.green, 1);
        Escritoire escritoire = new Escritoire(p, 1);

        assertEquals(escritoire.actionesLevel(), 2);

        escritoire.increasePower(Power.Actiones);
        assertEquals(escritoire.actionesLevel(), 3);

        escritoire.increasePower(Power.Actiones);
        assertEquals(escritoire.actionesLevel(), 3);

        escritoire.increasePower(Power.Actiones);
        assertEquals(escritoire.actionesLevel(), 4);

        escritoire.increasePower(Power.Actiones);
        assertEquals(escritoire.actionesLevel(), 4);

        escritoire.increasePower(Power.Actiones);
        assertEquals(escritoire.actionesLevel(), 5);

        try {
            escritoire.increasePower(Power.Actiones);
            fail("Missing exception");
        } catch (Exception ignored){
        }
    }

    public void testPrivilegium() throws Exception {
        IHTPlayer p = new HTPlayer(PlayerColor.green, 1);
        Escritoire escritoire = new Escritoire(p, 1);

        assertEquals(escritoire.privilegiumLevel(), Privillegium.White);

        escritoire.increasePower(Power.Privillegium);
        assertEquals(escritoire.privilegiumLevel(), Privillegium.Orange);

        escritoire.increasePower(Power.Privillegium);
        assertEquals(escritoire.privilegiumLevel(), Privillegium.Pink);

        escritoire.increasePower(Power.Privillegium);
        assertEquals(escritoire.privilegiumLevel(), Privillegium.Black);

        try {
            escritoire.increasePower(Power.Privillegium);
            fail("Missing exception");
        } catch (Exception ignored){
        }
    }

    public void testLiberSophia() throws Exception {
        IHTPlayer p = new HTPlayer(PlayerColor.green, 1);
        Escritoire escritoire = new Escritoire(p, 1);

        assertEquals(escritoire.liberSophiaLevel(), 2);

        escritoire.increasePower(Power.LiberSophiae);
        assertEquals(escritoire.liberSophiaLevel(), 3);

        escritoire.increasePower(Power.LiberSophiae);
        assertEquals(escritoire.liberSophiaLevel(), 4);

        escritoire.increasePower(Power.LiberSophiae);
        assertEquals(escritoire.liberSophiaLevel(), 5);

        try {
            escritoire.increasePower(Power.LiberSophiae);
            fail("Missing exception");
        } catch (Exception ignored){
        }
    }

    public void testBursa() throws Exception {
        IHTPlayer p = new HTPlayer(PlayerColor.green, 1);
        Escritoire escritoire = new Escritoire(p, 1);

        assertEquals(escritoire.bursaLevel(), 3);

        escritoire.increasePower(Power.Bursa);
        assertEquals(escritoire.bursaLevel(), 5);

        escritoire.increasePower(Power.Bursa);
        assertEquals(escritoire.bursaLevel(), 7);

        escritoire.increasePower(Power.Bursa);
        assertEquals(escritoire.bursaLevel(), Integer.MAX_VALUE);

        try {
            escritoire.increasePower(Power.Bursa);
            fail("Missing exception");
        } catch (Exception ignored){
        }
    }

    public void testStockAndSupply() throws Exception {
        Escritoire escritoire;
        List<Pawn> supplyPawns;

        IHTPlayer p = new HTPlayer(PlayerColor.green, 1);
        escritoire = new Escritoire(p, 1);
        assertTrue(escritoire.moveStockToSupply(0, 6));

        try {
            escritoire.moveStockToSupply(0, 1);
            fail("Missing exception");
        } catch (Exception ignored){
        }

        supplyPawns = escritoire.popFromSupply(1, 11);
        assertTrue(supplyPawns.size() == 12);

        IHTPlayer p2 = new HTPlayer(PlayerColor.red, 5);
        escritoire = new Escritoire(p2, 5);
        assertTrue(escritoire.moveStockToSupply(0, 2));

        try {
            escritoire.moveStockToSupply(0, 1);
            fail("Missing exception");
        } catch (Exception ignored){
        }

        supplyPawns = escritoire.popFromSupply(1, 11);
        assertTrue(supplyPawns.size() == 12);
    }

    public void testDecrease() {
        IHTPlayer p = new HTPlayer(PlayerColor.green, 1);
        Escritoire escritoire = new Escritoire(p, 1);

        escritoire.increasePower(Power.Bursa);
        escritoire.increasePower(Power.Bursa);
        escritoire.increasePower(Power.Bursa);

        assertEquals(escritoire.bursaLevel(), Integer.MAX_VALUE);

        escritoire.decreasePower(Power.Bursa);
        assertEquals(escritoire.bursaLevel(), 7);

        escritoire.decreasePower(Power.Bursa);
        assertEquals(escritoire.bursaLevel(), 5);

        escritoire.decreasePower(Power.Bursa);
        assertEquals(escritoire.bursaLevel(), 3);
    }
}