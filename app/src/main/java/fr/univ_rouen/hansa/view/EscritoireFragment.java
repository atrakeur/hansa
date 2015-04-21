package fr.univ_rouen.hansa.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.univ_rouen.hansa.R;

/**
 * Display Content of the Content of a Player's Escritoire
 */
public class EscritoireFragment extends Fragment {
    public static final String COLOR = "color";
    public static final String STOCK_TRADER = "stock_trader";
    public static final String STOCK_MERCHANT = "stock_merchant";
    public static final String SUPPLY_TRADER = "supply_trader";
    public static final String SUPPLY_MERCHANT = "supply_merchant";
    public static final String CLAVIS_URBIS = "clavis_urbis";
    public static final String PRIVILEGIUM = "privilegium";
    public static final String LIBER_SOPHIA = "liber_sophia";
    public static final String ACTIONES = "actiones";
    public static final String BURSA = "bursa";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(R.layout.layout_escritoire, container, false);

        Bundle args = getArguments();

        //Info about the Player's Stock.
        ((TextView) rootView.findViewById(R.id.nb_trader_stock)).setText("" + args.getInt(STOCK_TRADER));
        ((TextView) rootView.findViewById(R.id.nb_merchant_stock)).setText("" + args.getInt(STOCK_MERCHANT));
        System.out.println(args.getInt(STOCK_TRADER));

        //Info about the Player's Supply.
        ((TextView) rootView.findViewById(R.id.nb_trader_supply)).setText("" + args.getInt(SUPPLY_TRADER));
        ((TextView) rootView.findViewById(R.id.nb_merchant_supply)).setText("" + args.getInt(SUPPLY_MERCHANT));

        //Info about the Player's Competence.
        ((TextView) rootView.findViewById(R.id.nb_clavis_urbis)).setText("" + args.getInt(CLAVIS_URBIS));
        //rootView.findViewById(R.id.color_privilegium).setBackgroundColor(args.getInt(PRIVILEGIUM));
        ((TextView) rootView.findViewById(R.id.nb_liber_sophia)).setText("" + args.getInt(LIBER_SOPHIA));
        ((TextView) rootView.findViewById(R.id.nb_actiones)).setText("" + args.getInt(ACTIONES));

        /*
            Special Case : Bursa Level.
            If it's at its maximum, then display the infinity symbol.
        */
        String bursa = (args.getInt(BURSA) == Integer.MAX_VALUE) ? "\u221e" : "" + args.getInt(BURSA);
        ((TextView) rootView.findViewById(R.id.nb_bursa)).setText(bursa);

        return rootView;
    }

}
