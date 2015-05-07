package fr.univ_rouen.hansa.gameboard.player.escritoire;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;

public interface IEscritoire {
    int INIT_CLAVIS_URBIS = 4;
    int INIT_ACTIONES = 5;
    int INIT_PRIVILEGIUM = 3;
    int INIT_LIBER_SOPHIA = 3;
    int INIT_BURSA = 3;
    int INIT_STOCK = 7;
    int INIT_SUPPLY = 4;

    List<IBonusMarker> getBonusMarker();
    
    /**
     * Return Bonus markers the player need to replace at the end of the round
     *
     * @return list of bonus marker
     */
    List<IBonusMarker> getTinPlateContent();

    /**
     * Return Bonus markers who are not used by the player
     *
     * @return the list of bonus marker that the player can play
     */
    List<IBonusMarker> getUnusedBonusMarker();

    /**
     * Return the list of bonus marker the player have already play
     *
     * @return the list of bonus marker that the player have play
     */
    List<IBonusMarker> getUsedBonusMarker();

    /**
     * Add a bonus to the list of bonus markers
     * @param bonus
     * the bonusmarkers to add
     */
    void addBonusMarker(IBonusMarker bonus);

    /**
     * Remove the bonus marker from the lists of bonus marker
     * @param marker the bonus marker to remove
     */
    void removeBonusMarker(IBonusMarker marker);

    /**
     * Remove the bonus marker from the tin plate
     * @param bonusMarker the bonus to remove
     */
    void removeTinPlate(IBonusMarker bonusMarker);

    /**
     * Add a bonus marker to the tin plate
     * @param bonusMarker the bonus marker to add
     */
    void addTinPlate(IBonusMarker bonusMarker);

    /**
     * Return the number of bonus marker the player have
     *
     * @return the number of bonus marker
     */
    int bonusMarkerCount();

    /**
     * return the level of the power clavis urbis
     *
     * @return an Integer who represent the power
     */
    int clavisUrbisLevel();

    /**
     * return the level of the power actiones
     *
     * @return an Integer who represent the power
     */
    int actionesLevel();

    /**
     * return the level of the power privilegium
     *
     * @return an Integer who represent the power
     */
    Privillegium privilegiumLevel();

    /**
     * return the level of the power liber sophia
     *
     * @return an Integer who represent the power
     */
    int liberSophiaLevel();

    /**
     * return the level of the power bursa
     *
     * @return an Integer who represent the power
     */
    int bursaLevel();

    /**
     * Increase the selected power
     *
     * @param power to increase
     */
    void increasePower(Power power);

    /**
     * Decrease the selected power
     * This action can only be done when a player undo an action
     *
     * @param power to decrease
     */
    void decreasePower(Power power);

    /**
     * Move stock (unaccessible pawns) to supply (accessible pawns)
     *
     * @param merchants number of merchants to move
     * @param traders   number of traders to move
     * @return true if the action can be done, false else
     */
    boolean moveStockToSupply(int merchants, int traders);

    /**
     * Move supply (accessible pawns) to stock (unaccessible pawns)
     *
     * @param merchants number of merchants to move
     * @param traders number of traders to move
     * @return true if the action can be done, false else
     */
    boolean moveSupplyToStock(int merchants, int traders);

    /**
     * Return a list of pawn keep from supply
     *
     * @param merchants the number of merchants
     * @param traders   the number of trader
     * @return the list of pawn with the merchants and traders
     */
    List<Pawn> popFromSupply(int merchants, int traders);

    /**
     * Remove pawn from stock
     *
     * @param pawns to remove from stock
     * @return the list of pawn who have been remove
     */
    List<Pawn> removeFromStock(List<Pawn> pawns);

    /**
     * Remove pawn from supply
     *
     * @param pawns to remove from stock
     * @return the list of pawn who have been remove
     */
    List<Pawn> removeFromSupply(List<Pawn> pawns);

    /**
     * Add the list of pawns to stock
     *
     * @param pawns list of pawns to add to stock
     */
    void addToStock(List<Pawn> pawns);

    /**
     * Add the list of pawns to supply
     *
     * @param pawns list of pawns to add to supply
     */
    void addToSupply(List<Pawn> pawns);

    /**
     * allow to know if the player have enought merchants and traders
     *
     * @param merchants the merchants count to check
     * @param traders   the traders count to check
     * @return true if the player have enought supply, false else
     */
    boolean enoughStock(int merchants, int traders);

    /**
     * Get the stock of the player
     *
     * @return the stock of the player
     */
    IPawnList getStock();

    /**
     * Get the supply of the player
     *
     * @return the supply of the player
     */
    IPawnList getSupply();

    /**
     * Return the state of all power for the save
     *
     * @return the List that contains all powers level
     */
    List<List<? extends Pawn>> savePowers();

}
