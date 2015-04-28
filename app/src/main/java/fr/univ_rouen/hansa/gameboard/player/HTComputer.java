package fr.univ_rouen.hansa.gameboard.player;

import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.activity.GameActivity;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;
import fr.univ_rouen.hansa.gameboard.player.strategies.Strategy;

public class HTComputer extends HTPlayer {

    private volatile Thread runThread;
    private volatile boolean run;

    private ComputerStrategy strategy;

    /**
     * Init a AI from a safeguard
     *
     * @param color of the AI
     * @param escritoire of the AI
     * @param action number of action playable of the AI
     * @param strategy of the AI
     */
    public HTComputer(PlayerColor color, IEscritoire escritoire, int action, ComputerStrategy strategy) {
        super(color, escritoire, action);

        this.strategy = strategy;
        this.strategy.setPlayer(this);
    }

    /**
     * Init a AI for a new game
     *
     * @param color of the AI
     * @param startingPlace of the AI
     * @param strategy of the AI
     */
    public HTComputer(PlayerColor color, int startingPlace, ComputerStrategy strategy) {
        super(color, startingPlace);

        this.strategy = strategy;
        this.strategy.setPlayer(this);
    }

    @Override
    public void newTurn() {
        super.newTurn();

        run = true;
        runThread = new Thread(new Runnable() {
            @Override
            public void run() {
                final String orgName = Thread.currentThread().getName();
                Thread.currentThread().setName(orgName + " - AI " + getPlayerColor().name());
                while (run) {
                    //Wait for it
                    do {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while(TurnManager.getInstance().getCurrentPlayingPlayer() != HTComputer.this);

                    //Get movements from strategy
                    IMovement[] movements = strategy.compute(GameBoardFactory.getGameBoard());
                    //Use movements (sould be safe cause we are awesome!)

                    for (int i = 0; i < movements.length; i++) {
                        MovementManager.getInstance().doMove(movements[i]);
                    }

                    if (TurnManager.getInstance().isNextTurnAvailable() == TurnManager.nextTurnRequire.bonusMarkers) {
                        //Got bonus markers to replace
                        //TODO
                    }
                    if (TurnManager.getInstance().isNextTurnAvailable() == TurnManager.nextTurnRequire.none) {
                        //Got no more actiones? ready to move your fucking ass out of the way
                        run = false;
                        GameActivity.getInstance().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TurnManager.getInstance().nextPlayer(true);
                            }
                        });
                    }
                }
            }
        });
        runThread.start();
    }

    public Strategy getStrategy() {
        return strategy.getStrategy();
    }
}
