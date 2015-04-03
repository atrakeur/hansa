package fr.univ_rouen.hansa.actions.movement;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.exceptions.NotEnoughSupplyException;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

/**
 * Move Supply (accessible) to Stock (unaccessible)
 */
public class MovePawnRtoS implements IMovement {
    private final IHTPlayer player;
    private final int merchantsCount;
    private final int tradersCount;

    private boolean actionDone;

    public MovePawnRtoS(IHTPlayer player, int merchantsCount, int tradersCount) {
        this.player = player;
        this.merchantsCount = merchantsCount;
        this.tradersCount = tradersCount;

        actionDone = false;
    }

    @Override
    public boolean isDone() {
        return actionDone;
    }

    @Override
    public Actions getActionDone() {
        return Actions.movePawnRtoS;
    }

    @Override
    public void doMovement() {
        if (!player.getEscritoire().getSupply().enoughPawns(merchantsCount, tradersCount)) {
            throw new NotEnoughSupplyException();
        }

        player.getEscritoire().moveSupplyToStock(merchantsCount, tradersCount);

        actionDone = true;
    }

    @Override
    public void doRollback() {
        if (!player.getEscritoire().getStock().enoughPawns(merchantsCount, tradersCount)) {
            throw new NotEnoughSupplyException();
        }

        player.getEscritoire().moveStockToSupply(merchantsCount, tradersCount);

        actionDone = false;
    }

    @Override
    public int getPawnReplaceMove() {
        return 0;
    }

    @Override
    public int getMergeableMove() {
        return 0;
    }
}
