package fr.univ_rouen.hansa.view.display;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class HansaCityDrawer implements IDrawer  {

    public final float CITY_DRAW_SIZE = 0.005f;
    private final ICity city;

    public HansaCityDrawer(ICity city) {
        this.city = city;
    }

    @Override
    public void load(ResourceRepository resources) {

    }

    @Override
    public void draw(ResourceRepository resources, Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(
            resources.getPercentToScreenWidth(city.getPosition().getX() - CITY_DRAW_SIZE),
            resources.getPercentToScreenHeight(city.getPosition().getY() - CITY_DRAW_SIZE),
            resources.getPercentToScreenWidth(city.getPosition().getX() + CITY_DRAW_SIZE),
            resources.getPercentToScreenHeight(city.getPosition().getY() + CITY_DRAW_SIZE),
            paint
        );
    }
}
