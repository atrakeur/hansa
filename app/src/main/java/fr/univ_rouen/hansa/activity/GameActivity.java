package fr.univ_rouen.hansa.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.view.interactions.AlertDialogBursa;

public class GameActivity extends Activity {

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TurnManager.getInstance().addPlayers(Arrays.asList(PlayerColor.values()));
    }

    public void toasty(View v){
        Toast.makeText(getApplicationContext(), "Toasty", Toast.LENGTH_SHORT).show();
    }

    public void bursa_action(View v){
        if(TurnManager.getInstance().getCurrentPlayer().getEscritoire().getStock().getMerchantCount() <= 0){
            MovementManager.getInstance().doBursaMove(0);
            Toast.makeText(context, "Nombre de trader : "+TurnManager.getInstance().getCurrentPlayer().getEscritoire().getSupply().getTraderCount(), Toast.LENGTH_SHORT).show();
            return;
        }


        AlertDialogBursa dialog = new AlertDialogBursa(context, getLayoutInflater(), TurnManager.getInstance().getCurrentPlayer() );

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set title
        alertDialogBuilder.setTitle(R.string.ab_title);

        /*LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_bursa, null);

        ((TextView) dialogView.findViewById(R.id.ab_mer_nb_stock)).setText(""+TurnManager.getInstance().getCurrentPlayer().getEscritoire().getStock().getMerchantCount());
        ((TextView) dialogView.findViewById(R.id.ab_tra_nb_stock)).setText(""+TurnManager.getInstance().getCurrentPlayer().getEscritoire().getStock().getTraderCount());
        ((TextView) dialogView.findViewById(R.id.ab_bursa_nb)).setText(""+TurnManager.getInstance().getCurrentPlayer().getEscritoire().bursaLevel());
        final TextView nbMer = ((TextView) dialogView.findViewById(R.id.ab_bursa_mer));
        nbMer.setText("0");
        ((Button) dialogView.findViewById(R.id.ab_bursa_plus)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nb = (Integer.parseInt("" + nbMer.getText()) + 1);
                if (nb > TurnManager.getInstance().getCurrentPlayer().getEscritoire().bursaLevel()) {
                    Toast.makeText(getApplicationContext(), R.string.ab_too_much, Toast.LENGTH_SHORT).show();
                    return;
                }
                nbMer.setText("" + nb);
            }
        });
        ((Button) dialogView.findViewById(R.id.ab_bursa_minus)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nb = Integer.parseInt(""+nbMer.getText())-1;
                if(nb<0){
                    Toast.makeText(getApplicationContext(), R.string.ab_too_low, Toast.LENGTH_SHORT).show();
                    return;
                }
                nbMer.setText(""+nb);
            }
        });*/
        final TextView tex = dialog.getResult();
        // set dialog message
        alertDialogBuilder
            .setView(dialog.getView())
            .setCancelable(false)
            .setPositiveButton(R.string.alert_confirm, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    IHTPlayer p = TurnManager.getInstance().getCurrentPlayer();
                    MovementManager.getInstance().doBursaMove(Integer.parseInt( tex.getText()+"" ));
                    Toast.makeText(context, "Nombre de trader : "+p.getEscritoire().getSupply().getTraderCount(), Toast.LENGTH_SHORT).show();
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