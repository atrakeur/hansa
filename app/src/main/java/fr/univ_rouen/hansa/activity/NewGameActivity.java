package fr.univ_rouen.hansa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Set;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.adapter.CustomAdapter;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.view.utils.ListModel;

public class NewGameActivity extends ActionBarActivity {

    private ArrayList<ListModel> listModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        initializeListView();

        ListView list = (ListView) findViewById(R.id.list);

        CustomAdapter adapter = new CustomAdapter(this, listModels, getResources());
        list.setAdapter(adapter);
    }

    private void initializeListView() {
        listModels = Lists.newArrayList();

        for (int i = 0; i < PlayerColor.values().length; i++) {

            final ListModel sched = new ListModel();

            sched.setColor("Yellow");
            sched.setPlayer("IA");

            listModels.add(sched);
        }
    }


    public void startNewGame(View view) {
        ListView list = (ListView) findViewById(R.id.list);

        Set<PlayerColor> colors = Sets.newHashSet();

        for (int i = 0; i < listModels.size(); i++) {
            Spinner sPlayer = (Spinner) list.getChildAt(i).findViewById(R.id.player);

            if (("" + sPlayer.getSelectedItem()).equals("Aucun")) {
                continue;
            }

            Spinner sColor = (Spinner) list.getChildAt(i).findViewById(R.id.color);
            PlayerColor playerColor = (PlayerColor) sColor.findViewById(R.id.player_color).getTag();

            if (!colors.add(playerColor)) {
                //TODO throw error
                Log.w("Pb", "Already inside");
            }

            //TODO remove log
            Log.w("Recap Color", sColor.getSelectedItem() + "");
            Log.w("Recap Player", sPlayer.getSelectedItem() + "");
        }

        //TODO revoir
        if (colors.size() <= 2) {
            Log.w("Erreur", "Minimum 2 joueur !!!!!");
        }

        TurnManager.getInstance().addPlayers(Lists.newArrayList(colors));
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }
}
