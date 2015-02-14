package fr.univ_rouen.hansa.actions;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public interface IChooser {
    /**
     * Allow to know for the view the number of pawn the player have to replace
     * and the number of pawns availible in the stock
     *
     * @param village the origin place of the pawn
     * @return number of pawns to replace, traders in stock and merchant in stock
     */
    public int[] replaceMovedPawnData(IVillage village);

    /**
     * Allow to know where the player can replace moved pawn
     *
     * @param source origin of the pawn to replace
     * @return list of village where the player can replace pawns
     */
    public List<IVillage> replaceMovedPawnLocation(IRoute source);
}
