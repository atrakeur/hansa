package fr.univ_rouen.hansa.gameboard.bonusmarkers;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;

public class BonusEscritoire implements IBonusMarker {

    private BonusState state;

    public BonusEscritoire() {
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
        Power power = Power.Actiones;
        // TODO trouver comment recuperer le pouvoir choisi par le joueur
        escritoire.increasePower(power);
        this.state = BonusState.used;
    }

    public Power getPower() {
        return null;
    }
}
