package fr.univ_rouen.hansa.actions.movement;

import junit.framework.TestCase;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class KeepRouteTest extends TestCase {
    private IHTPlayer player;
    private IRoute route;

    @Override
    public void setUp() throws java.lang.Exception {
        super.setUp();

        GameBoard gameBoard = GameBoardFactory.getInstance().createGameBoard(1);

        player = TurnManager.getInstance().getCurrentPlayer();
        route = gameBoard.getRoutes().get(0);
    }

    public void testMovement() throws Exception {
        KeepRoute action = new KeepRoute(player, route);

        try {
            action.doMovement();
            fail("need to throw an exception is the route is not taken by the player");
        } catch (Exception ignored) {
        }

        assertFalse(action.isDone());

        for (IVillage village : route.getVillages()) {
            new MovePawnRtoGB(player, village, Trader.class).doMovement();
        }

        int stockState = player.getEscritoire().getStock().getMerchantCount()
                       + player.getEscritoire().getStock().getTraderCount();


        // ------ Movement ------ //

        action.doMovement();

        for (IVillage village : route.getVillages()) {
            assertTrue(village.isEmpty());
        }

        int stockDone = player.getEscritoire().getStock().getMerchantCount()
                      + player.getEscritoire().getStock().getTraderCount();

        assertTrue(stockDone == stockState + route.getVillages().size());


        // ------ Rollback ------ //


        action.doRollback();

        for (IVillage village : route.getVillages()) {
            assertFalse(village.isEmpty());
        }

        int stockRB = player.getEscritoire().getStock().getMerchantCount()
                    + player.getEscritoire().getStock().getTraderCount();

        assertTrue(stockRB == stockState);
    }

}