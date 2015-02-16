package fr.univ_rouen.hansa.gameboard.player.escritoire;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;


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
    public Privillegium privilegiumLevel();
    public int liberSophiaLevel();
    public int bursaLevel();
    public void increasePower(Power power);
    public void decreasePower(Power power);

    /**
     * Move stock (unaccessible pawns) to supply (accessible pawns)
     *
     * @param merchants number of merchants to move
     * @param traders   number of traders to move
     * @return true if the action can be done, false else
     */
    public boolean moveStockToSupply(int merchants, int traders);

    /**
     * Move supply (accessible pawns) to stock (unaccessible pawns)
     *
     * @param merchants number of merchants to move
     * @param traders number of traders to move
     * @return true if the action can be done, false else
     */
    public boolean moveSupplyToStock(int merchants, int traders);

    public List<Pawn> popFromSupply(int merchants, int traders);
    public List<Pawn> removeFromStock(List<Pawn> pawns);
    public List<Pawn> removeFromSupply(List<Pawn> pawns);
    public void addToStock(List<Pawn> pawns);
    public void addToSupply(List<Pawn> pawns);
    public boolean enoughSupply(int merchants, int traders);
    public IPawnList getStock();
    public IPawnList getSupply();

}
