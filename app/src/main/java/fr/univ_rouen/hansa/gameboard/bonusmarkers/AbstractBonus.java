package fr.univ_rouen.hansa.gameboard.bonusmarkers;

/**
 * abstract Bonus which handle states
 */
public abstract class AbstractBonus implements IBonusMarker {
    private final BonusType type;
    private BonusState state;

    public AbstractBonus(BonusType type) {
        this.type = type;
        this.state = BonusState.unused;
    }

    public BonusType getType() {
        return this.type;
    }

    public String getImage() {
        return "bonus"+this.getType();
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
