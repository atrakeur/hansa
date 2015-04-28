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

public class HansaVillageClickableArea extends ClickableArea {

    private float selectionDistance = 0.025f;
    private IVillage village;
    private IClickableArea tavernArea;

    public HansaVillageClickableArea(IVillage village) {
        super(Type.village);

        this.village = village;
        if (village.getRoute().getBonusMarker() != null) {
            tavernArea = new HansaTavernClickableArea(village);
        }
    }

    public boolean isClicked(float x, float y) {
        if (village.getRoute().getBonusMarker() != null) {
            return tavernArea.isClicked(x,y);
        } else {
            double distance = Math.sqrt(
                    Math.pow(x - village.getPosition().getX(), 2)
                            +
                            Math.pow(y - village.getPosition().getY(), 2)
            );

            return distance < selectionDistance;
        }
    }

    @Override
    public void onClick() {
        if (village.getRoute().getBonusMarker() != null) {
            tavernArea.onClick();
        } else {
            Log.w("Village", "onClick");

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
        if (village.getRoute().getBonusMarker() != null) {
            tavernArea.onDragTo(to);
        } else {
            Log.w("Village", "onDragTo " + to);
        }
    }

    @Override
    public void onDragFrom(IClickableArea from) {
        if (village.getRoute().getBonusMarker() != null) {
            tavernArea.onDragFrom(from);
        } else {
            Log.w("Village", "onDragFrom" + from);

            try {
                IMovement m = MovementFactory.getInstance().makeMovement(from, this);

                Log.w("Important!", m.toString());
                Log.w("Important!", "" + village.isEmpty());
                Log.w("Important!", "" + village.getPawnType());

                MovementManager.getInstance().doMove(m);

                Log.w("Important!", "" + village.isEmpty());
                Log.w("Important!", "" + village.getPawnType());
            } catch (GameException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void drawDebug(ResourceRepository resources, Canvas canvas) {
        if (village.getRoute().getBonusMarker() != null) {
            tavernArea.drawDebug(resources, canvas);
        } else {
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
    }

    @Override
    public Object getSubject() {
        if (village.getRoute().getBonusMarker() != null) {
            return tavernArea.getSubject();
        } else {
            return this.village;
        }
    }

}
