package fr.univ_rouen.hansa.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
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

public class GameActivity extends Activity implements GestureDetector.OnGestureListener{

    enum EscritoireState{MINE, OTHERS, HIDE}

    private EscritoireState state;
    private Context context = this;
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        state = EscritoireState.HIDE;
        TurnManager.getInstance().addPlayers(Arrays.asList(PlayerColor.values()));
        IHTPlayer player =TurnManager.getInstance().getCurrentPlayer();

        mDetector = new GestureDetectorCompat(this,this);

        final ImageView view = (ImageView)findViewById(R.id.escritoire);

        view.setVisibility(View.GONE);


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

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.w("Scroll Event : ", distanceX+"\t"+distanceY);
        if(Math.abs(distanceX)<Math.abs(distanceY)){
            if(state == EscritoireState.HIDE && distanceY>0){
                state = EscritoireState.MINE;
                (findViewById(R.id.escritoire)).setVisibility(View.VISIBLE);
            } else if(state == EscritoireState.MINE && distanceY < 0){
                state = EscritoireState.HIDE;
                (findViewById(R.id.escritoire)).setVisibility(View.GONE);
            }
            Log.w("Vertical", state.toString());
        } else if(Math.abs(distanceX)>Math.abs(distanceY)){
            if(state == EscritoireState.HIDE && distanceX<0){
                state = EscritoireState.OTHERS;
            } else if(state == EscritoireState.OTHERS&& distanceX > 0){
                state = EscritoireState.HIDE;
            }
            Log.w("Horizontal", state.toString());
        }


        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}