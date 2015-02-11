package fr.univ_rouen.hansa.activity;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import fr.univ_rouen.hansa.R;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        SurfaceView surface =  (SurfaceView)findViewById(R.id.dynamic_ui);
    }

    public void toasty(View v){
        Toast.makeText(getApplicationContext(), "Toasty", Toast.LENGTH_SHORT).show();
    }

}