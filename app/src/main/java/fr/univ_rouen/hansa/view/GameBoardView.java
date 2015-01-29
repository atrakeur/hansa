package fr.univ_rouen.hansa.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.view.utils.BitmapResourceRepository;

public class GameBoardView extends SurfaceView {

    BitmapResourceRepository resources;

    public GameBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        resources = new BitmapResourceRepository(this);
        resources.addResource("background", getResources(), R.drawable.plateau23);

        setWillNotDraw(false);
    }

    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(resources.getScaledResource("background"), 0, 0, null);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(
                resources.getScaledWidth(0),
                resources.getScaledHeight(0),
                resources.getScaledWidth(100),
                resources.getScaledHeight(50),
                paint);
    }

}
