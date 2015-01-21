package fr.univ_rouen.hansa.GameBoard.Player.Escritoire;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

import fr.univ_rouen.hansa.GameBoard.Pawns.Merchant;
import fr.univ_rouen.hansa.GameBoard.Pawns.Pawn;
import fr.univ_rouen.hansa.GameBoard.Pawns.Trader;

public class PawnList implements IPawnList {
    List<Trader> traders;
    List<Merchant> merchants;

    public PawnList() {
        traders = Lists.newArrayList();
        merchants = Lists.newArrayList();
    }

    @Override
    public void addPawns(List<Pawn> pawns) {
        for (Pawn pawn : pawns) {
            if (pawn instanceof Trader) {
                traders.add((Trader) pawn);
            } else if (pawn instanceof Merchant) {
                merchants.add((Merchant) pawn);
            } else {
                //FIXME add exceptions
            }
        }
    }

    @Override
    public List<Merchant> getMerchants(int merchantCount) throws IllegalStateException {
        if (merchants.size() >= merchantCount) {
            throw new IllegalStateException(); //FIXME add exception
        }

        List<Merchant> rMerchant = Lists.newArrayList();

        Iterator<Merchant> merchantIterator = merchants.iterator();

        while (merchantIterator.hasNext() && rMerchant.size() >= merchantCount) {
            rMerchant.add(merchantIterator.next());
            merchantIterator.remove();
        }

        return rMerchant;
    }

    @Override
    public List<Trader> getTraders(int traderCount) throws IllegalStateException {
        if (merchants.size() >= traderCount) {
            throw new IllegalStateException();  //FIXME add exception
        }

        List<Trader> rTraders = Lists.newArrayList();

        Iterator<Trader> traderIterator = traders.iterator();

        while (traderIterator.hasNext() && rTraders.size() >= traderCount) {
            rTraders.add(traderIterator.next());
            traderIterator.remove();
        }

        return rTraders;
    }
}
