package fr.univ_rouen.hansa.actions.movement;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusState;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;

public class PlaceBonusMarker implements IMovement {

    private IBonusMarker bonusMarker;
    private IRoute route;
    private boolean actionDone;

    public PlaceBonusMarker(IBonusMarker bonusMarker, IRoute route) {
        if(bonusMarker == null || route == null || !route.isEmpty() || bonusMarker.getState() != BonusState.inPlate ){
            throw new IllegalArgumentException();
        }

        this.bonusMarker = bonusMarker;
        this.route = route;
    }

    @Override
    public boolean isDone() {
        return actionDone;
    }

    @Override
    public Actions getActionDone() {
        return Actions.placeBonusMarker;
    }

    @Override
    public void doMovement() {
        if(actionDone){
            throw new IllegalStateException("Action already done");
        }
        if(!route.isEmpty() || route.getBonusMarker() != null){
            throw new IllegalStateException("Route not empty");
        }
        if(bonusMarker.getState() != BonusState.inPlate){
            throw new IllegalStateException("Invalid bonus state");
        }

        route.pushBonusMarker(bonusMarker);
        bonusMarker.setState(BonusState.onBoard);

        actionDone = true;
    }

    @Override
    public void doRollback() {

        if(!actionDone){
            throw new IllegalStateException("Action already undone");
        }
        if(bonusMarker.getState() != BonusState.onBoard){
            throw new IllegalStateException("Invalid bonus state");
        }

        route.popBonusMarker();
        bonusMarker.setState(BonusState.inPlate);
        actionDone = false;
    }

    @Override
    public Pawn getPawnToReplace() {
        return null;
    }

    @Override
    public int getMergeableMove() {
        return 0;
    }
}
