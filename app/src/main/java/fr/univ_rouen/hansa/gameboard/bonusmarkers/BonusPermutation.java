package fr.univ_rouen.hansa.gameboard.bonusmarkers;


/**
 * Bonus which allow you to permute two Kontor in a City
 */
public class BonusPermutation implements IBonusMarker {

    private BonusState state;

    public BonusPermutation() {
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
        //TODO

    }
    public void undoAction() {

    }
}
