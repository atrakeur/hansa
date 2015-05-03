package fr.univ_rouen.hansa.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.actions.movement.PlayBonus;
import fr.univ_rouen.hansa.activity.GameActivity;
import fr.univ_rouen.hansa.exceptions.NotEnoughSupplyException;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusActiones;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusEscritoire;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusKontor;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusPermutation;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusRemovePawns;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusState;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IVisitorBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

public class BonusMarkerOnHandAdapter extends BonusMarkerAdapter {
    @Override
    public List<IBonusMarker> getListBonusMarker() {
        List<IBonusMarker> bonusMarkers = Lists.newArrayList();

        for(IBonusMarker bonusMarker : getPlayer().getEscritoire().getBonusMarker()){
            if(bonusMarker.getState() == BonusState.onHand){
                bonusMarkers.add(bonusMarker);
            }
        }

        return bonusMarkers;
    }

    public BonusMarkerOnHandAdapter(Activity a, IHTPlayer player) {
        super(a, player);
    }

    private class BonusClickHandler implements IVisitorBonusMarker, View.OnClickListener {

        @Override
        public void onClick(View v) {
            IBonusMarker bonusMarker = (IBonusMarker) v.getTag();
            bonusMarker.accept(this);
        }

        @Override
        public void visit(IBonusMarker marker) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void visit(BonusActiones marker) {
            marker.setPlayer(getPlayer());
            MovementManager.getInstance().doMove(new PlayBonus(marker));
            getActivity().finish();
        }

        @Override
        public void visit(final BonusEscritoire marker) {


            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            alertDialog.setTitle("Choisir le pouvoir a augmenter");

            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.spinner_power, null);
            final Spinner spi = (Spinner) view.findViewById(R.id.powers);

            alertDialog.setView(spi);

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Oui",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String power = (String) spi.getSelectedItem();
                        if (power.equalsIgnoreCase("Actiones")) marker.setPower(Power.Actiones);
                        else if (power.equalsIgnoreCase("Bursa")) marker.setPower(Power.Bursa);
                        else if (power.equalsIgnoreCase("Clavis Urbis"))
                            marker.setPower(Power.ClavisUrbis);
                        else if (power.equalsIgnoreCase("Liber Sophia"))
                            marker.setPower(Power.LiberSophiae);
                        else if (power.equalsIgnoreCase("Privilegium"))
                            marker.setPower(Power.Privillegium);

                        marker.setPlayer(getPlayer());

                        try{
                            MovementManager.getInstance().doMove(new PlayBonus(marker));
                            dialog.dismiss();
                            getActivity().finish();
                        } catch(NotEnoughSupplyException ex){
                            dialog.dismiss();
                            Toast.makeText(getActivity(), "Cette competence est augmentee a son maximum", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Non",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }

        @Override
        public void visit(BonusKontor marker) {
            marker.setPlayer(getPlayer());
            //TODO handle BonusKontor movement

            MovementFactory.getInstance().setBonusMarker(marker);
            MovementFactory.getInstance().setBonusHasToBeReplaced(false);

            getActivity().finish();

            Toast.makeText(GameActivity.getInstance().getApplicationContext(),
                    "Selectionnez 1 village et 1 ville", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void visit(BonusPermutation marker) {
            //TODO Handle the BonusPermutation movement
            MovementFactory.getInstance().setBonusMarker(marker);
            MovementFactory.getInstance().setBonusHasToBeReplaced(false);

            getActivity().finish();

            Toast.makeText(GameActivity.getInstance().getApplicationContext(),
                    "Selectionnez 2 comptoir d'une meme ville a inverser", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void visit(BonusRemovePawns marker) {
            MovementFactory.getInstance().setBonusMarker(marker);
            MovementFactory.getInstance().setBonusHasToBeReplaced(false);

            getActivity().finish();

            Toast.makeText(GameActivity.getInstance().getApplicationContext(),
                    "Selectionnez 3 villages a retirer", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public View.OnClickListener bonusClickHandler() {
        return new BonusClickHandler();
    }
}
