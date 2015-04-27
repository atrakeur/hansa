package fr.univ_rouen.hansa.adapter;

import android.app.Activity;
import android.view.View;

import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusState;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

public class BonusMarkerUsedAdapter extends BonusMarkerAdapter {

    public BonusMarkerUsedAdapter(Activity a, IHTPlayer player) {
        super(a, player, BonusState.used);
    }

    @Override
    public View.OnClickListener bonusClickHandler() {
        return null;
    }
}
