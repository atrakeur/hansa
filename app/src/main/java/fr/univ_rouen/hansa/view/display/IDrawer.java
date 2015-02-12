package fr.univ_rouen.hansa.view.display;

import android.graphics.Canvas;

import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public interface IDrawer {

    /**
     * Tell the drawer to load his resources
     * @param resources
     */
    public void load(ResourceRepository resources);

    /**
     * Draw the current object on the current canvas
     * @param resources
     * @param canvas
     */
    public void draw(ResourceRepository resources, Canvas canvas);

}
