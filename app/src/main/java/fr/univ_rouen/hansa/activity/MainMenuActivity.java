package fr.univ_rouen.hansa.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.common.collect.Lists;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;


public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void newGame(View view) {
        Intent intent = new Intent(MainMenuActivity.this, NewGameActivity.class);
        startActivity(intent);
    }

    public void loadGame(View view) {
        GameBoard.LOAD_FROM_SAVE = true;

        TurnManager.getInstance().addPlayers(Lists.newArrayList(PlayerColor.blue));

        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();

        //TODO loading (new SaveManager().load())
        // loadActivity
                /*Intent intent = new Intent(MainMenuActivity.this, LoadActivity.class);
                Toast.makeText(getApplicationContext(), "Chargement en cours...", Toast.LENGTH_SHORT).show();
                startActivity(intent);*/
    }

    public void optionsGame(View view) {


    }

    public void exitGame(View view) {
        this.finish();
    }

}
