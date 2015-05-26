package fr.univ_rouen.hansa.ai.strategies;

import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.actions.movement.IncreasePower;
import fr.univ_rouen.hansa.actions.movement.KeepKontor;
import fr.univ_rouen.hansa.actions.movement.LiberSophia;
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

/**
 * Stratégie de base
 *
 * Contient toutes les routines bas niveau utiles pour les différentes stratégies du jeu
 */
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
     * Check if we have enought trader that can be potentially usable
     * @param pawn
     * @return
     */
    protected boolean hasEnoughTraderInStock(int pawn) {
        if (TurnManager.getInstance().getCurrentPlayingPlayer() != this.player) {
            throw new IllegalStateException("Strategy user isn't playing now");
        }

        return player.getEscritoire().getStock().enoughPawns(0, pawn);
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
     *
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
        }
        if (village.getOwner() == null){
            //Try to move an allready placed pawn
            for (IRoute route: GameBoardFactory.getGameBoard().getRoutes()) {
                if (route != village.getRoute()) {
                    for (IVillage village1: route.getVillages()) {
                        if (village1.getOwner() == this.getPlayer()) {
                            IMovement movement = new LiberSophia(getPlayer(), village1, village);
                            return new IMovement[] {movement};
                        }
                    }
                }
            }
        }
        if (hasEnoughTraderInStock(neededTraders)){
            IMovement movement = new MovePawnRtoS(this.getPlayer(), 0, getPawnThatCanBeMovedToStock());
            return new IMovement[] {movement};
        }

        return null;
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
     * Retourne la route la moins chére pour aller vers une ville
     * @param targetCity
     * @return
     */
    protected IRoute getLessExpensiveRouteToCity(ICity targetCity) {
        int lastNeeded = Integer.MAX_VALUE;
        IRoute targetRoute = null;
        for (int i = 0; i < targetCity.getRoutes().size(); i++) {
            int neededTraders = getNeededTraderCount(targetCity.getRoutes().get(i));
            if (neededTraders < lastNeeded) {
                targetRoute = targetCity.getRoutes().get(i);
            }
        }

        return targetRoute;
    }

    /**
     * Attempt to take a power
     * @param power
     * @return one movement to take power
     */
    protected IMovement[] takePower(Power power) {
        ICity targetCity = GameBoardFactory.getGameBoard().getCityByPower(Power.Actiones);

        //choose the route that cost less to take
        IRoute targetRoute = getLessExpensiveRouteToCity(targetCity);

        //Attempt to take that route
        IMovement[] movements = fullfillRoute(targetRoute);
        if (movements != null) {
            return movements;
        }

        //Route is allready taken? take the power
        IMovement movement = new IncreasePower(getPlayer(), targetCity, targetRoute);
        return new IMovement[] {movement};
    }

    /**
     * Tente de prendre un comptoir
     *
     * Prend un privillegium si le privillegium de l'IA est trop bas
     *
     * @param targetCity
     * @return
     */
    protected IMovement[] takeKontor(ICity targetCity) {
        IKontor kontor = targetCity.getNextKontor();
        if (!kontor.isEmpty()) {
            throw new IllegalStateException("Kontor not empty");
        }

        //Privillegium trop bas? on prend le pouvoir plutot
        if (!getPlayer().getEscritoire().privilegiumLevel().isBetterThan(kontor.getPrivillegium())) {
            return this.takePower(Power.Privillegium);
        }

        //Privillegium okay? on prepare la prise de comptoir
        //Si le kontoir a besoin d'un Merchant
        //TODO placer un merchant sur la route
        //Pour le moment on supporte pas ça!
        if (kontor.getPawnClass().equals(Merchant.class)) {
            return null;
        }

        //choose the route that cost less to take
        IRoute targetRoute = getLessExpensiveRouteToCity(targetCity);

        //Attempt to take that route
        IMovement[] movements = fullfillRoute(targetRoute);
        if (movements != null) {
            return movements;
        }

        //Route is allready taken? take the kontor
        IMovement movement = new KeepKontor(getPlayer(), targetCity, targetRoute.getVillage(0));
        return new IMovement[] {movement};
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
