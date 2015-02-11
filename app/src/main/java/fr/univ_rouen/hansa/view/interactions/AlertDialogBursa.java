package fr.univ_rouen.hansa.view.interactions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

/**
 * Created by Valentin on 09/02/2015.
 */
public class AlertDialogBursa {

    private final Context context;
    private final IHTPlayer player;
    private final View dialogView;

    public AlertDialogBursa(Context context, LayoutInflater inflater, IHTPlayer player) {
        if(context == null || inflater == null || player == null){
            throw new IllegalArgumentException();
        }

        this.context = context;
        this.player = player;
        dialogView = inflater.inflate(R.layout.alert_bursa, null);

    }

    public View getView(){
        final TextView abMerNbStock, abTraNbStock, abBursaNb, abBursaMer;
        final Button bPlus, bMinus;


        abMerNbStock = (TextView) dialogView.findViewById(R.id.ab_mer_nb_stock);
        abTraNbStock = (TextView) dialogView.findViewById(R.id.ab_tra_nb_stock);
        abBursaNb    = (TextView) dialogView.findViewById(R.id.ab_bursa_nb);
        abBursaMer   = (TextView) dialogView.findViewById(R.id.ab_bursa_mer);

        bPlus = (Button) dialogView.findViewById(R.id.ab_bursa_plus);
        bMinus= (Button) dialogView.findViewById(R.id.ab_bursa_minus);

        abMerNbStock.setText(""+player.getEscritoire().getStock().getMerchantCount());
        abTraNbStock.setText(""+player.getEscritoire().getStock().getTraderCount());
        abBursaNb.setText(""+player.getEscritoire().bursaLevel());
        abBursaMer.setText("0");


        bPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nb = (Integer.parseInt(""+abBursaMer.getText())) +1;

                if(nb > player.getEscritoire().bursaLevel()){
                    Toast.makeText(context, R.string.ab_too_much_bursa, Toast.LENGTH_SHORT).show();
                    return;
                }

                if(nb > player.getEscritoire().getStock().getMerchantCount()){
                    Toast.makeText(context, R.string.ab_too_much_mer, Toast.LENGTH_SHORT).show();
                    return;
                }

                abBursaMer.setText(""+nb);

            }
        });

        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nb = (Integer.parseInt(""+abBursaMer.getText())) -1;

                if (nb < 0) {
                    Toast.makeText(context, R.string.ab_too_low, Toast.LENGTH_SHORT).show();
                    return;
                }

                abBursaMer.setText(""+nb);

            }
        });

        return dialogView;
    }

    public TextView getResult(){
        return (TextView)dialogView.findViewById(R.id.ab_bursa_mer);
    }


}
