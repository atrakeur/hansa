package fr.univ_rouen.hansa.save;

import com.google.common.collect.Lists;

import junit.framework.TestCase;

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

        saveManager = SaveManager.getInstance();
        gameBoard = GameBoardFactory.getInstance().createGameBoard(1);

        List<PlayerColor> players = Lists.newArrayList(PlayerColor.green, PlayerColor.red);

        TurnManager.getInstance().addPlayers(players);
    }

    public void testQuickSave() throws Exception {
        //TODO
    }

    public void testSave() throws Exception {
        boolean saveWork = saveManager.save(gameBoard, "test");
        //TODO
    }
}