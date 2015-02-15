package fr.univ_rouen.hansa.actions.movement;

import junit.framework.TestCase;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

public class MovePawnRtoSTest extends TestCase {
    private IHTPlayer player;

    @Override
    public void setUp() throws java.lang.Exception {
        super.setUp();

        GameBoardFactory.getInstance().createGameBoard(1);
        player = TurnManager.getInstance().getCurrentPlayer();
    }

    public void testMovement() throws Exception {
        int sMerchantCount = player.getEscritoire().getStock().getMerchantCount();
        int sTraderCount = player.getEscritoire().getStock().getTraderCount();

        int rMerchantCount = player.getEscritoire().getSupply().getMerchantCount();
        int rTraderCount = player.getEscritoire().getSupply().getTraderCount();

        MovePawnRtoS action = new MovePawnRtoS(player, rMerchantCount, rTraderCount);

        action.doMovement();

        assertTrue(player.getEscritoire().getStock().getMerchantCount() == sMerchantCount + rMerchantCount);
        assertTrue(player.getEscritoire().getStock().getTraderCount() == sTraderCount + rTraderCount);

        assertTrue(player.getEscritoire().getSupply().getMerchantCount() == 0);
        assertTrue(player.getEscritoire().getSupply().getTraderCount() == 0);

        try {
            new MovePawnRtoS(player, 0, 1).doMovement();
            fail("can't make the movement but didn't throw exception");
        } catch(Exception e) {
        }

        action.doRollback();

        assertTrue(player.getEscritoire().getStock().getMerchantCount() == sMerchantCount);
        assertTrue(player.getEscritoire().getStock().getTraderCount() == sTraderCount);

        assertTrue(player.getEscritoire().getSupply().getMerchantCount() == rMerchantCount);
        assertTrue(player.getEscritoire().getSupply().getTraderCount() == rTraderCount);
    }
}