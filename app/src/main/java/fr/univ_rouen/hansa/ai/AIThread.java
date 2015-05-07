package fr.univ_rouen.hansa.ai;

import android.util.Log;
import android.view.SurfaceHolder;

import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.activity.GameActivity;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.player.HTComputer;
import fr.univ_rouen.hansa.view.GameBoardView;

public class AIThread extends Thread{

    private volatile boolean running;
    private volatile Thread threadInstance;

    private GameBoardView view;
    private SurfaceHolder holder;


    public AIThread(GameBoardView view, SurfaceHolder holder) {
        this.view = view;
        this.holder = holder;

        final AIThread controlThread = this;

        view.getHolder().addCallback(new SurfaceHolder.Callback() {
            public void surfaceCreated(SurfaceHolder holder) {
                Log.w("AI", "AIThread starting...");
                threadInstance = new Thread(AIThread.this);
                controlThread.setRunning(true);
                threadInstance.start();
                Log.w("AI", "AIThread started...");
            }

            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                controlThread.setRunning(false);
                Log.w("AI", "AIThread stopping...");
                while (retry) {
                    try {
                        threadInstance.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
                Log.w("AI", "AIThread stopped...");
            }
        });
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void run() {
        final String orgName = Thread.currentThread().getName();
        Thread.currentThread().setName(orgName + " - AI ");
        while (this.running) {
            //Wait for it
            do {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!this.running) {
                    return;
                }
            } while(!(TurnManager.getInstance().getCurrentPlayingPlayer() instanceof HTComputer));

            HTComputer player = (HTComputer) TurnManager.getInstance().getCurrentPlayingPlayer();
            Log.w("AI", "AIThread picked "+player.getPlayerColor().toString()+" player");

            //Get movements from strategy
            IMovement[] movements = player.getStrategy().compute(GameBoardFactory.getGameBoard());
            //Use movements (sould be safe cause we are awesome!)

            for (int i = 0; i < movements.length; i++) {
                MovementManager.getInstance().doMove(movements[i]);
            }

            if (TurnManager.getInstance().isNextTurnAvailable() == TurnManager.nextTurnRequire.bonusMarkers) {
                //Got bonus markers to replace
                //TODO replace bonus markers
            }
            if (TurnManager.getInstance().isNextTurnAvailable() == TurnManager.nextTurnRequire.none) {
                //Got no more actiones? ready to move your fucking ass out of the way
                //With a little delay to be more sexy of course
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                GameActivity.getInstance().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TurnManager.getInstance().nextPlayer(true);
                        Log.w("AI", "AIThread moved to player");
                    }
                });
            }
        }
    }
}
