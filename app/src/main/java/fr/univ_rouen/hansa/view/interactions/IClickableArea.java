package fr.univ_rouen.hansa.view.interactions;

import android.view.MotionEvent;

public interface IClickableArea {

    public enum Type {
        village,
        city,
        power,
        stock,
        supply,
        bonus
    }

    public Type getType();

    public boolean isClicked(float x, float y);

    public boolean onTouchEvent(MotionEvent event);
    
}
