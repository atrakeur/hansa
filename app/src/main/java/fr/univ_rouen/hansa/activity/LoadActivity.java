package fr.univ_rouen.hansa.activity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.save.ModelListSave;
import fr.univ_rouen.hansa.gameboard.save.SaveGame;


public class LoadActivity extends Activity {
    SaveGame  saveGame = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        try {
            saveGame = SaveGame.getSaveGame(getApplicationContext());
            ListView list = (ListView) findViewById(R.id.saves);
            list.setAdapter(new ModelListSave(LoadActivity.this, saveGame.getSaves()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
