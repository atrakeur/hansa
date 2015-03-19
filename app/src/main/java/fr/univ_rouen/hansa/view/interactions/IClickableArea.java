package fr.univ_rouen.hansa.view.interactions;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.io.Serializable;

import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public interface IClickableArea extends Serializable {

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

    public void onClick();

    public void onDragTo(IClickableArea to);

    public void onDragFrom(IClickableArea from);

    public void drawDebug(ResourceRepository resources, Canvas canvas);

    public Object getSubject();
    
}
