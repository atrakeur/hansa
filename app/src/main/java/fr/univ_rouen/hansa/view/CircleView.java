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

    public int getCircleRadius() {
        return circleRadius;
    }

    public void setCircleRadius(int circleRadius) {
        this.circleRadius = circleRadius;
    }

    public int getCircleFillColor() {
        return circleFillColor;
    }

    public void setCircleFillColor(int circleFillColor) {
        this.circleFillColor = circleFillColor;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int measuredWidth = measureWidth(widthMeasureSpec);
        if (circleRadius == 0) // No radius specified.
        {                     // Lets see what we can make.
            // Check width size. Make radius half of available.
            circleRadius = measuredWidth / 2;
            int tempRadiusHeight = measureHeight(heightMeasureSpec) / 2;
            if (tempRadiusHeight < circleRadius)
                // Check height, if height is smaller than
                // width, then go half height as radius.
                circleRadius = tempRadiusHeight;
        }

        int measuredHeight = measureHeight(heightMeasureSpec);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 0;
        if (specMode == MeasureSpec.AT_MOST) {
            result = circleRadius * 2;
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        return result;
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 0;
        if (specMode == MeasureSpec.AT_MOST) {
            result = circleRadius * 2;
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        return result;
    }
}
