package fr.univ_rouen.hansa.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;

public class EscritoirePagerAdapter extends FragmentStatePagerAdapter {

    public EscritoirePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        List<IHTPlayer> players = TurnManager.getInstance().getPlayers();

        Fragment fragment = new EscritoireFragment();
        Bundle args = new Bundle();
        IHTPlayer player = players.get((position + TurnManager.getInstance().getPosition()) % players.size());
        IEscritoire esc = player.getEscritoire();

        args.putInt(EscritoireFragment.COLOR, player.getPlayerColor().getColor());
        args.putInt(EscritoireFragment.STOCK_MERCHANT, esc.getStock().getMerchantCount());
        args.putInt(EscritoireFragment.STOCK_TRADER, esc.getStock().getTraderCount());
        args.putInt(EscritoireFragment.SUPPLY_MERCHANT, esc.getSupply().getMerchantCount());
        args.putInt(EscritoireFragment.SUPPLY_TRADER, esc.getSupply().getTraderCount());
        args.putInt(EscritoireFragment.CLAVIS_URBIS, esc.clavisUrbisLevel());
        args.putInt(EscritoireFragment.PRIVILEGIUM, esc.privilegiumLevel().ordinal());
        args.putInt(EscritoireFragment.LIBER_SOPHIA, esc.liberSophiaLevel());
        args.putInt(EscritoireFragment.ACTIONES, esc.actionesLevel());
        args.putInt(EscritoireFragment.BURSA, esc.bursaLevel());

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        return TurnManager.getInstance().getPlayers().size();
    }
}
