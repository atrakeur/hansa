package fr.univ_rouen.hansa.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.view.CircleView;
import fr.univ_rouen.hansa.view.interactions.AlertDialogBursa;

public class GameActivity extends Activity {

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TurnManager.getInstance().addPlayers(Arrays.asList(PlayerColor.values()));
        IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();

        //TODO remove these lines later
        List<Pawn> pawns = Lists.newArrayList();
        pawns.add(new Merchant(player));
        pawns.add(new Merchant(player));
        pawns.add(new Merchant(player));
        pawns.add(new Merchant(player));
        pawns.add(new Merchant(player));
        pawns.add(new Merchant(player));

        player.getEscritoire().addToStock(pawns);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();

        LinearLayout sideMenu = (LinearLayout) findViewById(R.id.side_menu);

        for (int i = 0; i < sideMenu.getChildCount(); i++) {
            sideMenu.getChildAt(i).setBackgroundColor(player.getPlayerColor().getColor());
        }

    }

    public void toasty(View v) {
        Toast.makeText(getApplicationContext(), "Toasty", Toast.LENGTH_SHORT).show();
    }

    public void bursaAction(View v) {
        final IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();

        if (player.getEscritoire().getStock().getMerchantCount() == 0 &&
                player.getEscritoire().getStock().getTraderCount() == 0) {
            Toast.makeText(context, "Action Impossible : Pas de pions à déplacer.", Toast.LENGTH_SHORT).show();
            return;
        }


        if (player.getEscritoire().getStock().getMerchantCount() <= 0) {
            MovementManager.getInstance().doBursaMove(0);
            Toast.makeText(context, "Nombre de trader : " + TurnManager.getInstance().getCurrentPlayer().getEscritoire().getSupply().getTraderCount(), Toast.LENGTH_SHORT).show();
            return;
        }

        if (player.getEscritoire().bursaLevel() == Integer.MAX_VALUE) {
            MovementManager.getInstance().doBursaMove();
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
                        MovementManager.getInstance().doBursaMove(Integer.parseInt(tex.getText() + ""));
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
        TurnManager.getInstance().nextPlayer();
        this.onResume();
    }

    /**
     * This method display a dialog containing the information about the player's escritoire
     */
    public void displayEscritoire(View view) {

        //Setting up the dialog.
        Dialog dial = new Dialog(context);
        dial.setTitle(R.string.esc_title);
        dial.setContentView(R.layout.list_escritoire);

        int color = TurnManager.getInstance().getCurrentPlayer().getPlayerColor().getColor();
        IEscritoire esc = TurnManager.getInstance().getCurrentPlayer().getEscritoire();

        //Info about the Player's Stock.
        dial.findViewById(R.id.color_trader_stock).setBackgroundColor(color);
        ((TextView) dial.findViewById(R.id.nb_trader_stock)).setText("x" + esc.getStock().getTraderCount());
        ((CircleView) dial.findViewById(R.id.color_merchant_stock)).setCircleFillColor(color);
        ((TextView) dial.findViewById(R.id.nb_merchant_stock)).setText("x" + esc.getStock().getMerchantCount());

        //Info about the Player's Supply.
        dial.findViewById(R.id.color_trader_supply).setBackgroundColor(color);
        ((TextView) dial.findViewById(R.id.nb_trader_supply)).setText("x" + esc.getSupply().getTraderCount());
        ((CircleView) dial.findViewById(R.id.color_merchant_supply)).setCircleFillColor(color);
        ((TextView) dial.findViewById(R.id.nb_merchant_supply)).setText("x" + esc.getSupply().getMerchantCount());

        //Info about the Player's Competence.
        ((TextView) dial.findViewById(R.id.nb_clavis_urbis)).setText("" + esc.clavisUrbisLevel());
        dial.findViewById(R.id.color_privilegium).setBackgroundColor(esc.privilegiumLevel().getColor());
        ((TextView) dial.findViewById(R.id.nb_liber_sophia)).setText("" + esc.liberSophiaLevel());
        ((TextView) dial.findViewById(R.id.nb_actiones)).setText("" + esc.actionesLevel());

        /*
            Special Case : Bursa Level.
            If it's at its maximum, then display the infinity symbol.
        */
        String bursa = (esc.bursaLevel() == Integer.MAX_VALUE) ? "\u221e" : "" + esc.bursaLevel();
        ((TextView) dial.findViewById(R.id.nb_bursa)).setText(bursa);

        //Display the dialog
        dial.show();

    }

}