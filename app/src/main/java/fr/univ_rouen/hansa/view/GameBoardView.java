package fr.univ_rouen.hansa.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import fr.univ_rouen.hansa.R;

public class GameBoardView extends SurfaceView implements SurfaceHolder.Callback {

    private int destWidth;
    private int destHeigth;
    private float scale;

    private Bitmap background;

    public GameBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setWillNotDraw(false);
        getHolder().addCallback(this);
    }

    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(background, 0, 0, null);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(getScaledWidth(0), getScaledHeigth(0), getScaledWidth(100), getScaledHeigth(50), paint);
    }

    public void surfaceCreated(SurfaceHolder arg0) {
        Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.plateau23);
        destWidth = getWidth();
        destHeigth = getHeight();
        scale = (float) background.getHeight() / (float) getHeight();

        int newWidth = getScaledWidth(background.getWidth());
        int newHeight = getScaledHeigth(background.getHeight());
        this.background = Bitmap.createScaledBitmap(background, newWidth, newHeight, true);
    }

    public int getScaledWidth(int width) {
        return Math.round(width / scale);
    }

    public int getScaledHeigth(int heigth) {
        return Math.round(heigth / scale);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO Callback method contents
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Callback method contents
    }

}
