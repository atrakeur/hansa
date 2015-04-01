package fr.univ_rouen.hansa.gameboard.save;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IPawnList;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class LoadGame {

    public static GameBoard loadBoard(String objectSerialized) {

        GameBoard board;
        GameBoardSave boardSave = deserializeBoard(objectSerialized);
        if (boardSave.getBackground() == R.drawable.plateau23) {
            board = GameBoardFactory.getInstance().createGameBoard(1);
        } else {
            board = GameBoardFactory.getInstance().createGameBoard(2);
        }
        for (int i = 0; i < boardSave.getCities().size(); i++) {
            for (int j = 0; j < boardSave.getCities().get(i).getKontors().size(); j++)
                if (!boardSave.getCities().get(i).getKontors().get(j).isEmpty()) {

                    IKontor k = boardSave.getCities().get(i).getKontors().get(j);

                    IKontor k1 = board.getCities().get(i).getKontors().get(j);

                    Pawn t = k.popPawn();
                    for (IHTPlayer player : boardSave.getPawns().keySet()) {
                        List<Pawn> l = boardSave.getPawns().get(player);
                        if (l.contains(t)) {
                            t.setPlayer(player);
                        }
                    }
                    k1.pushPawn(t);


                }
        }
        for (int i = 0; i < boardSave.getRoutes().size(); i++) {

            for (int j = 0; j < boardSave.getRoutes().get(i).getVillages().size(); j++) {

                if (!boardSave.getRoutes().get(i).getVillages().get(j).isEmpty()) {

                    IVillage k = boardSave.getRoutes().get(i).getVillages().get(j);

                    IVillage k1 = board.getRoutes().get(i).getVillages().get(j);

                    Pawn t = k.pullPawn();
                    for (IHTPlayer player : boardSave.getPawns().keySet()) {
                        List<Pawn> l = boardSave.getPawns().get(player);
                        if (l.contains(t)) {
                            t.setPlayer(player);
                        }
                    }
                    k1.pushPawn(t);
                }
            }
        }

        TurnManager.loadInstance(boardSave.getManager());
        for (IHTPlayer player : TurnManager.getInstance().getPlayers()) {
            player.getEscritoire().loadPlayer(player);
        }
        return board;
    }

    private static GameBoardSave deserializeBoard(String s) {
        Type typeSave = new TypeToken<GameBoardSave>() {
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
        return gson.fromJson(s, typeSave);
    }

}
