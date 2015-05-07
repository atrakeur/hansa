package fr.univ_rouen.hansa.view.interactions;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.util.PowerPositions;
import fr.univ_rouen.hansa.view.IPosition;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class HansaPowerClickableArea extends ClickableArea implements IClickable {

    private Power power;
    private float selectionDistance = 0.025f;

    private IPosition position;
    private ICity city;

    public HansaPowerClickableArea(Power power) {
        super(Type.power);

        this.power = power;
        this.position = PowerPositions.powerToPosition.get(power);
    }

    public boolean isClicked(float x, float y) {
        double distance = Math.sqrt(
                Math.pow(x - position.getX(), 2)
                        +
                Math.pow(y - position.getY(), 2)
        );

        return distance < selectionDistance;
    }

    @Override
    public void onClick() {
        Log.w("Power", "onClick");

        if(MovementFactory.getInstance().getBonusMarker() == null){
            try {
                IMovement m = MovementFactory.getInstance().makeMovement(this, null);
                doMove(m);
            } catch(GameException e) {
                e.printStackTrace();
            }
        } else {
            try {
                IMovement m = MovementFactory.getInstance().makeMovement(this, null);
                if(m != null){
                    doMove(m);
                    MovementFactory.getInstance().clearBonusMove();

                }
            } catch(GameException e) {
                e.printStackTrace();
            }
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
            doMove(m);
        } catch(GameException e) {
            e.printStackTrace();
            throw e;
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
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.FILL);
        canvas.drawCircle(
                resources.getPercentToScreenWidth(position.getX()),
                resources.getPercentToScreenHeight(position.getY()),
                radius,
                p
        );
    }

    @Override
    public Object getSubject() {
        return this.power;
    }

    @Override
    public IClickableArea getClickableArea() {
        return this;
    }
}
