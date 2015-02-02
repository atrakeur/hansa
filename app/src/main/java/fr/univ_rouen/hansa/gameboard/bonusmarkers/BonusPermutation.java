package fr.univ_rouen.hansa.gameboard.bonusmarkers;

public class BonusPermutation implements IBonusMarker {

    private BonusState state;

    public BonusPermutation() {
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

    }
}
