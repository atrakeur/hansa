package fr.univ_rouen.hansa.actions.movement;

import junit.framework.TestCase;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.gameboard.routes.Village;
import fr.univ_rouen.hansa.util.VillagePositions;

public class ReplaceMovedPawnTest extends TestCase {
    private IHTPlayer player;

    @Override
    public void setUp() throws java.lang.Exception {
        GameBoardFactory.getInstance().createGameBoard(1);
        player = TurnManager.getInstance().getCurrentPlayer();
    }

    public void testEtape1() throws Exception {
        IVillage source = new Village(VillagePositions.GRONINGEN_EMDEN_1);
        IVillage destination = new Village(VillagePositions.ARNHEIM_DUISBURG_1);

        new MovePawnRtoGB(player, source, Trader.class).doMovement();

        ReplaceMovedPawn action = new ReplaceMovedPawn(player, source, destination);

        assertFalse(source.isEmpty());
        assertTrue(destination.isEmpty());

        action.doMovement();

        assertTrue(source.isEmpty());
        assertFalse(destination.isEmpty());

        action.doRollback();

        assertFalse(source.isEmpty());
        assertTrue(destination.isEmpty());
    }

    public void testEtape2() throws Exception {
        IVillage destination = new Village(VillagePositions.ARNHEIM_DUISBURG_1);
        Class<? extends Pawn> pawnType = Merchant.class;

        int merchantCount = player.getEscritoire().getSupply().getMerchantCount();

        ReplaceMovedPawn action = new ReplaceMovedPawn(player, destination, pawnType);

        assertTrue(destination.isEmpty());

        action.doMovement();

        assertFalse(destination.isEmpty());
        assertTrue(merchantCount == player.getEscritoire().getSupply().getMerchantCount() + 1);

        action.doRollback();

        assertTrue(destination.isEmpty());
        assertTrue(merchantCount == player.getEscritoire().getSupply().getMerchantCount());
    }
}