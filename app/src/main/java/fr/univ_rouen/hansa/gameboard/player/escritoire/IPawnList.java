package fr.univ_rouen.hansa.gameboard.player.escritoire;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;

public interface IPawnList {
    public void addPawns(List<Pawn> pawns);
    public List<Merchant> getMerchants(int merchants);
    public List<Trader> getTraders(int traders);
}
