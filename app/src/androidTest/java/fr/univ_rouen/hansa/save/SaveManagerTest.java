package fr.univ_rouen.hansa.save;

import com.google.common.collect.Lists;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class SaveManagerTest extends TestCase {
    private SaveManager saveManager;
    private GameBoard gameBoard;

    @Override
    public void setUp() throws java.lang.Exception {
        super.setUp();

        saveManager = new SaveManager();
        gameBoard = GameBoardFactory.getInstance().createGameBoard(1);

        List<PlayerColor> players = Lists.newArrayList(PlayerColor.green, PlayerColor.red);

        TurnManager.getInstance().addPlayers(players);
    }

    public void testSave() throws Exception {
        assertTrue(saveManager.save(gameBoard));
    }

    public void testLoad() throws IOException {
        assertTrue(saveManager.load());
    }
}
