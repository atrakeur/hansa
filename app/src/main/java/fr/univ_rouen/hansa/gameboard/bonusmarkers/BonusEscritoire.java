package fr.univ_rouen.hansa.gameboard.bonusmarkers;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;


/**
 * Bonus which upgrade one of your Power
 */
public class BonusEscritoire implements IBonusMarker {

    private BonusState state;
    private Power power;

    public BonusEscritoire() {
        super();
        this.state = BonusState.unused;
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
        IEscritoire escritoire = TurnManager.getInstance().getCurrentPlayer().getEscritoire();
        escritoire.increasePower(getPower());
        this.state = BonusState.used;
    }

    @Override
    public void undoAction() {
        IEscritoire escritoire = TurnManager.getInstance().getCurrentPlayer().getEscritoire();
        escritoire.decreasePower(getPower());
        this.state = BonusState.unused;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        power = p;

    }
}
