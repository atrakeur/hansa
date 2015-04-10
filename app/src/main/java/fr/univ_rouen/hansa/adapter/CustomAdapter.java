package fr.univ_rouen.hansa.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

/**
 * ****** Adapter class extends with BaseAdapter and implements with OnClickListener ***********
 */
public class CustomAdapter extends BaseAdapter implements OnClickListener {

    /**
     * ******** Declare Used Variables ********
     */
    private Activity activity;
    private ArrayList data;
    public Resources res;

    /**
     * **********  CustomAdapter Constructor ****************
     */
    public CustomAdapter(Activity a, ArrayList d, Resources resLocal) {

        /********** Take passed values **********/
        activity = a;
        data = d;
        res = resLocal;
    }

    @Override
    public int getCount() {

        if (data.size() <= 0)
            return 1;
        return data.size();
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

        final Spinner spiColor = (Spinner) convertView.findViewById(R.id.color);
        spiColor.setAdapter(new PlayerColorAdapter(activity, R.layout.list_player_color, PlayerColor.values()));
        spiColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.w("Color", "" + spiColor.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Spinner spiPlayer = (Spinner) convertView.findViewById(R.id.player);
        spiPlayer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.w("Player", "" + spiPlayer.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        convertView.setOnClickListener(new OnItemClickListener(position));

        return convertView;
    }

    @Override
    public void onClick(View v) {
        Log.v("CustomAdapter", "=====Row button clicked");
    }

    /**
     * ****** Called when Item click in ListView ***********
     */
    private class OnItemClickListener implements OnClickListener {
        private int mPosition;

        OnItemClickListener(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {

        }
    }
}
