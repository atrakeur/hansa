package fr.univ_rouen.hansa.gameboard.bonusmarkers;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

/**
 * Bonus which allow you to do 3 or 4 more Actions
 */
public class BonusActiones implements IBonusMarker {
    private BonusState state;
    private int value;

    public BonusActiones(int v) {
        super();
        if (v != 3 && v !=  4) {
            throw new IllegalArgumentException("Valeur");
        }
        state = BonusState.unused;
        this.value = v;
    }

    @Override
    public BonusState getState() {
        return state;
    }

    @Override
    public void setState(BonusState state) {
        if (state == null){
            throw new NullPointerException();
        }
        this.state = state;
    }

    @Override
    public void doAction() {
        IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();
        player.setActionNumber(getValue());
        this.state = BonusState.used;
    }

    @Override
    public void undoAction() {
        IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();
        int undo = -getValue();
        player.setActionNumber(undo);
        this.state = BonusState.unused;
    }

    public int getValue() {
        return value;
    }
}
