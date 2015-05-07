package fr.univ_rouen.hansa.adapter;

import android.app.Activity;
import android.view.View;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

public class BonusMarkerUsedAdapter extends BonusMarkerAdapter {

    @Override
    public List<IBonusMarker> getListBonusMarker() {
        return getPlayer().getEscritoire().getUsedBonusMarker();
    }

    public BonusMarkerUsedAdapter(Activity a, IHTPlayer player) {
        super(a, player);
    }

    @Override
    public View.OnClickListener bonusClickHandler() {
        return null;
    }
}
