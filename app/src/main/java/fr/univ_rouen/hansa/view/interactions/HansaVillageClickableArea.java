package fr.univ_rouen.hansa.view.interactions;

import android.os.Debug;
import android.util.Log;
import android.view.MotionEvent;

import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class HansaVillageClickableArea extends ClickableArea {

    private double selectionDistance = 0.1;
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

    public boolean onTouchEvent(MotionEvent event) {
        Log.w("Blah", "Blah");

        return true;
    }
}
