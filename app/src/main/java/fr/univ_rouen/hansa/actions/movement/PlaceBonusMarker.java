package fr.univ_rouen.hansa.actions.movement;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusState;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;

public class PlaceBonusMarker implements IMovement {

    private IHTPlayer player;
    private IBonusMarker bonusMarker;
    private IRoute route;
    private boolean actionDone;

    public PlaceBonusMarker(IHTPlayer player, IBonusMarker bonusMarker, IRoute route) {
        if(bonusMarker == null || route == null || !route.isEmpty()){
            throw new IllegalArgumentException();
        }

        this.player = player;
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
        /*if(bonusMarker.getState() != BonusState.inPlate){
            throw new IllegalStateException("Invalid bonus state");
        }*/
        /*
        if (!player.getEscritoire().getTinPlateContent().contains(bonusMarker)) {
            throw new IllegalStateException("Player hasn't the bonus he try to play");
        }
        */

        player.getEscritoire().getTinPlateContent().remove(bonusMarker);
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
        player.getEscritoire().getTinPlateContent().add(bonusMarker);
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
