package fr.univ_rouen.hansa.GameBoard.Player.Escritoire;

import java.util.List;

import fr.univ_rouen.hansa.GameBoard.Pawns.Merchant;
import fr.univ_rouen.hansa.GameBoard.Pawns.Pawn;
import fr.univ_rouen.hansa.GameBoard.Pawns.Trader;

public interface IPawnList {
    public void addPawns(List<Pawn> pawns);
    public List<Merchant> getMerchants(int merchants) throws IllegalStateException;
    public List<Trader> getTraders(int traders) throws IllegalStateException;
}
