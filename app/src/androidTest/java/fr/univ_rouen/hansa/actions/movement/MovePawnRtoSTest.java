package fr.univ_rouen.hansa.actions.movement;

import junit.framework.TestCase;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class MovePawnRtoSTest extends TestCase {
    private IHTPlayer player;

    @Override
    public void setUp() throws java.lang.Exception {
        super.setUp();

        player = new HTPlayer(PlayerColor.green, 1);
    }

    public void testMovement() throws Exception {
        int sMerchantCount = player.getEscritoire().getSupply().getMerchantCount();
        int sTraderCount = player.getEscritoire().getSupply().getTraderCount();

        int rMerchantCount = player.getEscritoire().getStock().getMerchantCount();
        int rTraderCount = player.getEscritoire().getStock().getTraderCount();

        MovePawnRtoS action = new MovePawnRtoS(player, rMerchantCount, rTraderCount);

        action.doMovement();

        assertTrue(player.getEscritoire().getSupply().getMerchantCount() == sMerchantCount + rMerchantCount);
        assertTrue(player.getEscritoire().getSupply().getTraderCount() == sTraderCount + rTraderCount);

        assertTrue(player.getEscritoire().getStock().getMerchantCount() == 0);
        assertTrue(player.getEscritoire().getStock().getTraderCount() == 0);

        try {
            new MovePawnRtoS(player, 0, 1).doMovement();
            fail("can't make the movement but didn't throw exception");
        } catch(Exception ignored) {
        }

        action.doRollback();

        assertTrue(player.getEscritoire().getSupply().getMerchantCount() == sMerchantCount);
        assertTrue(player.getEscritoire().getSupply().getTraderCount() == sTraderCount);

        assertTrue(player.getEscritoire().getStock().getMerchantCount() == rMerchantCount);
        assertTrue(player.getEscritoire().getStock().getTraderCount() == rTraderCount);
    }
}