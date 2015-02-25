package fr.univ_rouen.hansa.gameboard.bonusmarkers;

/**
 * abstract Bonus which handle states
 */
public abstract class StatedBonus implements IBonusMarker {
    private BonusState state;

    public StatedBonus() {
        state = BonusState.unused;

    }

    @Override
    public BonusState getState() {
        return state;
    }

    @Override
    public void setState(BonusState state) {
        if (state == null) {
            throw new NullPointerException();
        }
        this.state = state;
    }

    @Override
    public void doAction() {
        if (this.getState() != BonusState.onHand) {
            throw new IllegalStateException("Bonus must have been set");
        }
        setState(BonusState.used);
    }

    public void undoAction() {
        if (this.getState() != BonusState.used) {
            throw new IllegalStateException("Bonus must have been used");
        }
        setState(BonusState.onHand);
    }
}
