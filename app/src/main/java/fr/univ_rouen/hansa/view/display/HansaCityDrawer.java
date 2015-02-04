package fr.univ_rouen.hansa.view.display;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.Kontor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class HansaCityDrawer implements IDrawer  {

    public final float KONTOR_SIZE = 0.015f;
    public final float KONTOR_SPACING = 0.015f;

    private final ICity city;

    public HansaCityDrawer(ICity city) {
        this.city = city;
    }

    @Override
    public void load(ResourceRepository resources) {

    }

    @Override
    public void draw(ResourceRepository resources, Canvas canvas) {
        List<IKontor<? extends Pawn>> kontors = city.getKontors();

        float citySizeX = KONTOR_SIZE * kontors.size() + KONTOR_SPACING * (kontors.size() - 1);

        Paint paint = new Paint();

        for(int i = 0; i < kontors.size(); i++)
        {
            float kontorPosX = city.getPosition().getX() - citySizeX / 2;
            kontorPosX = kontorPosX + KONTOR_SIZE * i;
            kontorPosX = kontorPosX + KONTOR_SPACING * i;

            paint.setColor(kontors.get(i).getPrivillegium().getColor());

            canvas.drawRect(
                    resources.getPercentToScreenWidth(kontorPosX),
                    resources.getPercentToScreenHeight(city.getPosition().getY() - KONTOR_SIZE),
                    resources.getPercentToScreenWidth(kontorPosX + KONTOR_SIZE),
                    resources.getPercentToScreenHeight(city.getPosition().getY() + KONTOR_SIZE),
                    paint
            );
        }
    }
}
