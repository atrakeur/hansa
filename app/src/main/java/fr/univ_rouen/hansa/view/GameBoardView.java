package fr.univ_rouen.hansa.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.widget.Toast;

import java.util.Collections;

import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.view.display.HansaGameBoardDrawer;
import fr.univ_rouen.hansa.view.interactions.HansaGameBoardEventManager;
import fr.univ_rouen.hansa.view.utils.DrawingThread;
import fr.univ_rouen.hansa.view.interactions.IClickable;
import fr.univ_rouen.hansa.view.interactions.IClickableArea;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class GameBoardView extends SurfaceView {

    private DrawingThread thread;

    private GameBoard board;
    private ResourceRepository resources;

    private HansaGameBoardEventManager eventManager;

    public GameBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        resources = new ResourceRepository(this, getResources());

        setFocusable(true);
        setWillNotDraw(false);

        //TODO change that using a cute menu to select map
        setBoard(GameBoardFactory.getInstance().createGameBoard(1));

        eventManager = new HansaGameBoardEventManager(this, board, resources);

        thread = new DrawingThread(this, getHolder());
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
        return this.eventManager.onTouchEvent(event);
    }

}
