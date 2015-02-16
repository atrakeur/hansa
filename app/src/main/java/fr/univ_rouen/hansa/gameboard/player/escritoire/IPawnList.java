package fr.univ_rouen.hansa.gameboard.player.escritoire;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;

public interface IPawnList {

    /**
     * Add the given pawns arguments to the list of pawns
     * @param pawns list of pawns to be added
     * */
    public void addPawns(List<Pawn> pawns);

    /**
     * Return a list of Merchant with the number of merchants specified
     *
     * @param merchants Number of merchants to return
     * @return a list with the number of merchants specified
     * @throws fr.univ_rouen.hansa.exceptions.NotEnoughSupplyException if there is not enough merchants in the list.
     * */
    public List<Merchant> popMerchants(int merchants);

    /**
     * Return a list of Merchant with the number of traders specified
     *
     * @param traders Number of traders to return
     * @return a list with the number of traders specified
     * @throws fr.univ_rouen.hansa.exceptions.NotEnoughSupplyException if there is not enough traders in the list.
     * */
    public List<Trader> popTraders(int traders);

    /**
     * Remove and return the pawns argument from the list of pawns
     * @return the given pawns
     * @throws fr.univ_rouen.hansa.exceptions.NotEnoughSupplyException if there is not enough pawns in the list or one pawns is not in the list.
     * */
    public List<Pawn> removePawns(List<Pawn> pawns);

    /**
     * Test if there is enough merchants and trader in the list.
     * @return true if there is enough pawns
     * */
    public boolean enoughPawns(int merchants, int traders);

    /**
     * Return the number of traders
     * @return the number of traders
     * */
    public int getTraderCount();

    /**
     * Return the number of merchants
     * @return the number of merchants
     * */
    public int getMerchantCount();
}
