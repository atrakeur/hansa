package fr.univ_rouen.hansa.view.display;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.util.PowerPositions;
import fr.univ_rouen.hansa.view.IPosition;
import fr.univ_rouen.hansa.view.Position;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class HansaCityDrawer implements IDrawer {

    private boolean debug = true;

    public final float KONTOR_MERCHANT_SIZE_X = 0.021f;
    public final float KONTOR_MERCHANT_SIZE_Y = 0.0275f;
    public final float KONTOR_TRADER_SIZE_X = 0.014f;
    public final float KONTOR_TRADER_SIZE_Y = 0.018f;
    public final float KONTOR_SPACING_X = 0.00925f;

    public final float POWER_SIZE_X = 0.055f;
    public final float POWER_SIZE_Y = 0.055f;

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
        List<IPosition> lp = Lists.newArrayList();
        lp.add(PowerPositions.ACTIONES);
        lp.add(PowerPositions.BURSA);
        lp.add(PowerPositions.CLAVISURBIS);
        lp.add(PowerPositions.LIBERSOPHIAE);
        lp.add(PowerPositions.CLAVISURBIS);
        lp.add(PowerPositions.PRIVILEGIUM);
        if (debug == true) {
            drawPosX = 0;
                if (city.getPower() != Power.Null) {
                    Position pos = city.getPower().getPosition();
                    drawPosX += pos.getX();
                    //canvas.drawOval(new RectF(left, top, right, bottom));
                    paint.setColor(Color.BLUE);
                    canvas.drawOval(
                            new RectF(resources.getPercentToScreenWidth(drawPosX),
                                    resources.getPercentToScreenHeight(pos.getY() - POWER_SIZE_Y / 2),
                                    resources.getPercentToScreenWidth(drawPosX + POWER_SIZE_X),
                                    resources.getPercentToScreenHeight(pos.getY() + POWER_SIZE_Y / 2)),
                            paint
                    );
            }
        }
    }

}
