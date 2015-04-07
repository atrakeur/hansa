package fr.univ_rouen.hansa.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.activity.GameActivity;
import fr.univ_rouen.hansa.view.utils.ListModel;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class CustomAdapter extends BaseAdapter   implements OnClickListener {

    /*********** Declare Used Variables *********/
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    ListModel tempValues = null;
    int i = 0;

    /*************  CustomAdapter Constructor *****************/
    public CustomAdapter(Activity a, ArrayList d,Resources resLocal) {

        /********** Take passed values **********/
        activity = a;
        data = d;
        res = resLocal;

        /***********  Layout inflator to call external xml layout () **********************/
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {

        if(data.size()<=0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder to contain inflated xml file elements ***********/
    public static class ViewHolder{

        public Spinner text;
        public Spinner text1;
        public TextView player;

    }

    /*********** Depends upon data size called for each row , Create each ListView row ***********/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi=convertView;
        ViewHolder holder;

        if (convertView == null){

            /********** Inflate tabitem.xml file for each row ( Defined below ) ************/
            vi = inflater.inflate(R.layout.tabitem, null);

            /******** View Holder Object to contain tabitem.xml file elements ************/
            holder=new ViewHolder();
            holder.text = (Spinner) vi.findViewById(R.id.player);
            holder.text1=(Spinner)vi.findViewById(R.id.color);
            holder.player=(TextView)vi.findViewById(R.id.text);

            /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);
        } else
            holder=(ViewHolder)vi.getTag();
            /***** Get each Model object from Arraylist ********/
            tempValues = null;
            tempValues = (ListModel) data.get(position);

            /************  Set Model values in Holder elements ***********/
            holder.player.setText("Joueur :");
            /******** Set Item Click Listner for LayoutInflater for each row ***********/
            vi.setOnClickListener(new OnItemClickListener(position));
        return vi;
    }

    @Override
    public void onClick(View v) {
        Log.v("CustomAdapter", "=====Row button clicked");
    }

    /********* Called when Item click in ListView ************/
    private class OnItemClickListener  implements OnClickListener{
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {
            GameActivity sct = (GameActivity)activity;
            sct.onItemClick(mPosition);
        }
    }
}
