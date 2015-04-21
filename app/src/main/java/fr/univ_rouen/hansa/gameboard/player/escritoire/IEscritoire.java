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

    public List<IBonusMarker> getBonusMarker();
    
    /**
     * Return Bonus markers the player need to replace at the end of the round
     *
     * @return list of bonus marker
     */
    public List<IBonusMarker> getTinPlateContent();

    /**
     * Return Bonus markers who are not used by the player
     *
     * @return the list of bonus marker that the player can play
     */
    public List<IBonusMarker> getUnusedBonusMarker();

    /**
     * Return the list of bonus marker the player have already play
     *
     * @return the list of bonus marker that the player have play
     */
    public List<IBonusMarker> getUsedBonusMarker();

    /**
     * Add a bonus to the list of bonus markers
     * @param bonus
     * the bonusmarkers to add
     */
    public void addBonusMarker(IBonusMarker bonus);

    /**
     * Return the number of bonus marker the player have
     *
     * @return the number of bonus marker
     */
    public int bonusMarkerCount();

    /**
     * return the level of the power clavis urbis
     *
     * @return an Integer who represent the power
     */
    public int clavisUrbisLevel();

    /**
     * return the level of the power actiones
     *
     * @return an Integer who represent the power
     */
    public int actionesLevel();

    /**
     * return the level of the power privilegium
     *
     * @return an Integer who represent the power
     */
    public Privillegium privilegiumLevel();

    /**
     * return the level of the power liber sophia
     *
     * @return an Integer who represent the power
     */
    public int liberSophiaLevel();

    /**
     * return the level of the power bursa
     *
     * @return an Integer who represent the power
     */
    public int bursaLevel();

    /**
     * Increase the selected power
     *
     * @param power to increase
     */
    public void increasePower(Power power);

    /**
     * Decrease the selected power
     * This action can only be done when a player undo an action
     *
     * @param power to decrease
     */
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

    /**
     * Return a list of pawn keep from supply
     *
     * @param merchants the number of merchants
     * @param traders   the number of trader
     * @return the list of pawn with the merchants and traders
     */
    public List<Pawn> popFromSupply(int merchants, int traders);

    /**
     * Remove pawn from stock
     *
     * @param pawns to remove from stock
     * @return the list of pawn who have been remove
     */
    public List<Pawn> removeFromStock(List<Pawn> pawns);

    /**
     * Remove pawn from supply
     *
     * @param pawns to remove from stock
     * @return the list of pawn who have been remove
     */
    public List<Pawn> removeFromSupply(List<Pawn> pawns);

    /**
     * Add the list of pawns to stock
     *
     * @param pawns list of pawns to add to stock
     */
    public void addToStock(List<Pawn> pawns);

    /**
     * Add the list of pawns to supply
     *
     * @param pawns list of pawns to add to supply
     */
    public void addToSupply(List<Pawn> pawns);

    /**
     * allow to know if the player have enought merchants and traders
     *
     * @param merchants the merchants count to check
     * @param traders   the traders count to check
     * @return true if the player have enought supply, false else
     */
    public boolean enoughStock(int merchants, int traders);

    /**
     * Get the stock of the player
     *
     * @return the stock of the player
     */
    public IPawnList getStock();

    /**
     * Get the supply of the player
     *
     * @return the supply of the player
     */
    public IPawnList getSupply();

    /**
     * Return the state of all power for the save
     *
     * @return the List that contains all powers level
     */
    public List<List<? extends Pawn>> savePowers();

}
