package fr.univ_rouen.hansa.adapter;

import android.app.Activity;
import android.view.View;

import java.util.List;

import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

public class BonusMarkerInPlateAdapter extends BonusMarkerAdapter {

    public BonusMarkerInPlateAdapter(Activity a, IHTPlayer player) {
        super(a, player);
    }


    @Override
    public List<IBonusMarker> getListBonusMarker() {
        return getPlayer().getEscritoire().getTinPlateContent();
    }

    @Override
    public View.OnClickListener bonusClickHandler() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Bonus Marker selected by the player
                IBonusMarker bonusMarker = (IBonusMarker) v.getTag();
                MovementFactory.getInstance().setBonusMarker(bonusMarker);
                MovementFactory.getInstance().setBonusHasToBeReplaced(true);
                getPlayer().getEscritoire().removeTinPlate(bonusMarker);
                //Close the dialog activity that display the BonusMarker
                getActivity().finish();

            }
        };
    }
}
