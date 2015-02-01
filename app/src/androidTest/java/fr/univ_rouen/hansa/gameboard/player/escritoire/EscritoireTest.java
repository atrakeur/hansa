package fr.univ_rouen.hansa.gameboard.player.escritoire;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.pawns.Pawn;

public class EscritoireTest extends ApplicationTestCase<Application> {
    public EscritoireTest() {
        super(Application.class);
    }

    public void testClavisUrbis() throws Exception {
        Escritoire escritoire = new Escritoire(1);

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
        } catch (Exception e){
        }
    }

    public void testActiones() throws Exception {
        Escritoire escritoire = new Escritoire(1);

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
        } catch (Exception e){
        }
    }

    public void testPrivilegium() throws Exception {
        Escritoire escritoire = new Escritoire(1);

        assertEquals(escritoire.privilegiumLevel(), 1);

        escritoire.increasePower(Power.Privillegium);
        assertEquals(escritoire.privilegiumLevel(), 2);

        escritoire.increasePower(Power.Privillegium);
        assertEquals(escritoire.privilegiumLevel(), 3);

        escritoire.increasePower(Power.Privillegium);
        assertEquals(escritoire.privilegiumLevel(), 4);

        try {
            escritoire.increasePower(Power.Privillegium);
            fail("Missing exception");
        } catch (Exception e){
        }
    }

    public void testLiberSophia() throws Exception {
        Escritoire escritoire = new Escritoire(1);

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
        } catch (Exception e){
        }
    }

    public void testBursa() throws Exception {
        Escritoire escritoire = new Escritoire(1);

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
        } catch (Exception e){
        }
    }

    public void testStockAndSupply() throws Exception {
        Escritoire escritoire;
        List<Pawn> supplyPawns;

        escritoire = new Escritoire(1);
        assertTrue(escritoire.moveStockToSupply(6, 0));

        try {
            escritoire.moveStockToSupply(1, 0);
            fail("Missing exception");
        } catch (Exception e){
        }

        supplyPawns = escritoire.popFromSupply(11, 1);
        assertTrue(supplyPawns.size() == 12);

        escritoire = new Escritoire(5);
        assertTrue(escritoire.moveStockToSupply(2, 0));

        try {
            escritoire.moveStockToSupply(1, 0);
            fail("Missing exception");
        } catch (Exception e){
        }

        supplyPawns = escritoire.popFromSupply(11, 1);
        assertTrue(supplyPawns.size() == 12);
    }

    public void testDecrease() {
        Escritoire escritoire = new Escritoire(1);

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