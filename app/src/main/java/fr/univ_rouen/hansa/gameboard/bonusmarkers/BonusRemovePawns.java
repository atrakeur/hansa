package fr.univ_rouen.hansa.gameboard.bonusmarkers;

public class BonusRemovePawns implements IBonusMarker {

    private BonusState state;

    public BonusRemovePawns() {
        super();
        this.state = BonusState.unused;
    }
    @Override
    public BonusState getState() {
        //TODO
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
        //IEscritoire escritoire = TurnManager.getInstance().getCurrentPlayer().getEscritoire();
    }
}
