package fr.univ_rouen.hansa.adapter;

import android.app.Activity;
import android.view.View;

import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusState;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

public class BonusMarkerInPlateAdapter extends BonusMarkerAdapter {

    public BonusMarkerInPlateAdapter(Activity a, IHTPlayer player) {
        super(a, player, BonusState.inPlate);
    }

    @Override
    public View.OnClickListener bonusClickHandler() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Bonus Marker selected by the player
                IBonusMarker bonusMarker = (IBonusMarker) v.getTag();

                MovementFactory.getInstance().state = MovementFactory.State.BM_PLATE;
                MovementFactory.getInstance().setBonusMarker(bonusMarker);

                //Close the dialog activity that display the BonusMarker
                getActivity().finish();

            }
        };
    }
}
