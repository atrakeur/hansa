package fr.univ_rouen.hansa.actions.movement;

import junit.framework.TestCase;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class KeepKontorTest extends TestCase {
    private IHTPlayer player;
    private ICity city;
    private IRoute route;

    @Override
    public void setUp() throws java.lang.Exception {
        super.setUp();

        GameBoard gameBoard = GameBoardFactory.getInstance().createGameBoard(1);

        player = TurnManager.getInstance().getCurrentPlayer();

        List<ICity> cities = gameBoard.getCities();

        for (ICity city : cities) {
            if (city.getPower() == Power.LiberSophiae) {
                this.city = city;
                break;
            }
        }

        if (city == null) {
            throw new IllegalStateException();
        }

        route = city.getRoutes().get(0);
    }

    public void testMovement() throws Exception {
        for (IVillage village : route.getVillages()) {
            new MovePawnRtoGB(player, village, Trader.class).doMovement();
        }

        KeepKontor action = new KeepKontor(player, city, route.getVillage(0));
        
        IKontor kontor = city.getNextKontor();

        assertFalse(action.isDone());

        int stockState = player.getEscritoire().getStock().getMerchantCount()
                       + player.getEscritoire().getStock().getTraderCount();


        // ------ Movement ------ //
        
        action.doMovement();

        for (IVillage village : route.getVillages()) {
            assertTrue(village.isEmpty());
        }
        
        assertFalse(kontor.isEmpty());

        int stockDone = player.getEscritoire().getStock().getMerchantCount()
                      + player.getEscritoire().getStock().getTraderCount();

        assertTrue(stockDone == stockState + route.getVillages().size() - 1);


        // ------ Rollback ------ //


        action.doRollback();

        for (IVillage village : route.getVillages()) {
            assertFalse(village.isEmpty());
        }

        assertTrue(kontor.isEmpty());

        int stockRB = player.getEscritoire().getStock().getMerchantCount()
                    + player.getEscritoire().getStock().getTraderCount();

        assertTrue(stockRB == stockState);


        // ------ Movement forbidden ------ //


        //Create a fake pawn for test
        kontor.pushPawn(new Trader(player));

        try {
            action.doMovement();
            fail("if the player don't have privillegium, need to throw an exception");
        } catch (Exception ignored) {
        }
    }
    
}
