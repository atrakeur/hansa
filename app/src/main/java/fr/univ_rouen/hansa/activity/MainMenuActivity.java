package fr.univ_rouen.hansa.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import fr.univ_rouen.hansa.R;


public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        createController();
    }
    private void createController(){

        //button new game
        findViewById(R.id.new_game).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //exemple comment appeler une autre activity
                Intent intent = new Intent(MainMenuActivity.this, GameActivity.class);
                Toast.makeText(getApplicationContext(), "Chargement en cours...", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        //button load game
        findViewById(R.id.load_game).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // loadActivity
                /*Intent intent = new Intent(MainMenuActivity.this, LoadActivity.class);
                Toast.makeText(getApplicationContext(), "Chargement en cours...", Toast.LENGTH_SHORT).show();
                startActivity(intent);*/
            }
        });

        //button options
        findViewById(R.id.options_game).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

        //button exit
        findViewById(R.id.exit_game).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}
