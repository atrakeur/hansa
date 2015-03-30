package fr.univ_rouen.hansa.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import fr.univ_rouen.hansa.R;

public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void newGame(View view) {
        //TODO appeller une nouvelle activité NewGameActivity
    }

    public void loadGame(View view) {
        //TODO appeller une nouvelle activité LoadGameActivity ou une popup  ???
    }

    public void options(View view) {
        //TODO appeller une nouvelle activité OptionsActivity

    }
}
