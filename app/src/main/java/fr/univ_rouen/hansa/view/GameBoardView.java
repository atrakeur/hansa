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
                resources.getPercentToScreenWidth(0),
                resources.getPercentToScreenHeight(0),
                resources.getPercentToScreenWidth(10),
                resources.getPercentToScreenHeight(10),
                paint);

        paint.setColor(Color.GREEN);
        canvas.drawRect(
                resources.getPercentToScreenWidth(49f),
                resources.getPercentToScreenHeight(49f),
                resources.getPercentToScreenWidth(51f),
                resources.getPercentToScreenHeight(51f),
                paint);
    }

}
