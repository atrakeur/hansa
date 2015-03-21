package fr.univ_rouen.hansa.save;

import android.util.Log;

import junit.framework.TestCase;

import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;

public class SaveManagerTest extends TestCase {
    private SaveManager saveManager;
    private GameBoard gameBoard;

    @Override
    public void setUp() throws java.lang.Exception {
        super.setUp();

        saveManager = SaveManager.getInstance();
        gameBoard = GameBoardFactory.getInstance().createGameBoard(1);
    }

    public void testQuickSave() throws Exception {
        //TODO
    }

    public void testSave() throws Exception {
        boolean saveWork = saveManager.save(gameBoard, "test");
        //TODO
    }
}