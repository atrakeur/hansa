package fr.univ_rouen.hansa.gameboard.player.escritoire;

import android.app.Application;
import android.test.ApplicationTestCase;

import junit.framework.TestCase;

public class EscritoireTest extends ApplicationTestCase<Application> {
    //TODO Pas de test sur les BonusMarker
    //TODO Pas de test sur stock et reserve
    public EscritoireTest() {
        super(Application.class);
    }

    public void testClavisUrbis() throws Exception {
        Escritoire escritoire = new Escritoire();

        assertEquals(escritoire.clavisUrbisLevel(), 1);

        escritoire.increaseClavisUrbis();
        assertEquals(escritoire.clavisUrbisLevel(), 2);

        escritoire.increaseClavisUrbis();
        assertEquals(escritoire.clavisUrbisLevel(), 2);

        escritoire.increaseClavisUrbis();
        assertEquals(escritoire.clavisUrbisLevel(), 3);

        escritoire.increaseClavisUrbis();
        assertEquals(escritoire.clavisUrbisLevel(), 4);

        try {
            escritoire.increaseClavisUrbis();
            fail("Missing exception");
        } catch (Exception e){
        }
    }

    public void testActiones() throws Exception {
        Escritoire escritoire = new Escritoire();

        assertEquals(escritoire.actionesLevel(), 2);

        escritoire.increaseActiones();
        assertEquals(escritoire.actionesLevel(), 3);

        escritoire.increaseActiones();
        assertEquals(escritoire.actionesLevel(), 3);

        escritoire.increaseActiones();
        assertEquals(escritoire.actionesLevel(), 4);

        escritoire.increaseActiones();
        assertEquals(escritoire.actionesLevel(), 4);

        escritoire.increaseActiones();
        assertEquals(escritoire.actionesLevel(), 5);

        try {
            escritoire.increaseActiones();
            fail("Missing exception");
        } catch (Exception e){
        }
    }

    public void testPrivilegium() throws Exception {
        Escritoire escritoire = new Escritoire();

        assertEquals(escritoire.privilegiumLevel(), 1);

        escritoire.increasePrivilegium();
        assertEquals(escritoire.privilegiumLevel(), 2);

        escritoire.increasePrivilegium();
        assertEquals(escritoire.privilegiumLevel(), 3);

        escritoire.increasePrivilegium();
        assertEquals(escritoire.privilegiumLevel(), 4);

        try {
            escritoire.increasePrivilegium();
            fail("Missing exception");
        } catch (Exception e){
        }
    }

    public void testLiberSophia() throws Exception {
        Escritoire escritoire = new Escritoire();

        assertEquals(escritoire.liberSophiaLevel(), 2);

        escritoire.increaseLiberSophia();
        assertEquals(escritoire.liberSophiaLevel(), 3);

        escritoire.increaseLiberSophia();
        assertEquals(escritoire.liberSophiaLevel(), 4);

        escritoire.increaseLiberSophia();
        assertEquals(escritoire.liberSophiaLevel(), 5);

        try {
            escritoire.increaseLiberSophia();
            fail("Missing exception");
        } catch (Exception e){
        }
    }

    public void testBursa() throws Exception {
        Escritoire escritoire = new Escritoire();

        assertEquals(escritoire.bursaLevel(), 3);

        escritoire.increaseBursa();
        assertEquals(escritoire.bursaLevel(), 5);

        escritoire.increaseBursa();
        assertEquals(escritoire.bursaLevel(), 7);

        escritoire.increaseBursa();
        assertEquals(escritoire.bursaLevel(), Integer.MAX_VALUE);

        try {
            escritoire.increaseBursa();
            fail("Missing exception");
        } catch (Exception e){
        }
    }
}