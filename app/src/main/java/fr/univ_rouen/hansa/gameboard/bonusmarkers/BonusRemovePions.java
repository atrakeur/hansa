package fr.univ_rouen.hansa.gameboard.bonusmarkers;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;

public class BonusRemovePions implements IBonusMarker {

    private BonusState state;

    public BonusRemovePions() {
        super();
        this.state = BonusState.unused;
    }
    @Override
    public BonusState getState() {
        //TODO
        return state;
    }

    @Override
    public void doAction() {
        //TODO
        //IEscritoire escritoire = TurnManager.getInstance().getCurrentPlayer().getEscritoire();
    }
}
