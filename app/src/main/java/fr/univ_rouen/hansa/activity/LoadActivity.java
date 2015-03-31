package fr.univ_rouen.hansa.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.gameboard.save.ModelListSave;
import fr.univ_rouen.hansa.gameboard.save.SaveGame;

public class LoadActivity extends Activity {
    SaveGame saveGame = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        saveGame = SaveGame.getSaveGame(getApplicationContext());
        ListView list = (ListView) findViewById(R.id.saves);
        list.setAdapter(new ModelListSave(LoadActivity.this, saveGame.getSaves()));

    }
}
