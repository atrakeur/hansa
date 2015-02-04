package fr.univ_rouen.hansa.gameboard.bonusmarkers;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

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

    public int getValue() {
        return value;
    }
}
