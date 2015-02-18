package fr.univ_rouen.hansa.gameboard.bonusmarkers;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;


/**
 * Bonus which upgrade one of your Power
 */
public class BonusEscritoire extends StatedBonus implements IBonusMarker {
    private Power power;
    private IHTPlayer player;

    public BonusEscritoire() {
        super();
    }

    @Override
    public void doAction() {
        super.doAction();
        IEscritoire escritoire = getPlayer().getEscritoire();
        escritoire.increasePower(getPower());
    }

    @Override
    public void undoAction() {
        super.undoAction();
        IEscritoire escritoire = getPlayer().getEscritoire();
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
    public IHTPlayer getPlayer() {
        return player;
    }
    public void setPlayer(IHTPlayer p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        player = p;
    }
}
