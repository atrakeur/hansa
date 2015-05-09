package fr.univ_rouen.hansa.save;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

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
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //On fait la sauvegarde
        PrintWriter outSave = null;

        try {
            outSave = new PrintWriter(FILE_NAME);
            outSave.println(save);
        } catch (FileNotFoundException ignore) {
        } finally {
            if (outSave != null) {
                outSave.close();
            }
        }

        return true;
    }

    public boolean load(Context context) throws IOException {
        File file = new File(context.getFilesDir(), FILE_NAME);

        if (file.exists()) {
            String json = readFile();
            HansaDao hansaDao = gson.fromJson(json, HansaDao.class);

            TurnManager.getInstance().initFromSave(
                    hansaDao.getPlayersEntities(),
                    hansaDao.getCurrentPlayer()
            );

            GameBoardFactory.getInstance().createGameBoardFromSave(hansaDao.getGameBoardEntity());
        }

        return true;
    }

    private String readFile() throws IOException {
        File file = new File(GameActivity.getInstance().getFilesDir(), FILE_NAME);

        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        char[] buf = new char[1024];
        int numRead = 0;

        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }

        reader.close();

        return fileData.toString();
    }
}
