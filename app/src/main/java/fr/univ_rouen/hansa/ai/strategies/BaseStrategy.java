package fr.univ_rouen.hansa.ai.strategies;

import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.actions.movement.IncreasePower;
import fr.univ_rouen.hansa.actions.movement.MovePawnRtoGB;
import fr.univ_rouen.hansa.actions.movement.MovePawnRtoS;
import fr.univ_rouen.hansa.ai.ComputerStrategy;
import fr.univ_rouen.hansa.ai.StrategyType;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
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

        return player.getEscritoire().getSupply().enoughPawns(0, pawn);
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

        return player.getEscritoire().getSupply().enoughPawns(pawn, 0);
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
     * Create movements to take a village
     * @param village
     * @return one movement to take the village (or null if already taken)
     */
    protected IMovement[] takeVillage(IVillage village) {
        if (village.getOwner() == getPlayer()) {
            return null;
        }

        int neededTraders = getNeededTraderCount(village);

        if (hasEnoughTrader(neededTraders)) {
            IMovement movement = new MovePawnRtoGB(this.getPlayer(), village, Trader.class);
            return new IMovement[] {movement};
        } else {
            IMovement movement = new MovePawnRtoS(this.getPlayer(), 0, getPawnThatCanBeMovedToStock());
            return new IMovement[] {movement};
        }
    }

    /**
     * Fullfill a route with our pawn
     * @param route
     * @return one movement to try to take a route, or null if already full
     */
    protected IMovement[] fullfillRoute(IRoute route) {
        int neededTraders = getNeededTraderCount(route);

        if (hasEnoughTrader(neededTraders)) {
            for (int i = 0; i < route.getVillages().size(); i++) {
                if (route.getVillage(i).getOwner() != getPlayer()) {
                    return this.takeVillage(route.getVillage(i));
                }
            }
            return null;
        } else {
            IMovement movement = new MovePawnRtoS(this.getPlayer(), 0, getPawnThatCanBeMovedToStock());
            return new IMovement[] {movement};
        }
    }

    /**
     * Attemp to take a power
     * @param power
     * @return one movement to take power
     */
    protected IMovement[] takePower(Power power) {
        ICity targetCity = GameBoardFactory.getGameBoard().getCityByPower(Power.Actiones);

        //choose the route that cost less to take
        int lastNeeded = Integer.MAX_VALUE;
        IRoute targetRoute = null;
        for (int i = 0; i < targetCity.getRoutes().size(); i++) {
            int neededTraders = getNeededTraderCount(targetCity.getRoutes().get(i));
            if (neededTraders < lastNeeded) {
                targetRoute = targetCity.getRoutes().get(i);
            }
        }

        //Attempt to take that route
        IMovement[] movements = fullfillRoute(targetRoute);
        if (movements != null) {
            return movements;
        }

        //Route is allready taken? take the power
        IMovement movement = new IncreasePower(getPlayer(), targetCity, targetRoute);
        return new IMovement[] {movement};
    }

    protected IMovement[] takeKontor(ICity city, int i) {
        IKontor kontor = city.getKontors().get(i);

        return null;
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
