package fr.univ_rouen.hansa.actions;

import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class Chooser {
    public int[] replaceMovedPawn(IVillage village) {
        int[] values = new int[3];

        if (village.getPawnType().equals(Trader.class)) {
            values[0] = 2;
        } else if (village.getPawnType().equals(Merchant.class))  {
            values[0] = 3;
        } else {
            throw new IllegalStateException("Village empty");
        }

        values[1] = village.getOwner().getEscritoire().getSupply().getTraderCount();
        values[2] = village.getOwner().getEscritoire().getSupply().getMerchantCount();

        return values;
    }
}
