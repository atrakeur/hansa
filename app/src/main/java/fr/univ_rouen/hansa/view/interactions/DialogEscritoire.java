package fr.univ_rouen.hansa.view.interactions;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;
import fr.univ_rouen.hansa.view.CircleView;

/**
 * The class display the escritoire of the player
 */
public class DialogEscritoire extends Dialog {

    /**
     * Constructor of DialogEscritoire
     *
     * @param context the context use by the dialog
     * @param player  the player with the information to display
     */
    public DialogEscritoire(Context context, IHTPlayer player) {
        super(context);
        init(player);
    }

    /**
     * Initialize the Dialog with the information from the specified player
     *
     * @param player the player with the information to display
     */
    private void init(IHTPlayer player) {

        this.setTitle(R.string.esc_title);
        this.setContentView(R.layout.list_escritoire);
        int color = player.getPlayerColor().getColor();
        IEscritoire esc = player.getEscritoire();

        //Info about the Player's Stock.
        this.findViewById(R.id.color_trader_stock).setBackgroundColor(color);
        ((TextView) this.findViewById(R.id.nb_trader_stock)).setText("x" + esc.getStock().getTraderCount());
        ((CircleView) this.findViewById(R.id.color_merchant_stock)).setCircleFillColor(color);
        ((TextView) this.findViewById(R.id.nb_merchant_stock)).setText("x" + esc.getStock().getMerchantCount());

        //Info about the Player's Supply.
        this.findViewById(R.id.color_trader_supply).setBackgroundColor(color);
        ((TextView) this.findViewById(R.id.nb_trader_supply)).setText("x" + esc.getSupply().getTraderCount());
        ((CircleView) this.findViewById(R.id.color_merchant_supply)).setCircleFillColor(color);
        ((TextView) this.findViewById(R.id.nb_merchant_supply)).setText("x" + esc.getSupply().getMerchantCount());

        //Info about the Player's Competence.
        ((TextView) this.findViewById(R.id.nb_clavis_urbis)).setText("" + String.format("%2d", esc.clavisUrbisLevel()));
        this.findViewById(R.id.color_privilegium).setBackgroundColor(esc.privilegiumLevel().getColor());
        ((TextView) this.findViewById(R.id.nb_liber_sophia)).setText("" + String.format("%2d", esc.liberSophiaLevel()));
        ((TextView) this.findViewById(R.id.nb_actiones)).setText("" + esc.actionesLevel());

        /*
            Special Case : Bursa Level.
            If it's at its maximum, then display the infinity symbol.
        */
        String bursa = (esc.bursaLevel() == Integer.MAX_VALUE) ? "\u221e" : "" + esc.bursaLevel();
        ((TextView) this.findViewById(R.id.nb_bursa)).setText(bursa);
    }

}
