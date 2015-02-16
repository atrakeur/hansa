package fr.univ_rouen.hansa.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.widget.Toast;

import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.view.utils.DrawingThread;
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

    /**Display the error message as a toast
     * @param msg message to be displayed*/
    public void showErrorMsg(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

}
