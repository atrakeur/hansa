package fr.univ_rouen.hansa.actions;

import fr.univ_rouen.hansa.actions.movement.Bursa;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.actions.movement.KeepKontor;
import fr.univ_rouen.hansa.actions.movement.KeepRoute;
import fr.univ_rouen.hansa.actions.movement.LiberSophia;
import fr.univ_rouen.hansa.actions.movement.MovePawnRtoGB;
import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.exceptions.ReplaceMovedPawn;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.gameboard.routes.Village;
import fr.univ_rouen.hansa.view.interactions.IClickableArea;

public class MovementFactory {
    private static MovementFactory ourInstance = new MovementFactory();

    public static MovementFactory getInstance() {
        return ourInstance;
    }

    private MovementFactory() {
    }

    public IMovement makeMovement(IClickableArea source, IClickableArea destination) {
        IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();

        if (source.getType() == IClickableArea.Type.bonus && destination == null ) {
            //TODO PlayBonus Bonus -> null
            throw new UnsupportedOperationException();
        } else if (source.getType() == IClickableArea.Type.village && destination == null ) {
            return new KeepRoute(player, ((IVillage) source.getSubject()).getRoute());
        } else if (source.getType() == IClickableArea.Type.village && destination.getType() == IClickableArea.Type.village ) {
            //TODO Check avec les differents players
            return new LiberSophia(player, (IVillage) source.getSubject(), (IVillage) destination.getSubject());
        } else if (source.getType() == IClickableArea.Type.village && destination.getType() == IClickableArea.Type.power ) {
            //TODO IncreasePower Village -> Power
            throw new UnsupportedOperationException();
        } else if (source.getType() == IClickableArea.Type.supply && destination.getType() == IClickableArea.Type.village ) {
            //TODO enlever trader en dur
            if (((Village)destination.getSubject()).isEmpty()) {
                return new MovePawnRtoGB(player, (IVillage) destination.getSubject(), Trader.class);
            } else {
                throw new ReplaceMovedPawn(destination);
            }
        } else if (source.getType() == IClickableArea.Type.village && destination.getType() == IClickableArea.Type.city ) {
            return new KeepKontor(player, (ICity) destination.getSubject(), (IVillage) source.getSubject());
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
