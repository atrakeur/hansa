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

public class HansaCityClickableArea extends ClickableArea {

    private float selectionDistance = 0.04f;
    private ICity city;
    private HansaPowerClickableArea hansapower;

    public HansaCityClickableArea(ICity city) {
        super(Type.city);
        this.city = city;
        if (city.getPower() != Power.Null) {
            hansapower = new HansaPowerClickableArea(city);
        }
    }

    public boolean isClicked(float x, float y) {
        if (city.getPower() != Power.Null) {
            hansapower.isClicked(x,y);
        }
            double distance = Math.sqrt(
                    Math.pow(x - city.getPosition().getX(), 2)
                            +
                            Math.pow(y - city.getPosition().getY(), 2)
            );
            //Log.w("Value city pos : " + city.getPosition().getX() + " ", val + "");
            return distance < selectionDistance;
    }

    @Override
    public void onClick() {
        if (city.getPower() != Power.Null) {
            hansapower.onClick();
        } else {
            Log.w("Ville", "onClick");

            try {
                IMovement m = MovementFactory.getInstance().makeMovement(this, null);
                MovementManager.getInstance().doMove(m);
            } catch (GameException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDragTo(IClickableArea to) {
        if (city.getPower() != Power.Null) {
            hansapower.onDragTo(to);
        } else {
            Log.w("Ville", "onDragTo " + to);
        }
    }

    @Override
    public void onDragFrom(IClickableArea from) {
        if (city.getPower() != Power.Null) {
            hansapower.onDragFrom(from);
        } else {
            Log.w("Ville", "onDragFrom" + from);

            try {
                IMovement m = MovementFactory.getInstance().makeMovement(from, this);
                MovementManager.getInstance().doMove(m);
            } catch (GameException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void drawDebug(ResourceRepository resources, Canvas canvas) {
        if (city.getPower() != Power.Null) {
            hansapower.drawDebug(resources, canvas);
        } else {
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
    }

    @Override
    public Object getSubject() {
        if (city.getPower() != Power.Null) {
            hansapower.getSubject();
        }
        return this.city;
    }

}
