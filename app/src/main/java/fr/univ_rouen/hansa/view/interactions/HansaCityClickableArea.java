package fr.univ_rouen.hansa.view.interactions;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class HansaCityClickableArea extends ClickableArea {

    private float selectionDistance = 0.2f;
    private ICity city;

    public HansaCityClickableArea(ICity city) {
        super(Type.city);

        this.city = city;
    }

    public boolean isClicked(float x, float y) {
        double distance = Math.sqrt(
                Math.pow(x - city.getPosition().getX(), 2)
                +
                Math.pow(y - city.getPosition().getY(), 2)
        );

        return distance < selectionDistance;
    }

    @Override
    public void onClick() {
        Log.w("Ville", "onClick");
    }

    @Override
    public void onDragTo(IClickableArea to) {
        Log.w("Ville", "onDragTo " + to);
    }

    @Override
    public void onDragFrom(IClickableArea from) {
        Log.w("Ville", "onDragFrom" + from);
    }

    @Override
    public void drawDebug(ResourceRepository resources, Canvas canvas)
    {
        float radius = Math.max(
                resources.getScreenWidthToPercent(this.selectionDistance),
                resources.getScreenHeigthToPercent(this.selectionDistance)
        );
        Paint p = new Paint();
        p.setColor(Color.GREEN);
        canvas.drawCircle(city.getPosition().getX(), city.getPosition().getY(), radius, p);
    }

    @Override
    public Object getSubject() {
        return this.city;
    }

}
