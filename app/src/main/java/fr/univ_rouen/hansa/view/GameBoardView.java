package fr.univ_rouen.hansa.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.view.utils.DrawingThread;
import fr.univ_rouen.hansa.view.interactions.IClickable;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class GameBoardView extends SurfaceView {

    private DrawingThread thread;

    private GameBoard board;
    private ResourceRepository resources;

    public GameBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        resources = new ResourceRepository(this, getResources());

        setFocusable(true);
        setWillNotDraw(false);

        //TODO change that using a cute menu to select map
        setBoard(GameBoardFactory.getInstance().createGameBoard(1));

        thread = new DrawingThread(this, getHolder());
    }

    public void setBoard(GameBoard board) {
        this.resources.clear();

        this.board = board;

        board.getDrawer().load(resources);
    }

    public void onDraw(Canvas canvas) {
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
        IClickable[] clickables = board.getClickables();

        float percentX = resources.getScreenWidthToPercent(event.getX());
        float percentY = resources.getScreenWidthToPercent(event.getX());

        for (IClickable clickable: clickables) {
            if (clickable.getClickableArea().isClicked(percentX, percentY)) {
                return clickable.getClickableArea().onTouchEvent(event);
            }
        }

        return true;
    }

}
