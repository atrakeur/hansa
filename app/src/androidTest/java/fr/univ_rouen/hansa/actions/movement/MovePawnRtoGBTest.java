package fr.univ_rouen.hansa.actions.movement;

import junit.framework.TestCase;

import fr.univ_rouen.hansa.exceptions.NoPlaceException;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

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
        player2 = new HTPlayer(PlayerColor.red, 1);

        village = gameBoard.getRoutes().get(0).getVillage(0);
    }

    public void testMovement() throws Exception {
        int merchantInHand = player1.getEscritoire().getSupply().getMerchantCount();
        MovePawnRtoGB action1 = new MovePawnRtoGB(player1, village, Merchant.class);

        assertTrue(village.isEmpty());

        action1.doMovement();

        assertFalse(village.isEmpty());

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
}
