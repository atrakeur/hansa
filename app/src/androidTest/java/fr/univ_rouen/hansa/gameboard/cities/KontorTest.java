package fr.univ_rouen.hansa.gameboard.cities;

import junit.framework.TestCase;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;


public class KontorTest extends TestCase {
    private IHTPlayer player = new HTPlayer(PlayerColor.blue, 1);
    private IKontor<Trader> kontor;

    public void testGetPawnClass() throws Exception {
        kontor = new Kontor(Trader.class, false, Privillegium.White);
        assertEquals(Trader.class, kontor.getPawnClass());

        kontor = new Kontor(Merchant.class, true, Privillegium.White);
        assertTrue(kontor.getPawnClass().equals(Merchant.class));
    }

    public void testIsEmpty() throws Exception {
        kontor = new Kontor(Trader.class, false, Privillegium.White);
        assertTrue("kontor  is empty", kontor.isEmpty());
        Trader p = new Trader(player);
        kontor.pushPawn(p);
        assertFalse("kontor is full", kontor.isEmpty());
        kontor.popPawn();
        assertTrue(kontor.isEmpty());
    }

    public void testPopPawn() throws Exception {

        Trader p = new Trader(player);
        kontor = new Kontor(Trader.class, false, Privillegium.Black);
        assertNull(kontor.popPawn());
        kontor.pushPawn(p);
        assertNotNull(kontor.popPawn());
        assertNull(kontor.popPawn());

    }

    public void testGetPrivillegium() throws Exception {

        kontor = new Kontor(Trader.class, false, Privillegium.Orange);
        assertNotNull(kontor.getPrivillegium());
        assertTrue(Privillegium.Orange == kontor.getPrivillegium());
    }

    public void testPushPawnNull() throws Exception {
        kontor = new Kontor(Merchant.class, true, Privillegium.Pink);
        try {
            kontor.pushPawn(null);
            fail("Invalid Affectation");
        } catch (IllegalArgumentException e) {
        }

    }

    public void testPushPawn2() throws Exception {

        IKontor<Merchant> kontor = new Kontor(Merchant.class, false, Privillegium.Pink);
        Merchant p = new Merchant(player);
        Merchant p1 = new Merchant(player);
        kontor.pushPawn(p);
        try {
            kontor.pushPawn(p1);
            fail("Invalid Affectation");
        } catch (IllegalStateException e) {
        }
    }
}