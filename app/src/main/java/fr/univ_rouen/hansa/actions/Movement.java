package fr.univ_rouen.hansa.actions;

import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.view.ClickableArea;
import fr.univ_rouen.hansa.view.IClickableArea;

public class Movement implements IMovement {

    private final boolean done;
    private final IMovement movement;

    public Movement(ClickableArea source, ClickableArea destination) {
        done = false;

        //wait interface
        movement = new IncreasePower();
        /*if (source.getType() == IClickableArea.Type.bonus && destination == null ) {
            //TODO PlayBonus Bonus -> null
        } else if (source.getType() == IClickableArea.Type.village && destination == null ) {
            //TODO KeepRoute Village -> null
        } else if (source.getType() == IClickableArea.Type.village && destination.getType() == IClickableArea.Type.village ) {
            //TODO LiberSophia Village -> Village
        } else if (source.getType() == IClickableArea.Type.village && destination.getType() == IClickableArea.Type.power ) {
            //TODO IncreasePower Village -> Power
        } else if (source.getType() == IClickableArea.Type.supply && destination.getType() == IClickableArea.Type.village ) {
            //TODO MovePawnRtoGB Supply -> Village
        } else if (source.getType() == IClickableArea.Type.village && destination.getType() == IClickableArea.Type.city ) {
            //TODO KeepKontor Village -> Ville
        } else if (source.getType() == IClickableArea.Type.stock && destination.getType() == IClickableArea.Type.supply ) {
            //TODO MovePawStoR Stock -> Supply
        } else {
            throw new GameException("Invalid movement");
        }*/

    }

    @Override
    public boolean isDone() {
        return movement.isDone();
    }

    @Override
    public Actions getActionDone() {
        return movement.getActionDone();
    }

    @Override
    public void doMovement() {
        movement.doMovement();
    }

    @Override
    public void doRollback() {
        movement.doRollback();
    }

}
