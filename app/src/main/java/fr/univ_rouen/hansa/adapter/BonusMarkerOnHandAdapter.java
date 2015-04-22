package fr.univ_rouen.hansa.adapter;

import android.app.Activity;
import android.view.View;

import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusState;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

public class BonusMarkerOnHandAdapter extends BonusMarkerAdapter {
    public BonusMarkerOnHandAdapter(Activity a, IHTPlayer player) {
        super(a, player, BonusState.onHand);
    }


    @Override
    public void onClick(View v) {
        IBonusMarker bonusMarker = (IBonusMarker) v.getTag();
        //TODO use the bonus marker action
    }
}
