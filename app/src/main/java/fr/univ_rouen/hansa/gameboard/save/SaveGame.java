package fr.univ_rouen.hansa.gameboard.save;


import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.univ_rouen.hansa.gameboard.board.GameBoard;

public class SaveGame implements Serializable {
    public static final int NB_SAVE_MAX = 5;
    public static final String FILE_NAME = "hansaFile";
    private static final long serialVersionUID = 5739345886717147020L;
    private List<Save> saves;

    private SaveGame() {
        saves = new ArrayList<Save>();

    }


    public static SaveGame getSaveGame(Context context) throws IOException {
        SaveGame saveGame;
        try {

            final FileInputStream fis = context.openFileInput(FILE_NAME);
            final ObjectInputStream is = new ObjectInputStream(fis);

            try {

                saveGame = (SaveGame) is.readObject();

            } catch (ClassNotFoundException e) {

                saveGame = new SaveGame();
            } finally {
                is.close();
                fis.close();
            }

        } catch (FileNotFoundException e1) {
            saveGame = new SaveGame();
        } catch (IOException e) {
            saveGame = new SaveGame();

        }
        if (saveGame != null) return saveGame;
        else
            return new SaveGame();

    }

    public List<Save> getSaves() {
        return saves;
    }

    public void remove(int save, Context context) throws IOException {
        if (save < 0 || save > saves.size()) {
            throw new IllegalArgumentException();
        }
        saves.remove(save);
        saveFile(context);
    }

    public boolean isMaxSaves() {
        return saves.size() == this.NB_SAVE_MAX;
    }

    public void save(GameBoard b, Context context) throws IOException {

        saves.add(new Save(b, new Date()));
        saveFile(context);
    }

    private void saveFile(Context context) {

        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);

            os.writeObject(this);

            os.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
