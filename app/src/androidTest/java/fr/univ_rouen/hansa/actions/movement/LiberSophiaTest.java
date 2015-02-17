package fr.univ_rouen.hansa.actions.movement;

import junit.framework.TestCase;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.Village;
import fr.univ_rouen.hansa.view.Position;

public class LiberSophiaTest extends TestCase {

    public void testMovement() throws Exception {
        GameBoardFactory.getInstance().createGameBoard(1);

        IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();
        Village source = new Village(new Position(0, 0));
        Village destination = new Village(new Position(0, 0));

        LiberSophia liberSophia = new LiberSophia(player, source, destination);

        source.pushPawn(new Trader(TurnManager.getInstance().getCurrentPlayer()));

        assertFalse(liberSophia.isDone());

        liberSophia.doMovement();

        assertTrue("After movement, source need to be empty", source.isEmpty());
        assertFalse("After movement, destination need to not be empty", destination.isEmpty());

        assertTrue(liberSophia.isDone());

        liberSophia.doRollback();

        assertFalse("After rollback, source need to not be empty", source.isEmpty());
        assertTrue("After rollback, destination need to be empty", destination.isEmpty());

        assertFalse(liberSophia.isDone());
    }

}