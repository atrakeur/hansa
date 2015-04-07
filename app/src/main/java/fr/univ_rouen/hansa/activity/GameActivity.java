package fr.univ_rouen.hansa.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.adapter.CustomAdapter;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.view.interactions.AlertDialogBursa;
import fr.univ_rouen.hansa.view.utils.ListModel;

public class GameActivity extends Activity {

    private Context context = this;
    ListView list;
    CustomAdapter adapter;
    public  GameActivity CustomListView = null;
    public ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TurnManager.getInstance().addPlayers(Arrays.asList(PlayerColor.values()));
        IHTPlayer player =TurnManager.getInstance().getCurrentPlayer();


        //TODO just pour la présentation, à enlever après ;)
        List<Pawn> pawns = Lists.newArrayList();
        pawns.add(new Merchant(player));
        pawns.add(new Merchant(player));
        pawns.add(new Merchant(player));
        pawns.add(new Merchant(player));
        pawns.add(new Merchant(player));
        pawns.add(new Merchant(player));

        player.getEscritoire().addToStock(pawns);
        CustomListView = this;

        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
        setListData();

        Resources res =getResources();
        list = (ListView)findViewById(R.id.list);

        /**************** Create Custom Adapter *********/
        adapter = new CustomAdapter(CustomListView, CustomListViewValuesArr,res);
        list.setAdapter(adapter);
        /**
         * Le spinner est a nul et ça pose probleme.
        Spinner spinner = (Spinner) findViewById(R.id.player);
        // Create an ArrayAdapter using the string array and a default spinner layout
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        this.setSpinnerListener(spinner);
        */
    }

    /****** Function to set data in ArrayList *************/
    public void setListData() {

        for (int i = 0; i < 5; i++) {

            final ListModel sched = new ListModel();

            /******* Firstly take data in model object ******/
            sched.setColor("Red");
            sched.setPlayer("Player");

            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add(sched);
        }
    }

    /**
     * Methode de creation des listener a l'exterieur. Possible de le stocker dans une classe
     * @param spinner
     */
    public void setSpinnerListener(Spinner spinner) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner p1 = (Spinner) findViewById(R.id.player);
                Spinner color1 = (Spinner) findViewById(R.id.color);
                p1.setOnItemSelectedListener(this);
                color1.setOnItemSelectedListener(this);
                String s1 = (String) p1.getSelectedItem();
                String s2 = (String) color1.getSelectedItem();
                Log.w("Joueur 1 : ", s1);
                Log.w("Couleur 1 :" , s2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();

        IHTPlayer player =TurnManager.getInstance().getCurrentPlayer();

        findViewById(R.id.button_cancel).setBackgroundColor(player.getPlayerColor().getColor());
        findViewById(R.id.button_bursa).setBackgroundColor(player.getPlayerColor().getColor());
        findViewById(R.id.button_bonus_marker).setBackgroundColor(player.getPlayerColor().getColor());
        findViewById(R.id.button_submit).setBackgroundColor(player.getPlayerColor().getColor());
        findViewById(R.id.button_pause).setBackgroundColor(player.getPlayerColor().getColor());
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
            IMovement m = MovementFactory.getInstance().makeBursaMovement(0);
            MovementManager.getInstance().doMove(m);
            Toast.makeText(context, "Nombre de trader : "+TurnManager.getInstance().getCurrentPlayer().getEscritoire().getSupply().getTraderCount(), Toast.LENGTH_SHORT).show();
            return;
        }

        if(player.getEscritoire().bursaLevel() == Integer.MAX_VALUE){
            IMovement m = MovementFactory.getInstance().makeBursaMovement();
            MovementManager.getInstance().doMove(m);
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
                    //Generate mouvement and do it!
                    int merchants = Integer.parseInt( tex.getText()+"" );
                    IMovement m = MovementFactory.getInstance().makeBursaMovement(merchants);
                    MovementManager.getInstance().doMove(m);
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
        TurnManager.getInstance().nextPlayer(false);
        this.onResume();
    }

    public void onItemClick(int mPosition) {

    }
}