package fr.univ_rouen.hansa.actions.movement;

import com.google.common.collect.Lists;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.exceptions.NotEnoughSupplyException;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class ReplaceMovedPawn implements IMovement {
    private final IHTPlayer player;
    private final IVillage source;
    private final IVillage destination;
    private final Class<? extends Pawn> pawnType;

    private boolean actionDone;

    // Etape 1 : on déplace le pions du village source vers le village destination
    public ReplaceMovedPawn(IHTPlayer player, IVillage source, IVillage destination) {
        this.player = player;
        this.source = source;
        this.destination = destination;
        this.pawnType = null;

        actionDone = false;
    }

    // Etape 2 : on déplace le pion du stock de l'utilisateur vers le village destination
    public ReplaceMovedPawn(IHTPlayer player, IVillage destination, Class<? extends Pawn> pawnType) {
        this.player = player;
        this.source = null;
        this.destination = destination;
        this.pawnType = pawnType;

        actionDone = false;
    }

    @Override
    public boolean isDone() {
        return actionDone;
    }

    @Override
    public Actions getActionDone() {
        return Actions.movePawnRtoGB;
    }

    @Override
    public void doMovement() {
        // Etape 1
        if (source != null) {
            if (source.isEmpty() && !destination.isEmpty()) {
                throw new IllegalStateException();
            }

            if (!source.getOwner().equals(player)) {
                throw new IllegalStateException("Wrong player");
            }

            destination.pushPawn(source.pullPawn());
        }
        // Etape 2
        else if (pawnType != null) {
            if (!destination.isEmpty()) {
                throw new IllegalStateException();
            }

            if (pawnType == Trader.class && !player.getEscritoire().getSupply().enoughPawns(0, 1)) {
                throw new NotEnoughSupplyException("Need more trader for make this action");
            } else if (pawnType == Merchant.class && !player.getEscritoire().getSupply().enoughPawns(1, 0)) {
                throw new NotEnoughSupplyException("Need more merchant for make this action");
            }


            Pawn pawn;

            if (pawnType == Trader.class) {
                pawn = player.getEscritoire().getSupply().popTraders(1).get(0);
            } else {
                pawn = player.getEscritoire().getSupply().popMerchants(1).get(0);
            }

            destination.pushPawn(pawn);
        } else {
            throw new IllegalStateException();
        }

        actionDone = true;
    }

    @Override
    public void doRollback() {
        if (source != null) {
            if (!source.isEmpty() && destination.isEmpty()) {
                throw new IllegalStateException();
            }

            source.pushPawn(destination.pullPawn());
        } else if (pawnType != null) {
            if (destination.isEmpty()) {
                throw new IllegalStateException();
            }

            player.getEscritoire().getSupply().addPawns(Lists.newArrayList(destination.pullPawn()));
        } else {
            throw new IllegalStateException();
        }

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
