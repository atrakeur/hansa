package fr.univ_rouen.hansa.actions.movement;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusState;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;

public class PlayBonus implements IMovement {

    private IBonusMarker bonusMarker;
    private boolean actionDone;

    public PlayBonus(IBonusMarker bonus){

        if(bonus == null || bonus.getState() != BonusState.onHand){
            throw new IllegalArgumentException();
        }

        this.bonusMarker = bonus;
        this.actionDone  = false;
    }

    @Override
    public boolean isDone() {
        return actionDone;
    }

    @Override
    public Actions getActionDone() {
        return Actions.playBonus;
    }

    @Override
    public void doMovement() {
        if(this.isDone() || this.bonusMarker.getState() != BonusState.onHand){
            throw new IllegalStateException();
        }

        bonusMarker.doAction();
    }

    @Override
    public void doRollback() {
        if(!this.isDone() || this.bonusMarker.getState() == BonusState.onHand){
            throw new IllegalStateException();
        }

        //??? euh undoAction ?
    }
}
