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
import fr.univ_rouen.hansa.view.utils.DrawingThread;
import fr.univ_rouen.hansa.view.interactions.IClickable;
import fr.univ_rouen.hansa.view.interactions.IClickableArea;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class GameBoardView extends SurfaceView {

    private DrawingThread thread;

    private GameBoard board;
    private ResourceRepository resources;

    private IClickableArea touchStart;
    private IClickableArea touchEnd;

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
        //On touchdown, reset
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            this.touchStart = null;
            this.touchEnd = null;
        }

        //Get the area below the touch area
        IClickableArea touchedArea = null;
        IClickable[] clickables = board.getClickables();
        float percentX = resources.getScreenWidthToPercent(event.getX());
        float percentY = resources.getScreenHeigthToPercent(event.getY());
        for (IClickable clickable: clickables) {
            if (clickable.getClickableArea().isClicked(percentX, percentY)) {
                touchedArea = clickable.getClickableArea();
            }
        }

        //Early out if nothing touched
        if (touchedArea == null) {
            return false;
        }

        //Touchdown, start drag & drop? or just click?
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            this.touchStart = touchedArea;
        }
        //Touch ended
        else if (event.getAction() == MotionEvent.ACTION_UP) {
            this.touchEnd = touchedArea;
        }
        //Event bidon qu'on gére pas!
        else {
            return false;
        }

        //Dispatch events correctly (Simple click, drag from and drag to)
        try {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (this.touchStart == this.touchEnd) {
                    this.touchEnd.onClick();
                } else {
                    if (touchStart != null) {
                        this.touchStart.onDragTo(this.touchEnd);
                    }
                    if (touchEnd != null) {
                        this.touchEnd.onDragFrom(this.touchStart);
                    }
                }
            }
        } catch (GameException e) {
            this.showErrorMsg(e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedOperationException e) {
            this.showErrorMsg("Action not implemented (yet?)");
            e.printStackTrace();
        } catch (Exception e) {
            this.showErrorMsg("General exception (should never happen, time to debug)");
            e.printStackTrace();
        }

        return true;
    }

    /**Display the error message as a toast
     * @param msg message to be displayed*/
    public void showErrorMsg(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

}
