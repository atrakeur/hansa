package fr.univ_rouen.hansa.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.adapter.BonusMarkerInPlateAdapter;
import fr.univ_rouen.hansa.adapter.BonusMarkerOnHandAdapter;
import fr.univ_rouen.hansa.adapter.BonusMarkerUsedAdapter;
import fr.univ_rouen.hansa.gameboard.TurnManager;

public class BonusMarkerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bonus_marker);

        ListView lv1 = (ListView) findViewById(R.id.list_bm_in_plate);
        lv1.setAdapter(new BonusMarkerInPlateAdapter(this, TurnManager.getInstance().getCurrentPlayingPlayer()));

        ListView lv2 = (ListView) findViewById(R.id.list_bm_on_hand);
        lv2.setAdapter(new BonusMarkerOnHandAdapter(this, TurnManager.getInstance().getCurrentPlayingPlayer()));

        ListView lv3 = (ListView) findViewById(R.id.list_bm_used);
        lv3.setAdapter(new BonusMarkerUsedAdapter(this, TurnManager.getInstance().getCurrentPlayingPlayer()));
    }
}
