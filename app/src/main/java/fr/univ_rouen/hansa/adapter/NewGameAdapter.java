package fr.univ_rouen.hansa.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class NewGameAdapter extends BaseAdapter {

    private Activity activity;
    public Resources res;

    public NewGameAdapter(Activity a, Resources resLocal) {
        activity = a;
        res = resLocal;
    }

    @Override
    public int getCount() {
        return PlayerColor.values().length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_player, parent, false);
        }

        Spinner spinnerColor = (Spinner) convertView.findViewById(R.id.color);
        spinnerColor.setAdapter(new PlayerColorAdapter(activity, R.layout.list_player_color, PlayerColor.values()));
        spinnerColor.setSelection(position);

        return convertView;
    }
}
