package fr.univ_rouen.hansa.gameboard.bonusmarkers;

/**
 * abstract Bonus wich handle states
 */
public abstract class StatedBonus implements IBonusMarker {
    private BonusState state;
    public StatedBonus(){
        state = BonusState.unused;

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
}
