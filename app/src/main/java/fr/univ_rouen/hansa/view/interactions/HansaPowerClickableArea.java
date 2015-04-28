package fr.univ_rouen.hansa.view.interactions;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class HansaPowerClickableArea extends ClickableArea {

    private float selectionDistance = 0.055f;
    private ICity city;
    public HansaPowerClickableArea(ICity c) {
        super(Type.city);
        this.city = c;
    }

    @Override
    public boolean isClicked(float x, float y) {
        double distance = Math.sqrt(
                Math.pow(x - city.getPower().getPosition().getX(), 2)
                        +
                        Math.pow(y - city.getPower().getPosition().getY(), 2)
        );

        return distance < selectionDistance;
    }

    @Override
    public void onClick() {
        Log.w("Power", "onClick");

        try {
            IMovement m = MovementFactory.getInstance().makeMovement(this, null);
            MovementManager.getInstance().doMove(m);
        } catch(GameException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDragTo(IClickableArea to) {
        Log.w("Power", "onDragTo " + to);
    }

    @Override
    public void onDragFrom(IClickableArea from) {
        Log.w("Power", "onDragFrom" + from);

        try {
            IMovement m = MovementFactory.getInstance().makeMovement(from, this);
            MovementManager.getInstance().doMove(m);
        } catch(GameException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void drawDebug(ResourceRepository resources, Canvas canvas) {
        float radius = Math.max(
                resources.getPercentToScreenWidth(this.selectionDistance),
                resources.getPercentToScreenHeight(this.selectionDistance)
        );
        Paint p = new Paint();
        p.setColor(Color.GREEN);
        p.setStyle(Paint.Style.FILL);
        canvas.drawCircle(
                resources.getPercentToScreenWidth(city.getPower().getPosition().getX()),
                resources.getPercentToScreenHeight(city.getPower().getPosition().getY()),
                radius,
                p
        );
    }

    @Override
    public Object getSubject() {
        return this.city.getPower();
    }
}
