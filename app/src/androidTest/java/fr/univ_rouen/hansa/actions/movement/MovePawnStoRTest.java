package fr.univ_rouen.hansa.actions.movement;

import junit.framework.TestCase;

import java.util.Arrays;

import fr.univ_rouen.hansa.exceptions.NotAvailableActionException;
import fr.univ_rouen.hansa.exceptions.NotEnoughSupplyException;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class MovePawnStoRTest extends TestCase {

    public void testMovement() throws Exception{

        TurnManager manager = TurnManager.getInstance();
        assertNotNull(manager);

        manager.addPlayers(Arrays.asList(PlayerColor.values()));
        IHTPlayer player=manager.getCurrentPlayer();
        assertNotNull(player);

        MovePawnStoR move;

        try{
            move = new MovePawnStoR(null, 0, 0);
            throw new Exception("Invalid Affectation not catch.");
        }catch (IllegalArgumentException e){}

        try{
            move = new MovePawnStoR(player, -1, 0);
            throw new Exception("Invalid Affectation not catch.");
        }catch (IllegalArgumentException e){}

        try{
            move = new MovePawnStoR(player, 0, -1);
            throw new Exception("Invalid Affectation not catch.");
        }catch (IllegalArgumentException e){}

        try{
            move = new MovePawnStoR(player, 100, 100);
            throw new Exception("Invalid Affectation not catch.");
        }catch (NotEnoughSupplyException e){}

        try{
            move = new MovePawnStoR(player, 4, 0);
            throw new Exception("Invalid Affectation not catch.");
        }catch (NotAvailableActionException e){}

        move = new MovePawnStoR(player, 3, 0);

        assertNotNull(move);
        assertFalse(move.isDone());

        try{
            move.doRollback();
        }catch(IllegalStateException ex){}

        move.doMovement();

        assertTrue(move.isDone());

        move.doRollback();

        assertFalse(move.isDone());
    }
}