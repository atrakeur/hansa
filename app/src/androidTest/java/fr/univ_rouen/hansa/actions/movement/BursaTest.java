package fr.univ_rouen.hansa.actions.movement;

import junit.framework.TestCase;

import java.util.Arrays;

import fr.univ_rouen.hansa.exceptions.NotAvailableActionException;
import fr.univ_rouen.hansa.exceptions.NotEnoughSupplyException;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class BursaTest extends TestCase {

    private static final int SUPPLY_MER = 1;
    private static final int SUPPLY_TRA = 5;
    private static final int STOCK_MER = 0;
    private static final int STOCK_TRA = 6;


    private static final int MOVE_MER = 0;
    private static final int MOVE_TRA = 3;

    public void testMaxBursa() throws Exception {

        TurnManager manager = TurnManager.getInstance();
        assertNotNull(manager);

        manager.addPlayers(Arrays.asList(PlayerColor.values()));
        IHTPlayer player = manager.getCurrentPlayer();
        assertNotNull(player);

        Bursa move;

        player.getEscritoire().increasePower(Power.Bursa);
        player.getEscritoire().increasePower(Power.Bursa);
        player.getEscritoire().increasePower(Power.Bursa);

        move = new Bursa(player);
        move.doMovement();

        assertTrue(player.getEscritoire().getStock().getMerchantCount() == 0);
        assertTrue(player.getEscritoire().getStock().getTraderCount() == 0);

        move.doRollback();

        assertTrue(player.getEscritoire().getStock().getMerchantCount() == STOCK_MER);
        assertTrue(player.getEscritoire().getStock().getTraderCount() == STOCK_TRA);

        move = new Bursa(player, 0);
        move.doMovement();

        assertTrue(player.getEscritoire().getStock().getMerchantCount() == 0);
        assertTrue(player.getEscritoire().getStock().getTraderCount() == 0);

        move.doRollback();

        assertTrue(player.getEscritoire().getStock().getMerchantCount() == STOCK_MER);
        assertTrue(player.getEscritoire().getStock().getTraderCount() == STOCK_TRA);
    }

    public void testMovement() throws Exception {

        TurnManager manager = TurnManager.getInstance();
        assertNotNull(manager);

        manager.addPlayers(Arrays.asList(PlayerColor.values()));
        IHTPlayer player = manager.getCurrentPlayer();
        assertNotNull(player);

        Bursa move;

        try {
            move = new Bursa(null, 0);
            throw new Exception("Invalid Affectation not catch.");
        } catch (IllegalArgumentException e) {
        }

        try {
            move = new Bursa(player, -1);
            throw new Exception("Invalid Affectation not catch.");
        } catch (IllegalArgumentException e) {
        }

        try {
            move = new Bursa(player);
            throw new Exception("Invalid Affectation not catch.");
        } catch (NotAvailableActionException e) {
        }

        try {
            move = new Bursa(player, 100);
            throw new Exception("Invalid Affectation not catch.");
        } catch (NotEnoughSupplyException e) {
        }

        try {
            move = new Bursa(player, 4);
            throw new Exception("Invalid Affectation not catch.");
        } catch (NotEnoughSupplyException e) {
        }

        move = new Bursa(player, MOVE_MER);

        assertNotNull(move);
        assertFalse(move.isDone());

        try {
            move.doRollback();
        } catch (IllegalStateException ex) {
        }

        move.doMovement();
        assertTrue(move.isDone());


        assertTrue(player.getEscritoire().getSupply().getMerchantCount() == (SUPPLY_MER + MOVE_MER));
        assertTrue(player.getEscritoire().getSupply().getTraderCount() == (SUPPLY_TRA + MOVE_TRA));

        assertTrue(player.getEscritoire().getStock().getMerchantCount() == (STOCK_MER - MOVE_MER));
        assertTrue(player.getEscritoire().getStock().getTraderCount() == (STOCK_TRA - MOVE_TRA));


        move.doRollback();

        assertFalse(move.isDone());


        assertTrue(player.getEscritoire().getSupply().getMerchantCount() == SUPPLY_MER);
        assertTrue(player.getEscritoire().getSupply().getTraderCount() == SUPPLY_TRA);

        assertTrue(player.getEscritoire().getStock().getMerchantCount() == STOCK_MER);
        assertTrue(player.getEscritoire().getStock().getTraderCount() == STOCK_TRA);

    }
}