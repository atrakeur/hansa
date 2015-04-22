package fr.univ_rouen.hansa.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusActiones;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusEscritoire;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusKontor;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusPermutation;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusRemovePawns;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusState;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IVisitorBonusMarker;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

/**
 * This class adapts an object BonusMarker into an ImageView.
 */
public abstract class BonusMarkerAdapter extends BaseAdapter implements IVisitorBonusMarker, View.OnClickListener {

    private Activity activity;
    private List<IBonusMarker> listBonusMarker;
    private int resBonusMarker;

    public BonusMarkerAdapter(Activity a, IHTPlayer player, BonusState state) {
        if (a == null || player == null || state == null) {
            throw new IllegalArgumentException();
        }

        activity = a;
        listBonusMarker = Lists.newArrayList();

        //Get all the used bonus marker
        for (IBonusMarker bonusMarker : player.getEscritoire().getBonusMarker()) {
            if (bonusMarker.getState() == state) {
                listBonusMarker.add(bonusMarker);
            }
        }
    }

    @Override
    public int getCount() {
        return listBonusMarker.size();
    }

    @Override
    public Object getItem(int position) {
        return listBonusMarker.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_bonus_marker, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.image_bonus_marker);
        imageView.setOnClickListener(this);
        imageView.setTag(listBonusMarker.get(position));

        //visitor pattern, kind of disgusting but it works ....
        listBonusMarker.get(position).accept(this);


        imageView.setImageResource(this.resBonusMarker);


        return convertView;
    }

    @Override
    public final void visit(IBonusMarker marker) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(BonusActiones marker) {
        switch (marker.getValue()) {
            case 3:
                this.resBonusMarker = R.drawable.bonusactiones3;
                break;
            case 4:
                this.resBonusMarker = R.drawable.bonusactiones4;
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public void visit(BonusEscritoire marker) {
        this.resBonusMarker = R.drawable.bonusescritoire;
    }

    @Override
    public void visit(BonusKontor marker) {
        this.resBonusMarker = R.drawable.bonuskontor;
    }

    @Override
    public void visit(BonusPermutation marker) {
        this.resBonusMarker = R.drawable.bonuspermutation;
    }

    @Override
    public void visit(BonusRemovePawns marker) {
        this.resBonusMarker = R.drawable.bonusremovepawn;
    }

    public Activity getActivity() {
        return activity;
    }
}
