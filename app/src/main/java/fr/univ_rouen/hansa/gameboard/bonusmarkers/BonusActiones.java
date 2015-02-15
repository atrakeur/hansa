package fr.univ_rouen.hansa.gameboard.bonusmarkers;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

/**
 * Bonus which allow you to do 3 or 4 more Actions
 */
public class BonusActiones extends StatedBonus implements IBonusMarker {
    private int value;

    public BonusActiones(int v) {
        super();
        if (v != 3 && v !=  4) {
            throw new IllegalArgumentException("Valeur");
        }
        this.value = v;
    }

    @Override
    public void doAction() {
        super.doAction();
        IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();
        player.setActionNumber(getValue());
    }

    @Override
    public void undoAction() {
        super.undoAction();
        IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();
        int undo = -getValue();
        player.setActionNumber(undo);
    }

    public int getValue() {
        return value;
    }
}
