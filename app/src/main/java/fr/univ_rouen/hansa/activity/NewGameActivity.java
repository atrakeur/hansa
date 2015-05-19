package fr.univ_rouen.hansa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.adapter.NewGameAdapter;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class NewGameActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        ListView list = (ListView) findViewById(R.id.list);

        NewGameAdapter adapter = new NewGameAdapter(this, getResources());
        list.setAdapter(adapter);
    }

    public void startNewGame(View view) {
        ListView list = (ListView) findViewById(R.id.list);

        List<PlayerColor> colors = Lists.newArrayList();
        List<Object> players     = Lists.newArrayList();

        for (int i = 0; i < PlayerColor.values().length; i++) {
            Spinner sPlayer = (Spinner) list.getChildAt(i).findViewById(R.id.player);


            if (("" + sPlayer.getSelectedItem()).equals("Aucun")) {
                continue;
            }

            Spinner sColor = (Spinner) list.getChildAt(i).findViewById(R.id.color);
            PlayerColor playerColor = (PlayerColor) sColor.findViewById(R.id.player_color).getTag();

            if (colors.contains(playerColor)) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.color_already_use), Toast.LENGTH_SHORT).show();
                return;
            }
            colors.add(playerColor);
            players.add(sPlayer.getSelectedItem());
        }

        if (colors.size() <= 2) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.not_enough_player), Toast.LENGTH_SHORT).show();
            return;
        }

        TurnManager.getInstance().addPlayers(players, colors);

        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }
}
