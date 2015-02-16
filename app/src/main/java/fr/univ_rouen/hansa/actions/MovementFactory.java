package fr.univ_rouen.hansa.actions;

import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.view.interactions.IClickableArea;

public class MovementFactory {
    private static MovementFactory ourInstance = new MovementFactory();

    public static MovementFactory getInstance() {
        return ourInstance;
    }

    private MovementFactory() {
    }

    private IMovement makeMovement(IClickableArea source, IClickableArea destination) {
        IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();

        if (source.getType() == IClickableArea.Type.bonus && destination == null ) {
            //TODO PlayBonus Bonus -> null
            throw new UnsupportedOperationException();
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
        }

        throw new GameException("Invalid movement");
    }
}
