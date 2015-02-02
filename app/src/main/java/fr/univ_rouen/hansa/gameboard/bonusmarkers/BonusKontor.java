package fr.univ_rouen.hansa.gameboard.bonusmarkers;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.cities.Kontor;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;

public class BonusKontor implements IBonusMarker {

    private BonusState state;

    public BonusKontor() {
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
        IEscritoire escritoire = TurnManager.getInstance().getCurrentPlayer().getEscritoire();

    }
}
