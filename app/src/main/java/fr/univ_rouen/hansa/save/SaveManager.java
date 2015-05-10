package fr.univ_rouen.hansa.save;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import fr.univ_rouen.hansa.activity.GameActivity;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.save.dao.HansaDao;

public class SaveManager {
    public static final String FILE_NAME = "hansa_save.json";

    private final Gson gson;

    public SaveManager() {
        gson = new Gson();
    }

    /**
     * Save the current state of the game with a specific name for the save
     *
     * @param gameBoard gameboard to save
     * @return true if the save has been correctly done, false else
     */
    public boolean save(GameBoard gameBoard) {
        HansaDao hansaDao = new HansaDao(gameBoard);
        String save = gson.toJson(hansaDao);

        //Si le fichier de sauvegarde existe pas, on le créé
        File file = new File(GameActivity.getInstance().getFilesDir(), FILE_NAME);
        if (file.exists()) {
            file.delete();
        }

        //On fait la sauvegarde
        FileOutputStream fos = null;
        try {
            fos = GameActivity.getInstance().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(save.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public GameBoard load() throws IOException {
        File file = new File(GameActivity.getInstance().getFilesDir(), FILE_NAME);
        GameBoard gameBoard = null;

        if (file.exists()) {
            String json = readFile();
            HansaDao hansaDao = gson.fromJson(json, HansaDao.class);

            TurnManager.getInstance().initFromSave(
                    hansaDao.getPlayersEntities(),
                    hansaDao.getCurrentPlayer()
            );

            gameBoard = hansaDao.getGameBoardEntity();

            GameBoardFactory.getInstance().createGameBoardFromSave(gameBoard);
        }

        return gameBoard;
    }

    private String readFile() throws IOException {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(GameActivity.getInstance().openFileInput(FILE_NAME)));
        String inputString;
        StringBuffer stringBuffer = new StringBuffer();

        while ((inputString = inputReader.readLine()) != null) {
            stringBuffer.append(inputString + "\n");
        }

        return stringBuffer.toString();
    }
}
