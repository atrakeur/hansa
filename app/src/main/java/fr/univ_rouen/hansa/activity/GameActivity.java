package fr.univ_rouen.hansa.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.view.interactions.AlertDialogBursa;

public class GameActivity extends Activity {

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TurnManager.getInstance().addPlayers(Arrays.asList(PlayerColor.values()));

        List<Pawn> pawns = new ArrayList<>();
        pawns.add(new Merchant());
        pawns.add(new Merchant());
        pawns.add(new Merchant());
        pawns.add(new Merchant());
        pawns.add(new Merchant());
        pawns.add(new Merchant());


        TurnManager.getInstance().getCurrentPlayer().getEscritoire().addToStock(pawns);


    }

    public void toasty(View v){
        Toast.makeText(getApplicationContext(), "Toasty", Toast.LENGTH_SHORT).show();
    }

    public void bursa_action(View v){
        final IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();

        if(player.getEscritoire().getStock().getMerchantCount() == 0 &&
                player.getEscritoire().getStock().getTraderCount() == 0){
            Toast.makeText(context, "Action Impossible : Pas de pions à déplacer.", Toast.LENGTH_SHORT).show();
            return;
        }


        if(player.getEscritoire().getStock().getMerchantCount() <= 0){
            MovementManager.getInstance().doBursaMove(0);
            Toast.makeText(context, "Nombre de trader : "+TurnManager.getInstance().getCurrentPlayer().getEscritoire().getSupply().getTraderCount(), Toast.LENGTH_SHORT).show();
            return;
        }


        AlertDialogBursa dialog = new AlertDialogBursa(context, getLayoutInflater(), player );

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
                    MovementManager.getInstance().doBursaMove(Integer.parseInt( tex.getText()+"" ));
                    Toast.makeText(context, "Nombre de trader : "+player.getEscritoire().getSupply().getTraderCount(), Toast.LENGTH_SHORT).show();
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

}