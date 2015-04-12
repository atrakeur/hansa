package fr.univ_rouen.hansa.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class PlayerColorAdapter extends ArrayAdapter {

    private Activity activity;

    public PlayerColorAdapter(Context context, int resource, Object[] objects) {
        super(context, resource, objects);
        activity = (Activity) context;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.list_player_color, parent, false);

        TextView color = (TextView) layout.findViewById(R.id.player_color);
        color.setTag(PlayerColor.values()[position]);
        color.setBackgroundColor(PlayerColor.values()[position].getColor());
        color.setMinHeight(50);

        return layout;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
}
