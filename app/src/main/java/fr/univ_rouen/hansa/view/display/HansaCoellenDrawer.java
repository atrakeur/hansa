package fr.univ_rouen.hansa.view.display;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.VictoryCoellen;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.util.CityPositions;
import fr.univ_rouen.hansa.util.CoellenPositions;
import fr.univ_rouen.hansa.view.IPosition;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;


public class HansaCoellenDrawer implements IDrawer {
    private boolean debug = true;

    public final float KONTOR_MERCHANT_SIZE_X = 0.021f;
    public final float KONTOR_MERCHANT_SIZE_Y = 0.0275f;
    public final float KONTOR_TRADER_SIZE_X = 0.014f;
    public final float KONTOR_TRADER_SIZE_Y = 0.018f;
    public final float KONTOR_SPACING_X = 0.00925f;

    private final VictoryCoellen victoryCoellen;

    public HansaCoellenDrawer(VictoryCoellen vc) {
        this.victoryCoellen = vc;
    }

    @Override
    public void load(ResourceRepository resources) {

    }

    @Override
    public void draw(ResourceRepository resources, Canvas canvas) {
        List<IPosition> list = Lists.newArrayList();
        list.add(CoellenPositions.SEVENPOINTS);
        list.add(CoellenPositions.EIGHTPOINTS);
        list.add(CoellenPositions.NINEPOINTS);
        list.add(CoellenPositions.ELEVENPOINTS);


        Paint paint = new Paint();
            if (debug) {
                paint.setColor(Color.BLUE);
                for (IPosition pos : list) {
                    canvas.drawOval(
                            new RectF(resources.getPercentToScreenWidth(pos.getX()),
                                    resources.getPercentToScreenHeight(pos.getY() - KONTOR_MERCHANT_SIZE_Y / 2),
                                    resources.getPercentToScreenWidth(pos.getX() + KONTOR_MERCHANT_SIZE_X),
                                    resources.getPercentToScreenHeight(pos.getY() + KONTOR_MERCHANT_SIZE_Y / 2)),
                            paint
                    );
                }
            }
    }
}
