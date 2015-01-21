package fr.univ_rouen.hansa.gameboard.player.escritoire;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.pawns.Trader;

public interface IPawnList {
    public void addPawns(List<Pawn> pawns);
    public List<Merchant> getMerchants(int merchants) throws IllegalStateException;
    public List<Trader> getTraders(int traders) throws IllegalStateException;
}
