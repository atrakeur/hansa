package fr.univ_rouen.hansa.gameboard.player.escritoire;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.pawns.Pawn;


public interface IEscritoire {
    public static final int INIT_CLAVIS_URBIS = 4;
    public static final int INIT_ACTIONES = 5;
    public static final int INIT_PRIVILEGIUM = 3;
    public static final int INIT_LIBER_SOPHIA = 3;
    public static final int INIT_BURSA = 3;
    public static final int INIT_STOCK = 7;
    public static final int INIT_SUPPLY = 4;

    public List<IBonusMarker> getTinPlateContent();
    public List<IBonusMarker> getUnusedBonusMarker();
    public List<IBonusMarker> getUsedBonusMarker();
    public int bonusMarkerCount();
    public int clavisUrbisLevel();
    public int actionesLevel();
    public int privilegiumLevel();
    public int liberSophiaLevel();
    public int bursaLevel();
    public Pawn increaseClavisUrbis();
    public Pawn increaseActiones();
    public Pawn increasePrivilegium();
    public Pawn increaseLiberSophia();
    public Pawn increaseBursa();
    public boolean moveStockToSupply(int merchants, int traders);
    public List<Pawn> getFromSupply(int merchants, int traders);
    public List<Pawn> removeFromStock(List<Pawn> pawns);
    public void addToStock(List<Pawn> pawns);
    public void addToSupply(List<Pawn> pawns);
}
