package fr.univ_rouen.hansa.gameboard.bonusmarkers;

import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;


/**
 * Bonus which upgrade one of your Power
 */
public class BonusEscritoire extends AbstractBonus implements IBonusMarker {
    private Power power;
    private IHTPlayer player;

    public BonusEscritoire() {
        super(BonusType.BonusEscritoire);
    }

    @Override
    public void doAction() {
        IEscritoire escritoire = getPlayer().getEscritoire();
        escritoire.increasePower(getPower());
        super.doAction();
    }

    @Override
    public void undoAction() {
        IEscritoire escritoire = getPlayer().getEscritoire();
        escritoire.decreasePower(getPower());
        super.undoAction();
    }

    @Override
    public void accept(IVisitorBonusMarker visitorBonusMarker) {
        visitorBonusMarker.visit(this);
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
