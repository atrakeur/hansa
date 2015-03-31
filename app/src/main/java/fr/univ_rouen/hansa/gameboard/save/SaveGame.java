package fr.univ_rouen.hansa.gameboard.save;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IPawnList;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class SaveGame {

    public static final int NB_SAVE_MAX = 5;

    public static final String FILE_NAME = "hansaFile.json";
    @Expose
    private List<Save> saves;

    private SaveGame() {
        saves = new ArrayList<Save>();

    }

    public static SaveGame getSaveGame(Context context) {
        SaveGame saveGame = new SaveGame();

        Type typeSave = new TypeToken<List<Save>>() {
        }.getType();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(IRoute.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IHTPlayer.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IVillage.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(Pawn.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IKontor.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IEscritoire.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IBonusMarker.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IPawnList.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(ICity.class,
                        new GameBoardSaveSerializer()).excludeFieldsWithoutExposeAnnotation().create();


        try {

            final FileInputStream fis = context.openFileInput(FILE_NAME);
            final ObjectInputStream is = new ObjectInputStream(fis);

            try {
                List<Save> l = gson.fromJson((String) is.readObject(), typeSave);
                saveGame.setSaves(l);

            } catch (ClassNotFoundException e) {
            } finally {
                is.close();
                fis.close();
            }

        } catch (FileNotFoundException e1) {

        } catch (IOException e) {


        }
        return saveGame;

    }

    public List<Save> getSaves() {
        return saves;
    }

    public void setSaves(List<Save> l) {
        saves = l;
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

    public void save(GameBoard board, Context context) throws IOException {
        Save save = new Save(new GameBoardSave(board), new Date());
        saves.add(save);
        saveFile(context);
    }

    private void saveFile(Context context) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(IRoute.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IHTPlayer.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IVillage.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(Pawn.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IKontor.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IEscritoire.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IBonusMarker.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IPawnList.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(ICity.class,
                        new GameBoardSaveSerializer()).excludeFieldsWithoutExposeAnnotation().create();

        Type typeSave = new TypeToken<List<Save>>() {
        }.getType();


        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(gson.toJson(this.getSaves(), typeSave));
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
