package fr.univ_rouen.hansa.actions;

import fr.univ_rouen.hansa.actions.movement.Bursa;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.actions.movement.IncreasePower;
import fr.univ_rouen.hansa.actions.movement.KeepKontor;
import fr.univ_rouen.hansa.actions.movement.KeepRoute;
import fr.univ_rouen.hansa.actions.movement.MovePawnRtoGB;
import fr.univ_rouen.hansa.actions.movement.PlayBonus;
import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.exceptions.PopupException;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.view.interactions.IClickableArea;

public class MovementFactory {
    private static MovementFactory ourInstance = new MovementFactory();

    public static MovementFactory getInstance() {
        return ourInstance;
    }

    private MovementFactory() {
    }

    /**
     * A methode for make all the movement possible in the Hansa game with two clickable area
     *
     * @param source      IClickableArea source
     * @param destination IClickableArea destination
     * @param pawnType    (optional) Pawn type for the movement MovePawnRtoGB
     * @return The movement made with Source and Destination
     * @throws PopupException when the action need a pawnType for the movePawnRtoGB movement
     */
    public IMovement makeMovement(IClickableArea source, IClickableArea destination, Class<? extends Pawn> pawnType) throws PopupException {
        IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();

        if (source.getType() == IClickableArea.Type.village && destination == null) {
            return new KeepRoute(player, ((IVillage) source.getSubject()).getRoute());
        } else if (source.getType() == IClickableArea.Type.village && destination.getType() == IClickableArea.Type.city) {
            return new KeepKontor(player, (ICity) destination.getSubject(), (IVillage) source.getSubject());
        } else if (source.getType() == IClickableArea.Type.village && destination.getType() == IClickableArea.Type.power) {
            //FIXME (07/04) non testé, destination ne réfère peut être pas à une icity
            return new IncreasePower(player, ((ICity) destination.getSubject()), ((IVillage) destination.getSubject()).getRoute());
        } else if (source.getType() == IClickableArea.Type.bonus && destination == null) {
            return new PlayBonus(((IBonusMarker) source.getSubject()));
        } else if (source.getType() == IClickableArea.Type.supply && destination.getType() == IClickableArea.Type.village) {
            //Si le type de pion est null on demande l'affiche d'une popup
            if (pawnType == null) {
                throw new PopupException(PopupException.PopupType.movementPawnRtoGB);
            } else {
                return new MovePawnRtoGB(player, (IVillage) destination.getSubject(), pawnType);
            }
        }


        throw new GameException("Invalid movement");
    }

    public IMovement makeBursaMovement(int merchant) {
        IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();

        return new Bursa(player, merchant);
    }

    public IMovement makeBursaMovement() {
        IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();

        return new Bursa(player);
    }
}
