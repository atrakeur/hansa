package fr.univ_rouen.hansa.view.display;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class HansaCityDrawer implements IDrawer {

    private boolean debug = false;

    public final float KONTOR_MERCHANT_SIZE_X = 0.021f;
    public final float KONTOR_MERCHANT_SIZE_Y = 0.0275f;
    public final float KONTOR_TRADER_SIZE_X = 0.014f;
    public final float KONTOR_TRADER_SIZE_Y = 0.018f;
    public final float KONTOR_SPACING_X = 0.00925f;

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

        //Calculate full city size
        float citySizeX = 0.0f;
        for (IKontor kontor : kontors) {
            if (kontor.getPawnClass() == Trader.class) {
                citySizeX += KONTOR_TRADER_SIZE_X;
            } else if (kontor.getPawnClass() == Merchant.class) {
                citySizeX += KONTOR_MERCHANT_SIZE_X;
            }
        }
        citySizeX += KONTOR_SPACING_X * (kontors.size() - 1);

        //Draw kontors from left to right
        Paint paint = new Paint();
        float drawPosX = city.getPosition().getX() - citySizeX / 2;

        for (IKontor kontor : kontors) {
            //Draw a trader type kontor
            if (kontor.getPawnClass() == Trader.class && (debug || !kontor.isEmpty())) {
                if (debug) {
                    int color = kontor.getPrivillegium().getColor();
                    paint.setColor(color);
                } else {
                    int color = kontor.getOwner().getPlayerColor().getColor();
                    paint.setColor(color);
                }

                canvas.drawRect(
                        resources.getPercentToScreenWidth(drawPosX),
                        resources.getPercentToScreenHeight(city.getPosition().getY() - KONTOR_TRADER_SIZE_Y / 2),
                        resources.getPercentToScreenWidth(drawPosX + KONTOR_TRADER_SIZE_X),
                        resources.getPercentToScreenHeight(city.getPosition().getY() + KONTOR_TRADER_SIZE_Y / 2),
                        paint
                );

                drawPosX += KONTOR_TRADER_SIZE_X + KONTOR_SPACING_X;
            }

            //Draw a Merchant type kontor
            if (kontor.getPawnClass() == Merchant.class && (debug || !kontor.isEmpty())) {
                if (debug) {
                    int color = kontor.getPrivillegium().getColor();
                    paint.setColor(color);
                } else {
                    int color = kontor.getOwner().getPlayerColor().getColor();
                    paint.setColor(color);
                }

                canvas.drawOval(
                        new RectF(resources.getPercentToScreenWidth(drawPosX),
                                resources.getPercentToScreenHeight(city.getPosition().getY() - KONTOR_MERCHANT_SIZE_Y / 2),
                                resources.getPercentToScreenWidth(drawPosX + KONTOR_MERCHANT_SIZE_X),
                                resources.getPercentToScreenHeight(city.getPosition().getY() + KONTOR_MERCHANT_SIZE_Y / 2)),
                        paint
                );

                drawPosX += KONTOR_MERCHANT_SIZE_X + KONTOR_SPACING_X;
            }
        }
    }
}
