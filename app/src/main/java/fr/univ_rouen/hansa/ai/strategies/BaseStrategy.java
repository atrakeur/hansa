package fr.univ_rouen.hansa.ai.strategies;

import fr.univ_rouen.hansa.ai.ComputerStrategy;
import fr.univ_rouen.hansa.ai.StrategyType;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public abstract class BaseStrategy implements ComputerStrategy {

    private final StrategyType strategyType;

    protected BaseStrategy(StrategyType strategyType) {
        this.strategyType = strategyType;
    }

    /**
     * Check if current user has enough moves left
     *
     * @param move
     * @return
     */
    protected boolean hasEnoughMoves(int move) {
        if (TurnManager.getInstance().getCurrentPlayingPlayer() != this.player) {
            throw new IllegalStateException("Strategy user isn't playing now");
        }

        return TurnManager.getInstance().actionLeft() >= move;
    }

    /**
     * Check if current user has enough trader left
     *
     * @param pawn
     * @return
     */
    protected boolean hasEnoughTrader(int pawn) {
        if (TurnManager.getInstance().getCurrentPlayingPlayer() != this.player) {
            throw new IllegalStateException("Strategy user isn't playing now");
        }

        return this.player.getEscritoire().getSupply().getTraderCount() >= pawn;
    }

    /**
     * Check if current user has enough merchant left
     *
     * @param pawn
     * @return
     */
    protected boolean hasEnoughMerchant(int pawn) {
        if (TurnManager.getInstance().getCurrentPlayingPlayer() != this.player) {
            throw new IllegalStateException("Strategy user isn't playing now");
        }

        return this.player.getEscritoire().getSupply().getMerchantCount() >= pawn;
    }

    /**
     * Retourne le nombre de trader nécéssaires pour prendre une route
     * <p/>
     * Calcule 1 trader par village a prendre + X trader (quand on doit remplacer des pions enemis)
     *
     * @param route
     * @return
     */
    protected int getNeededTraderCount(IRoute route) {
        int counter = 0;

        for (IVillage village : route.getVillages()) {
            counter += getNeededTraderCount(village);
        }

        return counter;
    }

    /**
     * Retourne le nombre de trader nécéssaires pour prendre ce village
     * <p/>
     * Calcule 1 ou 0 si le village est vide/déjà pris
     * Calcule 1 + X pions si le village doit etre pris
     *
     * @param village
     * @return
     */
    protected int getNeededTraderCount(IVillage village) {
        if (village.isEmpty()) {
            return 1;
        } else {
            if (village.getOwner() != this.player) {
                if (village.getPawnType().equals(Trader.class)) {
                    return 2;
                } else {
                    return 3;
                }
            }
        }

        return 0;
    }

    /**
     * Get max number of pawn that can be moved to stock
     *
     * @return
     */
    public int getPawnThatCanBeMovedToStock() {
        return Math.min(player.getEscritoire().bursaLevel(), player.getEscritoire().getStock().getTraderCount());
    }

    private IHTPlayer player;

    public IHTPlayer getPlayer() {
        return player;
    }

    public void setPlayer(IHTPlayer player) {
        this.player = player;
    }

    @Override
    public StrategyType getStrategyType() {
        return strategyType;
    }
}
