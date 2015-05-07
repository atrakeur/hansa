package fr.univ_rouen.hansa.view.interactions;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class HansaCityClickableArea extends ClickableArea {

    private float selectionDistance = 0.04f;
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

        try {
            IMovement m = MovementFactory.getInstance().makeMovement(this, null);
            doMove(m);
        } catch(GameException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDragTo(IClickableArea to) {
        Log.w("Ville", "onDragTo " + to);
    }

    @Override
    public void onDragFrom(IClickableArea from) {
        Log.w("Ville", "onDragFrom" + from);

        try {
            IMovement m = MovementFactory.getInstance().makeMovement(from, this);
            doMove(m);
        } catch(GameException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void drawDebug(ResourceRepository resources, Canvas canvas)
    {
        float radius = Math.max(
                resources.getPercentToScreenWidth(this.selectionDistance),
                resources.getPercentToScreenHeight(this.selectionDistance)
        );
        Paint p = new Paint();
        p.setColor(Color.GREEN);
        p.setStyle(Paint.Style.FILL);
        canvas.drawCircle(
                resources.getPercentToScreenWidth(city.getPosition().getX()),
                resources.getPercentToScreenHeight(city.getPosition().getY()),
                radius,
                p
        );
    }

    @Override
    public Object getSubject() {
        return this.city;
    }

}
