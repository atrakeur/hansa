package fr.univ_rouen.hansa.view.interactions;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class HansaSupplyClickableArea extends ClickableArea {

    private float posX = 0.9f;
    private float posY = 0.9f;
    private float sizeX = 0.1f;
    private float sizeY = 0.1f;

    public HansaSupplyClickableArea() {
        super(Type.supply);
    }

    public boolean isClicked(float x, float y) {
        return x > posX && x < posX + sizeX && y > posY && y < posY + sizeY;
    }

    @Override
    public void onClick() {
        Log.w("Supply", "onClick");

        try {
            IMovement m = MovementFactory.getInstance().makeMovement(this, null);
            MovementManager.getInstance().doMove(m);
        } catch(GameException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDragTo(IClickableArea to) {
        Log.w("Supply", "onDragTo " + to);
    }

    @Override
    public void onDragFrom(IClickableArea from) {
        Log.w("Supply", "onDragFrom" + from);

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
        Paint p = new Paint();
        p.setColor(Color.MAGENTA);
        canvas.drawRect(posX, posY, posX + sizeX, posY + sizeY, p);
    }

    @Override
    public Object getSubject() {
        throw new IllegalStateException("No subject on Supply clickable area");
    }

}

