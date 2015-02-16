package fr.univ_rouen.hansa.gameboard.cities;

import junit.framework.TestCase;

import org.junit.Test;

import fr.univ_rouen.hansa.exceptions.NotEnoughSupplyException;
import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;

import static junit.framework.Assert.*;

public class KontorTest{
    private IHTPlayer player = new HTPlayer(PlayerColor.blue, 1);
    private IKontor<Pawn> kontor;

    public void testConsKontorClass() throws Exception{
        try{
              kontor = new Kontor(City.class,false, Privillegium.White);
        }catch (IllegalArgumentException e){}
    }

    public void testConsKontorPNull() throws Exception{
        try{
            kontor = new Kontor(Merchant.class,false, null);
            fail("Invalid Affectation");
        }catch (IllegalArgumentException e){}
    }

    public void testGetPawnClass() throws Exception {
        kontor = new Kontor(Trader.class,false, Privillegium.White);
        assertNotNull(kontor);
        assertTrue(kontor.getClass().equals(Trader.class) );
        assertFalse(kontor.getClass().equals(Merchant.class) );

        kontor = new Kontor(Merchant.class,true, Privillegium.White);
        assertNotNull(kontor);
        assertTrue(kontor.getClass().equals(Merchant.class) );
        assertFalse(kontor.getClass().equals(Trader.class));
    }

    public void testIsEmpty() throws Exception {
        kontor = new Kontor(Trader.class,false, Privillegium.White);
        assertNotNull(kontor);
        assertTrue("kontor  is empty", kontor.isEmpty());
        Trader p= new Trader(player);
        kontor.pushPawn(p);
        assertFalse("kontor is full", kontor.isEmpty());
        kontor.popPawn();
        assertTrue(kontor.isEmpty());
    }

    public void testPopPawn() throws Exception {

        Merchant p= new Merchant(player);
        kontor = new Kontor(Merchant.class,false, Privillegium.Black);
        assertNotNull(kontor);
        assertNull(kontor.popPawn());
        kontor.pushPawn(p);
        assertNotNull(kontor.popPawn());
        assertNull(kontor.popPawn());

    }

    public void testGetPrivillegium() throws Exception {

        kontor = new Kontor(Trader.class,false, Privillegium.Orange);
        assertNotNull(kontor);
        assertNotNull(kontor.getPrivillegium());
        assertTrue(Privillegium.Orange == kontor.getPrivillegium());
        for(Privillegium p: Privillegium.values()) {
            assertFalse(p == kontor.getPrivillegium());
        }
    }

    public void testPushPawnNull() throws Exception {
        kontor = new Kontor(Merchant.class,true, Privillegium.Pink);
        assertNotNull(kontor);
        try{
              kontor.pushPawn(null);
            fail("Invalid Affectation");
        }catch (IllegalArgumentException e){}

    }

    public void testPushPawn2() throws Exception{

        kontor = new Kontor(Merchant.class,false, Privillegium.Pink);
        assertNotNull(kontor);
        Merchant p = new Merchant(player);
        Merchant p1 = new Merchant(player);
        kontor.pushPawn(p);
        try{
            kontor.pushPawn(p1);
            fail("Invalid Affectation");
        }catch (IllegalStateException e){}
    }
}