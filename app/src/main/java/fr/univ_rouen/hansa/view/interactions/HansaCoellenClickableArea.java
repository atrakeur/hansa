package fr.univ_rouen.hansa.view.interactions;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.gameboard.cities.VictoryCoellen;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class HansaCoellenClickableArea extends ClickableArea {
    private float selectionDistance = 0.04f;
    private VictoryCoellen victoryCoellen;

    public HansaCoellenClickableArea(VictoryCoellen vc) {
        super(Type.victoryCoellen);
        this.victoryCoellen = vc;
    }

    public boolean isClicked(float x, float y) {
        double distance = Math.sqrt(
                Math.pow(x - victoryCoellen.getPosition().getX(), 2)
                        +
                        Math.pow(y - victoryCoellen.getPosition().getY(), 2)
        );

        return distance < selectionDistance;
    }

    @Override
    public void onClick() {
        Log.w("Coellen", "onClick");

        try {
            IMovement m = MovementFactory.getInstance().makeMovement(this, null);
            MovementManager.getInstance().doMove(m);
        } catch(GameException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDragTo(IClickableArea to) {
        Log.w("Collen", "onDragTo " + to);
    }

    @Override
    public void onDragFrom(IClickableArea from) {
        Log.w("coellen", "onDragFrom" + from);

        try {
            IMovement m = MovementFactory.getInstance().makeMovement(from, this);
            MovementManager.getInstance().doMove(m);
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
                resources.getPercentToScreenWidth(victoryCoellen.getPosition().getX()),
                resources.getPercentToScreenHeight(victoryCoellen.getPosition().getY()),
                radius,
                p
        );
    }

    @Override
    public Object getSubject() {
        return this.victoryCoellen;
    }

}
