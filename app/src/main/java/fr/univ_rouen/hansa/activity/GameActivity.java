package fr.univ_rouen.hansa.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
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

public class GameActivity extends Activity{


    enum EscritoireState{MINE, OTHERS, HIDE}

    private EscritoireState state;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        state = EscritoireState.HIDE;
        TurnManager.getInstance().addPlayers(Arrays.asList(PlayerColor.values()));
        IHTPlayer player =TurnManager.getInstance().getCurrentPlayer();

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

    @Override
    protected void onResume() {
        super.onResume();

        IHTPlayer player =TurnManager.getInstance().getCurrentPlayer();

        LinearLayout sideMenu = (LinearLayout) findViewById(R.id.side_menu);

        for(int i = 0; i<sideMenu.getChildCount(); i++){
            sideMenu.getChildAt(i).setBackgroundColor(player.getPlayerColor().getColor());
        }
/*
        findViewById(R.id.button_cancel).setBackgroundColor(player.getPlayerColor().getColor());
        findViewById(R.id.button_bursa).setBackgroundColor(player.getPlayerColor().getColor());
        findViewById(R.id.button_bonus_marker).setBackgroundColor(player.getPlayerColor().getColor());
        findViewById(R.id.button_submit).setBackgroundColor(player.getPlayerColor().getColor());
        findViewById(R.id.button_pause).setBackgroundColor(player.getPlayerColor().getColor());*/
    }

    public void toasty(View v){
        Toast.makeText(getApplicationContext(), "Toasty", Toast.LENGTH_SHORT).show();
    }

    public void bursaAction(View v){
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

        if(player.getEscritoire().bursaLevel() == Integer.MAX_VALUE){
            MovementManager.getInstance().doBursaMove();
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
                    Toast.makeText(context, "Vous avez "+player.getEscritoire().getSupply().getTraderCount()+" Traders, et "
                            +player.getEscritoire().getSupply().getMerchantCount()+" Marchants", Toast.LENGTH_SHORT).show();
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

    public void pauseAction(View view){
        if(!MovementManager.getInstance().isEmpty()){
            MovementManager.getInstance().rollbackMove();
        }
    }

    public void submitAction(View v){
        TurnManager.getInstance().nextPlayer();
        this.onResume();
    }

    public void toggleEscritoire(View view) {
        Dialog dial = new Dialog(context, android.R.style.Theme_Material_Light_Dialog);
        Log.w("Escritoire State", ""+state);
        if(state == EscritoireState.HIDE){
            dial.setContentView(R.layout.dialog_escritoire);
            dial.show();
        } else if(state == EscritoireState.MINE){
            dial.dismiss();
            state = EscritoireState.HIDE;
            //(findViewById(R.id.escritoire)).setVisibility(View.GONE);
        }
    }

}