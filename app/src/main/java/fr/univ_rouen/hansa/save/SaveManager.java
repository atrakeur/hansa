package fr.univ_rouen.hansa.save;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.save.dao.HansaDao;

public class SaveManager {
    public static final String FILE_NAME = "hansa_save.json";

    private static SaveManager ourInstance = new SaveManager();

    private final Gson gson;

    public static SaveManager getInstance() {
        return ourInstance;
    }

    private SaveManager() {
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
        File file = new File("hansa_save.json");
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
}
