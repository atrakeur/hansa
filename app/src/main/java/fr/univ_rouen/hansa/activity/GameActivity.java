package fr.univ_rouen.hansa.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IPawnList;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.gameboard.save.GameBoardSave;
import fr.univ_rouen.hansa.gameboard.save.GameBoardSaveSerializer;
import fr.univ_rouen.hansa.gameboard.save.SaveGame;
import fr.univ_rouen.hansa.view.GameBoardView;
import fr.univ_rouen.hansa.view.interactions.AlertDialogBursa;

public class GameActivity extends Activity {


    private Context context = this;
    private GameBoard board = null;
    private IHTPlayer player = null;
    private TurnManager manager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        charger();
        manager = TurnManager.getInstance();

        if (board == null) {

            manager.addPlayers(Arrays.asList(PlayerColor.values()));
            player = manager.getCurrentPlayer();
            //TODO just pour la présentation, à enlever après ;)
            List<Pawn> pawns = new ArrayList<>();
            pawns.add(new Merchant(player));
            pawns.add(new Merchant(player));
            pawns.add(new Merchant(player));
            pawns.add(new Merchant(player));
            pawns.add(new Merchant(player));
            pawns.add(new Merchant(player));
            player.getEscritoire().addToStock(pawns);
        }
        player = manager.getCurrentPlayer();

    }

    @Override
    protected void onResume() {
        super.onResume();

        // IHTPlayer player =TurnManager.getInstance().getCurrentPlayer();

        findViewById(R.id.button_cancel).setBackgroundColor(player.getPlayerColor().getColor());
        findViewById(R.id.button_bursa).setBackgroundColor(player.getPlayerColor().getColor());
        findViewById(R.id.button_bonus_marker).setBackgroundColor(player.getPlayerColor().getColor());
        findViewById(R.id.button_submit).setBackgroundColor(player.getPlayerColor().getColor());
        findViewById(R.id.button_pause).setBackgroundColor(player.getPlayerColor().getColor());
    }

    public void toasty(View v) {
        Toast.makeText(getApplicationContext(), "Toasty", Toast.LENGTH_SHORT).show();
    }

    public void bursaAction(View v) {
        final IHTPlayer player = manager.getCurrentPlayer();

        if (player.getEscritoire().getStock().getMerchantCount() == 0 &&
                player.getEscritoire().getStock().getTraderCount() == 0) {
            Toast.makeText(context, "Action Impossible : Pas de pions à déplacer.", Toast.LENGTH_SHORT).show();
            return;
        }


        if (player.getEscritoire().getStock().getMerchantCount() <= 0) {
            IMovement m = MovementFactory.getInstance().makeBursaMovement(0);
            MovementManager.getInstance().doMove(m);
            Toast.makeText(context, "Nombre de trader : " + manager.getCurrentPlayer().getEscritoire().getSupply().getTraderCount(), Toast.LENGTH_SHORT).show();
            return;
        }

        if (player.getEscritoire().bursaLevel() == Integer.MAX_VALUE) {
            IMovement m = MovementFactory.getInstance().makeBursaMovement();
            MovementManager.getInstance().doMove(m);
            Toast.makeText(context, "Nombre de trader : " + manager.getCurrentPlayer().getEscritoire().getSupply().getTraderCount(), Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialogBursa dialog = new AlertDialogBursa(context, getLayoutInflater(), player);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set title
        alertDialogBuilder.setTitle(R.string.ab_title);

        final TextView tex = dialog.getResult();
        // set dialog message
        alertDialogBuilder
                .setView(dialog.getView())
                .setCancelable(false)
                .setPositiveButton(R.string.alert_confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Generate mouvement and do it!
                        int merchants = Integer.parseInt(tex.getText() + "");
                        IMovement m = MovementFactory.getInstance().makeBursaMovement(merchants);
                        MovementManager.getInstance().doMove(m);
                        Toast.makeText(context, "Vous avez " + player.getEscritoire().getSupply().getTraderCount() + " Traders, et "
                                + player.getEscritoire().getSupply().getMerchantCount() + " Marchants", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    public void pauseAction(View view) {
        if (!MovementManager.getInstance().isEmpty()) {
            MovementManager.getInstance().rollbackMove();
        }
    }

    public void submitAction(View v) {
        manager.nextPlayer();
        player = manager.getCurrentPlayer();
        this.onResume();
    }

    public void save(View view) {
        SaveGame saveGame = SaveGame.getSaveGame(getApplicationContext());
        if (saveGame.isMaxSaves()) {
            AlertDialog.Builder saveMax = new AlertDialog.Builder(GameActivity.this);
            saveMax.setTitle(getString(R.string.save_title));
            saveMax.setMessage(R.string.alert_save_max);
            saveMax.setNegativeButton(R.string.alert_cancel, null);
            saveMax.show();
        } else {

            AlertDialog.Builder saveDialog = new AlertDialog.Builder(GameActivity.this);
            saveDialog.setTitle(getString(R.string.save_title));
            saveDialog.setMessage(R.string.alert_question_save);

            saveDialog.setPositiveButton(R.string.alert_confirm,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            try {
                                SaveGame saveGame = SaveGame.getSaveGame(getApplicationContext());
                                GameBoardView boardView = (GameBoardView) findViewById(R.id.dynamic_ui);

                                saveGame.save(boardView.getBoard(), GameActivity.this);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            saveDialog.setNegativeButton(R.string.alert_cancel, null);
            saveDialog.show();

        }

    }

    // pour charger board
    private void charger() {

        if (getIntent().getExtras() != null) {
            String s = getIntent().getExtras().getString("board");
            board = this.loadBoard(deserializeBoard(s));
            GameBoardView boardView = (GameBoardView) findViewById(R.id.dynamic_ui);
            boardView.setBoardLoad(board);

        }
    }

    private GameBoardSave deserializeBoard(String s) {
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


    private GameBoard loadBoard(GameBoardSave boardSave) {
        GameBoard board;

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
            System.out.println("\ni= " + i);
            for (int j = 0; j < boardSave.getRoutes().get(i).getVillages().size(); j++) {
                System.out.print("j= " + j + ", ");
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

}