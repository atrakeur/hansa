package fr.univ_rouen.hansa.gameboard.save;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IPawnList;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;

public class LoadGame {

    public static GameBoard loadBoard(String objectSerialized) {

        GameBoard board;
        /*
        * load GameBoardSave
         */
        GameBoardSave game = deserializeBoard(objectSerialized);
        // create GameBoard
        if (game.getBackground() == R.drawable.plateau23) {
            board = GameBoardFactory.getInstance().createGameBoard(1);
        } else {
            board = GameBoardFactory.getInstance().createGameBoard(2);
        }
        // load players and currentPlayer
        TurnManager.getInstance().setPlayers(game.getPlayers());
        TurnManager.getInstance().setCurrentPlayer(game.getCurrentPlayer());

        for (IHTPlayer player : TurnManager.getInstance().getPlayers()) {
            player.getEscritoire().loadPlayer(player);
        }
        //load pawns in the kontors
        for (Pawn p : game.getPawnsCities().keySet()) {
            int index[] = game.getPawnsCities().get(p);
            p.setPlayer(TurnManager.getInstance().getPlayers().get(index[2]));
            board.getCities().get(index[0]).getKontor(index[1]).pushPawn(p);
        }
        // load the pawns in the villages
        for (Pawn p : game.getPawnsRoutes().keySet()) {
            int index[] = game.getPawnsRoutes().get(p);
            p.setPlayer(TurnManager.getInstance().getPlayers().get(index[2]));
            board.getRoutes().get(index[0]).getVillage(index[1]).pushPawn(p);
        }
        // load the bonus Markers in the routes
        for (IBonusMarker bm : game.getbonusMarkerRoutes().keySet()) {
            //  board.getRoutes().get(game.getbonusMarkerRoutes().get(bm)).;

        }

        return board;
    }

    private static GameBoardSave deserializeBoard(String s) {
        Type typeSave = new TypeToken<GameBoardSave>() {
        }.getType();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(IHTPlayer.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(Pawn.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IEscritoire.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IBonusMarker.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IPawnList.class,
                        new GameBoardSaveSerializer())
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson.fromJson(s, typeSave);
    }

}
