package fr.univ_rouen.hansa.view.interactions;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class HansaTavernClickableArea extends ClickableArea {
    private float selectionDistance = 0.025f;
    private IVillage village;

    public HansaTavernClickableArea(IVillage v) {
        super(Type.village);
        this.village = v;
    }

    @Override
    public boolean isClicked(float x, float y) {
        double distance = Math.sqrt(
                Math.pow(x - village.getRoute().getTavernPosition().getX(), 2)
                        +
                        Math.pow(y - village.getRoute().getTavernPosition().getY(), 2)
        );

        return distance < selectionDistance;

    }

    @Override
    public void onClick() {
        Log.w("Power", "onClick");

        try {
            IMovement m = MovementFactory.getInstance().makeMovement(this, null);
            MovementManager.getInstance().doMove(m);
        } catch (GameException e) {
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
        } catch (GameException e) {
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
                resources.getPercentToScreenWidth(village.getRoute().getTavernPosition().getX()),
                resources.getPercentToScreenHeight(village.getRoute().getTavernPosition().getY()),
                radius,
                p
        );
    }

    @Override
    public Object getSubject() {
        return this.village.getRoute().getBonusMarker();
    }
}