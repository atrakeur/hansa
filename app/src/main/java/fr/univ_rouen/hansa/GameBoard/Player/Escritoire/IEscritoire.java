package fr.univ_rouen.hansa.GameBoard.Player.Escritoire;

import java.util.List;

import fr.univ_rouen.hansa.GameBoard.BonusMarkers.BonusMarker;
import fr.univ_rouen.hansa.GameBoard.Pawns.Merchant;
import fr.univ_rouen.hansa.GameBoard.Pawns.Pawn;
import fr.univ_rouen.hansa.GameBoard.Pawns.Trader;

/**
 * Created by vivienlegrand on 20/01/15.
 */
public interface IEscritoire {
    public List<BonusMarker> getTinPlateContent();
    public List<BonusMarker> getUnusedBonusMarker();
    public List<BonusMarker> getUsedBonusMarker();
    public int bonusMarkerCount();
    public int clavisUrbisLevel();
    public int actionesLevel();
    public int privilegiumLevel();
    public int liberSophiaLevel();
    public int bursaLevel();
    public Trader increaseClavisUrbis();
    public Trader increaseActiones();
    public Trader increasePrivilegium();
    public Merchant increaseLiberSophia();
    public Trader increaseBursa();
    public boolean moveStockToReserve(int merchants, int traders);
    public void addToStock(List<Pawn> pawns);
    public void addToReserve(List<Pawn> pawns);
}
