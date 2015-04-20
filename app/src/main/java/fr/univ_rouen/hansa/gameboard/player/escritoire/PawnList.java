package fr.univ_rouen.hansa.gameboard.player.escritoire;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

import fr.univ_rouen.hansa.exceptions.InvalidPionException;
import fr.univ_rouen.hansa.exceptions.NotEnoughSupplyException;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;

public class PawnList implements IPawnList {
    List<Trader> traders;
    List<Merchant> merchants;

    public PawnList() {
        traders = Lists.newArrayList();
        merchants = Lists.newArrayList();
    }


    @Override
    public List<Trader> getTraders() {
        return Lists.newArrayList(traders);
    }

    @Override
    public List<Merchant> getMerchants() {
        return Lists.newArrayList(merchants);
    }
    @Override
    public void addPawns(List<Pawn> pawns) {
        for (Pawn pawn : pawns) {
            if (pawn instanceof Trader) {
                traders.add((Trader) pawn);
            } else if (pawn instanceof Merchant) {
                merchants.add((Merchant) pawn);
            } else {
                throw new InvalidPionException("Expects Traders or Merchants");
            }
        }
    }

    @Override
    public List<Merchant> popMerchants(int merchantCount) {
        if (merchants.size() < merchantCount) {
            throw new NotEnoughSupplyException();
        }

        List<Merchant> rMerchant = Lists.newArrayList();

        Iterator<Merchant> merchantIterator = merchants.iterator();

        while (merchantIterator.hasNext() && rMerchant.size() < merchantCount) {
            rMerchant.add(merchantIterator.next());
            merchantIterator.remove();
        }

        return rMerchant;
    }

    @Override
    public List<Trader> popTraders(int traderCount) {
        if (traders.size() < traderCount) {
            throw new NotEnoughSupplyException();
        }

        List<Trader> rTraders = Lists.newArrayList();

        Iterator<Trader> traderIterator = traders.iterator();

        while (traderIterator.hasNext() && rTraders.size() < traderCount) {
            rTraders.add(traderIterator.next());
            traderIterator.remove();
        }

        return rTraders;
    }

    @Override
    public List<Pawn> removePawns(List<Pawn> pawns) {
        List<Pawn> save = Lists.newArrayList();

        for (Pawn pawn : pawns) {
            if (traders.remove(pawn) || merchants.remove(pawn)) {
                save.add(pawn);
            } else {
                this.addPawns(save);
                throw new NotEnoughSupplyException("Selected pawns are not in the lists");
            }
        }

        return pawns;
    }

    @Override
    public boolean enoughPawns(int merchants, int traders) {
        return merchants <= this.merchants.size() && traders <= this.traders.size();
    }

    @Override
    public int getTraderCount() {
        return traders.size();
    }

    @Override
    public int getMerchantCount() {
        return merchants.size();
    }

}
