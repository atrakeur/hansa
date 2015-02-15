package fr.univ_rouen.hansa.actions.movement;

import junit.framework.TestCase;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class IncreasePowerTest extends TestCase {
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
        IncreasePower action = new IncreasePower(player, city, route);

        int liberSophia = player.getEscritoire().liberSophiaLevel();

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

        int supplyState = player.getEscritoire().getSupply().getMerchantCount()
                + player.getEscritoire().getSupply().getTraderCount();


        // ------ Movement ------ //


        action.doMovement();

        for (IVillage village : route.getVillages()) {
            assertTrue(village.isEmpty());
        }

        assertTrue(player.getEscritoire().liberSophiaLevel() == liberSophia + 1);

        int stockDone = player.getEscritoire().getStock().getMerchantCount()
                      + player.getEscritoire().getStock().getTraderCount();

        int supplyDone = player.getEscritoire().getSupply().getMerchantCount()
                       + player.getEscritoire().getSupply().getTraderCount();

        assertTrue(stockDone == stockState + route.getVillages().size());
        assertTrue(supplyDone == supplyState + 1);


        // ------ Rollback ------ //


        action.doRollback();

        for (IVillage village : route.getVillages()) {
            assertFalse(village.isEmpty());
        }

        assertTrue(player.getEscritoire().liberSophiaLevel() == liberSophia);

        int stockRB = player.getEscritoire().getStock().getMerchantCount()
                    + player.getEscritoire().getStock().getTraderCount();

        int supplyRB = player.getEscritoire().getSupply().getMerchantCount()
                     + player.getEscritoire().getSupply().getTraderCount();

        assertTrue(stockRB == stockState);
        assertTrue(supplyRB == supplyState);
    }

}