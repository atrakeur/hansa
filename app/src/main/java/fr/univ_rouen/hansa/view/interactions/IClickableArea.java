package fr.univ_rouen.hansa.view.interactions;

import android.graphics.Canvas;

import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public interface IClickableArea {

    enum Type {
        village,
        city,
        power,
        stock,
        supply,
        victoryCoellen,
        bonus
    }

    Type getType();

    boolean isClicked(float x, float y);

    void onClick();

    void onDragTo(IClickableArea to);

    void onDragFrom(IClickableArea from);

    void drawDebug(ResourceRepository resources, Canvas canvas);

    Object getSubject();

}
