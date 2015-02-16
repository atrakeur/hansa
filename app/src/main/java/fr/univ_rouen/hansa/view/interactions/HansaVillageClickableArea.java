package fr.univ_rouen.hansa.view.interactions;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Debug;
import android.util.Log;
import android.view.MotionEvent;

import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.actions.movement.MovePawnRtoGB;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class HansaVillageClickableArea extends ClickableArea {

    private float selectionDistance = 0.1f;
    private IVillage village;

    public HansaVillageClickableArea(IVillage village) {
        super(Type.village);

        this.village = village;
    }

    public boolean isClicked(float x, float y) {
        double distance = Math.sqrt(
                Math.pow(x - village.getPosition().getX(), 2)
                +
                Math.pow(y - village.getPosition().getY(), 2)
        );

        return distance < selectionDistance;
    }

    @Override
    public void onClick() {
        Log.w("Village", "onClick");
        MovementFactory.getInstance().makeMovement(this, null);
    }

    @Override
    public void onDragTo(IClickableArea to) {
        Log.w("Village", "onDragTo " + to);
    }

    @Override
    public void onDragFrom(IClickableArea from) {
        Log.w("Village", "onDragFrom" + from);
        MovementFactory.getInstance().makeMovement(from, this);
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
        canvas.drawCircle(village.getPosition().getX(), village.getPosition().getY(), radius, p);
    }

    @Override
    public Object getSubject() {
        return this.village;
    }

}
