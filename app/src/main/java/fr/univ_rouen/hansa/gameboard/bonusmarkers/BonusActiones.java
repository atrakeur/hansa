package fr.univ_rouen.hansa.gameboard.bonusmarkers;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;

public class BonusActiones implements IBonusMarker {
    private BonusState state;
    private int value;

    public BonusActiones(int v) {
        super();
        if (v != 3 || v !=  4) {
            throw new IllegalArgumentException("Valeur");
        }
        state = BonusState.unused;
        this.value = v;
    }

    @Override
    public BonusState getState() {
        //TODO
        return state;
    }

    @Override
    public void doAction() {
        //TODO
        IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();
        //Todo augmenter le nombre d'action du joueur
        player.setActionNumber(getValue());
        this.state = BonusState.used;
    }

    public int getValue() {
        return value;
    }
}
