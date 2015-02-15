package fr.univ_rouen.hansa.gameboard.bonusmarkers;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;


/**
 * Bonus which upgrade one of your Power
 */
public class BonusEscritoire extends StatedBonus implements IBonusMarker {
    private Power power;

    public BonusEscritoire() {
        super();
    }

    @Override
    public void doAction() {
        super.doAction();
        IEscritoire escritoire = TurnManager.getInstance().getCurrentPlayer().getEscritoire();
        escritoire.increasePower(getPower());
    }

    @Override
    public void undoAction() {
        super.undoAction();
        IEscritoire escritoire = TurnManager.getInstance().getCurrentPlayer().getEscritoire();
        escritoire.decreasePower(getPower());
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
