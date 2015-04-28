package fr.univ_rouen.hansa.actions;

import fr.univ_rouen.hansa.actions.movement.Bursa;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.actions.movement.IncreasePower;
import fr.univ_rouen.hansa.actions.movement.KeepKontor;
import fr.univ_rouen.hansa.actions.movement.KeepRoute;
import fr.univ_rouen.hansa.actions.movement.MovePawnRtoGB;
import fr.univ_rouen.hansa.actions.movement.PlayBonus;
import fr.univ_rouen.hansa.actions.movement.ReplaceMovedPawn;
import fr.univ_rouen.hansa.actions.movement.ValidateMovedPawn;
import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.exceptions.PopupException;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.view.interactions.IClickableArea;

public class MovementFactory {
    private static MovementFactory ourInstance = new MovementFactory();

    public static MovementFactory getInstance() {
        return ourInstance;
    }

    private Class<? extends Pawn> pawnType;

    private MovementFactory() {
        pawnType = Trader.class;
    }

    public void setPawnType(Class<? extends Pawn> pawnType) {
        if (pawnType == null) {
            throw new IllegalArgumentException("Pawn type can't be null");
        }
        this.pawnType = pawnType;
    }

    public Class<? extends Pawn> getPawnType() {
        return pawnType;
    }

    /**
     * A method for make all the movement possible in the Hansa game with two clickable area
     *
     * @param source      IClickableArea source
     * @param destination IClickableArea destination
     * @return The movement made with Source and Destination
     * @throws PopupException when the action need a pawnType for the movePawnRtoGB movement
     */
    public IMovement makeMovement(IClickableArea source, IClickableArea destination) {
        IHTPlayer player = TurnManager.getInstance().getCurrentPlayingPlayer();

        //Sépare le cas ou on joue normal du cas ou on joue pas normal
        if (TurnManager.getInstance().getCurrentPlayingPlayer() == TurnManager.getInstance().getCurrentPlayer()) {
            return makeNormalMove(source, destination, player);
        } else {
            return makeReplaceMove(source, destination, player);
        }
    }

    private IMovement makeReplaceMove(IClickableArea source, IClickableArea destination, IHTPlayer player) {
        if (source == null && destination == null) {
            return new ValidateMovedPawn();
        }
        else if (source.getType() == IClickableArea.Type.supply && destination.getType() == IClickableArea.Type.village ) {
            return new ReplaceMovedPawn(player, (IVillage) destination.getSubject(), pawnType);
        }

        throw new GameException("Invalid movement");
    }

    private IMovement makeNormalMove(IClickableArea source, IClickableArea destination, IHTPlayer player) {
        if (source == null && destination == null) {
            //TODO Fin de partie
            throw new UnsupportedOperationException();
        } else if (source.getType() == IClickableArea.Type.village && destination == null ) {
            //Si le village est vide, on le prend, sinon c'est une prise de comptoir
            IVillage village = (IVillage) source.getSubject();

            if (village.getRoute().isTradeRoute(player)) {
                //Si la route est pleine, c'est une prise de route
                return new KeepRoute(player, village.getRoute());
            } else if (village.isEmpty() || (village.getOwner() != null && village.getOwner() != player)) {
                //Si le village est vide
                //Si le village est occupé par un joueur, mais qui n'est pas le joueur courrant
                //C'est une prise de village
                return new MovePawnRtoGB(player, village, pawnType);
            } else {
                //Tous les autres cas on sort
                throw new GameException("Invalid movement");
            }
        } else if (source.getType() == IClickableArea.Type.village && destination.getType() == IClickableArea.Type.city) {
            return new KeepKontor(player, (ICity) destination.getSubject(), (IVillage) source.getSubject());
        } else if (source.getType() == IClickableArea.Type.village && destination.getType() == IClickableArea.Type.power) {
            //FIXME (07/04) non testé, destination ne réfère peut être pas à une icity
            return new IncreasePower(player, ((ICity) destination.getSubject()), ((IVillage) destination.getSubject()).getRoute());
        } else if (source.getType() == IClickableArea.Type.bonus && destination == null) {
            return new PlayBonus(((IBonusMarker) source.getSubject()));
        } else if (source.getType() == IClickableArea.Type.supply && destination.getType() == IClickableArea.Type.village) {
            return new MovePawnRtoGB(player, (IVillage) destination.getSubject(), pawnType);
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
