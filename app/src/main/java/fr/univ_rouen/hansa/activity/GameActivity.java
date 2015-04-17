package fr.univ_rouen.hansa.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.exceptions.UnfinishedRoundException;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.view.DialogPause;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.view.interactions.AlertDialogBursa;

public class GameActivity extends Activity {

    //TODO remove singleton (c'est degeu!)
    private static GameActivity instance;
    public static GameActivity getInstance() {
        return instance;
    }

    private Context context = this;

    public GameActivity() {
        //TODO remove singleton (c'est degeu!)
        instance = this;
    }

    public GameActivity(Context context) {
        this.context = context;

        //TODO remove singleton (c'est degeu!)
        instance = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    @Override
    public void onResume() {
        super.onResume();

        IHTPlayer player = TurnManager.getInstance().getCurrentPlayingPlayer();

        LinearLayout sideMenu = (LinearLayout) findViewById(R.id.side_menu);

        for (int i = 0; i < sideMenu.getChildCount(); i++) {
            sideMenu.getChildAt(i).setBackgroundColor(player.getPlayerColor().getColor());
        }

        if (MovementFactory.getInstance().getPawnType() == Trader.class) {
            ((ImageButton)findViewById(R.id.button_pawntype)).setImageResource(R.drawable.trader);
        } else {
            ((ImageButton)findViewById(R.id.button_pawntype)).setImageResource(R.drawable.merchant);
        }
    }

    public void toasty(View v) {
        Toast.makeText(getApplicationContext(), "Toasty", Toast.LENGTH_SHORT).show();
    }

    public void changePawnType(View v) {
        if (MovementFactory.getInstance().getPawnType() == Trader.class) {
            MovementFactory.getInstance().setPawnType(Merchant.class);
        } else {
            MovementFactory.getInstance().setPawnType(Trader.class);
        }
        this.onResume();
    }

    public void bursaAction(View v) {
        final IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();

        if (player.getEscritoire().getStock().getMerchantCount() == 0 &&
                player.getEscritoire().getStock().getTraderCount() == 0) {
            Toast.makeText(context, "Action Impossible : Pas de pions à déplacer.", Toast.LENGTH_SHORT).show();
            return;
        }


        if (player.getEscritoire().getStock().getMerchantCount() <= 0) {
            IMovement m = MovementFactory.getInstance().makeBursaMovement(0);
            MovementManager.getInstance().doMove(m);
            Toast.makeText(context, "Nombre de trader : " + TurnManager.getInstance().getCurrentPlayer().getEscritoire().getSupply().getTraderCount(), Toast.LENGTH_SHORT).show();
            return;
        }

        if (player.getEscritoire().bursaLevel() == Integer.MAX_VALUE) {
            IMovement m = MovementFactory.getInstance().makeBursaMovement();
            MovementManager.getInstance().doMove(m);
            Toast.makeText(context, "Nombre de trader : " + TurnManager.getInstance().getCurrentPlayer().getEscritoire().getSupply().getTraderCount(), Toast.LENGTH_SHORT).show();
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

    public void rollbackAction(View view) {
        if (!MovementManager.getInstance().isEmpty()) {
            MovementManager.getInstance().rollbackMove();
        }
        this.onResume();
    }

    public void submitAction(View v){
        try {
            TurnManager.getInstance().nextPlayer(false);
            this.onResume();
        } catch (UnfinishedRoundException ex) {
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Actions restantes");
            alertDialog.setMessage("Vous avez toujours des actions, voulez vous quand même terminer votre tour?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Oui",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            TurnManager.getInstance().nextPlayer(true);
                            dialog.dismiss();
                            onResume();
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Non",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            onResume();
                        }
                    });
            alertDialog.show();
        }
    }

    /**
     * This method display a dialog containing the information about the player's escritoire
     */
    public void displayEscritoire(View view) {
        Intent intent = new Intent(this, EscritoireActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }
    public void pauseGame(View view) {
        Dialog pause = new DialogPause(context);
        pause.show();
    }
}