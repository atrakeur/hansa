package fr.univ_rouen.hansa.save;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.save.gameboard.GameBoardDao;

public class SaveManager {
    private static SaveManager ourInstance = new SaveManager();

    private final Gson gson;

    public static SaveManager getInstance() {
        return ourInstance;
    }

    private SaveManager() {
        gson = new Gson();
    }

    /**
     * Quicksave for save the game when the user close the app
     *
     * @param gameBoard
     * @return true if the save has been correctly done, false else
     */
    public boolean quickSave(GameBoard gameBoard) {
        String date = new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());
        return this.save(gameBoard, "QuickSave : " + date);
    }

    /**
     * Save the current state of the game with a specific name for the save
     *
     * @param gameBoard gameboard to save
     * @param saveName name of the backup
     * @return true if the save has been correctly done, false else
     */
    public boolean save(GameBoard gameBoard, String saveName) {
        //TODO
        GameBoardDao gameBoardDao = new GameBoardDao(gameBoard);
        String save = gson.toJson(gameBoardDao);

        return false;
    }
}
