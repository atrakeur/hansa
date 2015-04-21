package fr.univ_rouen.hansa.gameboard.bonusmarkers;

import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

/**
 * Bonus which allow you to do 3 or 4 more Actions
 */
public class BonusActiones extends AbstractBonus implements IBonusMarker {
    private int value;
    private IHTPlayer player;

    public BonusActiones(int v) {
        super(BonusType.BonusActiones);
        if (v != 3 && v != 4) {
            throw new IllegalArgumentException("Valeur");
        }
        this.value = v;
    }

    @Override
    public void doAction() {
        super.doAction();
        player.setActionNumber(getValue());
    }

    @Override
    public void undoAction() {
        super.undoAction();
        int undo = -getValue();
        player.setActionNumber(undo);
    }

    public int getValue() {
        return value;
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
