package fr.univ_rouen.hansa.actions.movement;

import android.util.Log;

import com.google.common.collect.Lists;

import junit.framework.TestCase;

import java.util.List;

import fr.univ_rouen.hansa.exceptions.NoPlaceException;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.gameboard.routes.Village;

public class MovePawnRtoGBTest extends TestCase {
    private GameBoard gameBoard;
    private IHTPlayer player1;
    private IHTPlayer player2;
    private IVillage village;

    @Override
    public void setUp() throws java.lang.Exception {
        super.setUp();

        gameBoard = GameBoardFactory.getInstance().createGameBoard(1);

        player1 = new HTPlayer(PlayerColor.green, 1);
        player2 = new HTPlayer(PlayerColor.red, 2);

        village = gameBoard.getRoutes().get(0).getVillage(0);
    }

    public void testMovement() throws Exception {
        int merchantInHand = player1.getEscritoire().getSupply().getMerchantCount();
        assertEquals(merchantInHand, 1);
        MovePawnRtoGB action1 = new MovePawnRtoGB(player1, village, Merchant.class);

        assertTrue(village.isEmpty());

        action1.doMovement();

        assertTrue(village.getOwner().equals(player1));
        assertTrue(village.getPawnType().equals(Merchant.class));
        assertTrue(player1.getEscritoire().getSupply().getMerchantCount() == merchantInHand - 1);

        action1.doRollback();

        assertTrue(village.isEmpty());
        assertTrue(player1.getEscritoire().getSupply().getMerchantCount() == merchantInHand);

        action1.doMovement();

        assertFalse(village.isEmpty());

        MovePawnRtoGB action2 = new MovePawnRtoGB(player2, village, Trader.class);
        action2.doMovement();
        assertNotNull(action2.getPawnToReplace());
    }

    public void testReplacementPawnTrader() throws Exception {
        IVillage v = gameBoard.getRoutes().get(0).getVillage(1);
        v.pushPawn(new Trader(player2));
        assertTrue(!v.isEmpty());
        assertFalse(player1.getEscritoire().getSupply().getTraderCount() == 0);
        System.out.println(player1.getEscritoire().getSupply().getTraderCount());
         MovePawnRtoGB mp = new MovePawnRtoGB(player1,v,Trader.class);
        assertEquals(player1.getEscritoire().getSupply().getTraderCount(), 5);
        assertEquals(player1.getEscritoire().getSupply().getMerchantCount(), 1);
        mp.doMovement();
        assertTrue(!v.isEmpty());
        assertEquals(player1.getEscritoire().getSupply().getTraderCount(), 2);
        assertEquals(player1.getEscritoire().getSupply().getMerchantCount(), 1);

        mp.doRollback();

        assertEquals(player1.getEscritoire().getSupply().getTraderCount(), 5);
        assertEquals(player1.getEscritoire().getSupply().getMerchantCount(), 1);
    }

    public void testReplacementPawnMerchant() throws Exception {
        IVillage v = gameBoard.getRoutes().get(0).getVillage(1);
        v.pushPawn(new Merchant(player2));
        assertTrue(!v.isEmpty());
        assertFalse(player1.getEscritoire().getSupply().getTraderCount() == 0);

        //here village have a merchant ( -3 to replace )

        MovePawnRtoGB mp = new MovePawnRtoGB(player1,v,Trader.class);
        assertEquals(player1.getEscritoire().getSupply().getTraderCount(), 5);
        assertEquals(player1.getEscritoire().getSupply().getMerchantCount(), 1);
        mp.doMovement();
        assertTrue(!v.isEmpty());
        assertEquals(player1.getEscritoire().getSupply().getTraderCount(), 1);
        assertEquals(player1.getEscritoire().getSupply().getMerchantCount(), 1);

        mp.doRollback();

        assertEquals(player1.getEscritoire().getSupply().getTraderCount(), 5);
        assertEquals(player1.getEscritoire().getSupply().getMerchantCount(), 1);
    }
}
