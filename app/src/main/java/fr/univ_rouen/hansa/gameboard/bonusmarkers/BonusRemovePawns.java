package fr.univ_rouen.hansa.gameboard.bonusmarkers;


/**
 * Bonus which allow you to remove 3 pawns from the Routes
 */
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
