package fr.univ_rouen.hansa.adapter;

import android.app.Activity;
import android.view.View;

import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusState;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

public class BonusMarkerInPlateAdapter extends BonusMarkerAdapter {

    public BonusMarkerInPlateAdapter(Activity a, IHTPlayer player) {
        super(a, player, BonusState.inPlate);
    }

    @Override
    public View.OnClickListener bonusClickHandler() {
        //TODO a faire
        return null;
    }
}
