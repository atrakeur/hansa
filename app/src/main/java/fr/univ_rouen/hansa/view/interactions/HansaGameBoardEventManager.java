package fr.univ_rouen.hansa.view.interactions;

import android.view.MotionEvent;
import android.widget.Toast;

import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.view.GameBoardView;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class HansaGameBoardEventManager {

    private final GameBoardView view;
    private final GameBoard board;
    private final ResourceRepository resources;

    private IClickableArea touchStart;
    private IClickableArea touchEnd;

    public HansaGameBoardEventManager(GameBoardView view, GameBoard board, ResourceRepository resources) {
        this.view = view;
        this.board = board;
        this.resources = resources;
    }

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
                this.triggerAction(this.touchStart, this.touchEnd);
            }
        } catch (GameException e) {
            this.showErrorMsg(e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedOperationException e) {
            this.showErrorMsg("Action invalid");
            e.printStackTrace();
        } catch (Exception e) {
            this.showErrorMsg("Error during execution of the action");
            e.printStackTrace();
        }

        return true;
    }

    private void triggerAction(IClickableArea touchStart, IClickableArea touchEnd) {
        if (touchStart == touchEnd) {
            this.triggerClick(touchStart, touchEnd);
        } else {
            if (touchStart != null) {
                this.triggerDragTo(touchStart, touchEnd);
            }
            if (touchEnd != null) {
                this.triggerDragFrom(touchStart, touchEnd);
            }
        }
    }

    private void triggerClick(IClickableArea touchStart, IClickableArea touchEnd) {
        touchEnd.onClick();
    }

    private void triggerDragTo(IClickableArea touchStart, IClickableArea touchEnd) {
        touchStart.onDragTo(touchEnd);
    }

    private void triggerDragFrom(IClickableArea touchStart, IClickableArea touchEnd) {
        touchEnd.onDragFrom(touchStart);
    }

    /**Display the error message as a toast
     * @param msg message to be displayed*/
    public void showErrorMsg(String msg){
        Toast.makeText(view.getContext(), msg, Toast.LENGTH_LONG).show();
    }

}
