package fr.univ_rouen.hansa.gameboard.player.escritoire;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusMarker;
import fr.univ_rouen.hansa.gameboard.pawns.Pawn;


public interface IEscritoire {
    public static final int INIT_CLAVIS_URBIS = 4;
    public static final int INIT_ACTIONES = 4;
    public static final int INIT_PRIVILEGIUM = 3;
    public static final int INIT_LIBER_SOPHIA = 3;
    public static final int INIT_BURSA = 3;

    public List<BonusMarker> getTinPlateContent();
    public List<BonusMarker> getUnusedBonusMarker();
    public List<BonusMarker> getUsedBonusMarker();
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
    public boolean moveStockToReserve(int merchants, int traders);
    public void addToStock(List<Pawn> pawns);
    public void addToReserve(List<Pawn> pawns);
}
