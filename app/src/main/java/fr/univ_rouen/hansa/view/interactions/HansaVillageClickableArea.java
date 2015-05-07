package fr.univ_rouen.hansa.view.interactions;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class HansaVillageClickableArea extends ClickableArea {

    private float selectionDistance = 0.025f;
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
        Log.w("Village", "onDragTo " + to);
    }

    @Override
    public void onDragFrom(IClickableArea from) {
        Log.w("Village", "onDragFrom" + from);

        try {
            IMovement m = MovementFactory.getInstance().makeMovement(from, this);

            Log.w("Important!", m.toString());
            Log.w("Important!", ""+ village.isEmpty());
            Log.w("Important!", ""+ village.getPawnType());

            doMove(m);

            Log.w("Important!", ""+ village.isEmpty());
            Log.w("Important!", ""+ village.getPawnType());
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
        p.setColor(Color.CYAN);
        p.setStyle(Paint.Style.FILL);
        canvas.drawCircle(
                resources.getPercentToScreenWidth(village.getPosition().getX()),
                resources.getPercentToScreenHeight(village.getPosition().getY()),
                radius,
                p
        );
    }

    @Override
    public Object getSubject() {
        return this.village;
    }

}
