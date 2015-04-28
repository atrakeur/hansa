package fr.univ_rouen.hansa.adapter;

import android.app.Activity;
import android.view.View;

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

                //Close the dialog activity that display the BonusMarker
                getActivity().finish();

                //TODO set the bonusMarker var to the gameboard

                //Ask the player in which EMPTY road he whish to replace the bonusMarker

                //Prevent the player to do any action except select a road

                //Check if the raod selected is empty

                //Place the bonusMarker to the selected road
            }
        };
    }
}
