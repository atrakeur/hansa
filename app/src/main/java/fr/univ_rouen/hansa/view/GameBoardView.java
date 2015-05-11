package fr.univ_rouen.hansa.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.io.IOException;

import fr.univ_rouen.hansa.activity.GameActivity;
import fr.univ_rouen.hansa.ai.AIThread;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.save.SaveManager;
import fr.univ_rouen.hansa.view.interactions.HansaGameBoardEventManager;
import fr.univ_rouen.hansa.view.utils.DrawingThread;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class GameBoardView extends SurfaceView {

    private static GameBoardView instance;

    private DrawingThread thread;
    //TODO don't belong here, but it's more fancy if AI play only when we can see it playing!
    private AIThread aiThread;

    private GameBoard board;
    private ResourceRepository resources;

    private HansaGameBoardEventManager eventManager;

    public GameBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        resources = new ResourceRepository(this, getResources());

        setFocusable(true);
        setWillNotDraw(false);

        if (GameBoard.LOAD_FROM_SAVE) {
            try {
                GameBoard.LOAD_FROM_SAVE = false;

                GameBoard gameBoard = new SaveManager().load();
                setBoard(gameBoard);
            } catch (IOException e) {
                setBoard(GameBoardFactory.getInstance().createGameBoard(1));

                e.printStackTrace();
            }
        } else {
            setBoard(GameBoardFactory.getInstance().createGameBoard(1));
        }

        eventManager = new HansaGameBoardEventManager(this, board, resources);

        thread = new DrawingThread(this, getHolder());
        aiThread = new AIThread(this, getHolder());

        instance = this;
    }

    public static GameBoardView getInstance() {
        //TODO remove that
        return instance;
    }

    public void setBoard(GameBoard board) {
        this.resources.clear();

        this.board = board;

        board.getDrawer().load(resources);
    }

    public void redraw(Canvas canvas) {
        if (board == null) {
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            canvas.drawRect(0, 0, 100, 100, paint);
        } else {
            board.getDrawer().draw(resources, canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = this.eventManager.onTouchEvent(event);
        GameActivity.getInstance().onResume();
        return result;
    }

}
