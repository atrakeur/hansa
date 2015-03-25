package fr.univ_rouen.hansa.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import fr.univ_rouen.hansa.R;

public class CircleView extends View {

    private Paint circlePaint;

    // Attrs
    private int circleRadius;
    private int circleFillColor;


    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStyle(Paint.Style.FILL);
    }

    private void init(AttributeSet attrs) {
        TypedArray attrsArray = getContext().obtainStyledAttributes(attrs, R.styleable.CircleView);
        circleRadius = attrsArray.getInteger(R.styleable.CircleView_cRadius, 0);
        circleFillColor = attrsArray.getInteger(R.styleable.CircleView_cFillColor, 16777215);
        attrsArray.recycle();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        circlePaint.setColor(circleFillColor);
        canvas.drawCircle(circleRadius, circleRadius, circleRadius, circlePaint);
    }

}
