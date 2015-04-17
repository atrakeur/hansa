package fr.univ_rouen.hansa.view.display;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.List;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.view.IPosition;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class HansaRouteDrawer implements IDrawer {

    private boolean debug = false;

    public final float KONTOR_MERCHANT_SIZE_X = 0.021f;
    public final float KONTOR_MERCHANT_SIZE_Y = 0.0275f;
    public final float KONTOR_TRADER_SIZE_X = 0.014f;
    public final float KONTOR_TRADER_SIZE_Y = 0.018f;

    public final float TAVERN_SIZE = 0.060f;

    private final IRoute route;

    public HansaRouteDrawer(IRoute route) {
        this.route = route;
    }

    @Override
    public void load(ResourceRepository resources) {
        resources.addResource("bonusactiones3", R.drawable.bonusactiones3, TAVERN_SIZE, TAVERN_SIZE);
        resources.addResource("bonusactiones4", R.drawable.bonusactiones4, TAVERN_SIZE, TAVERN_SIZE);
        resources.addResource("bonusescritoire", R.drawable.bonusescritoire, TAVERN_SIZE, TAVERN_SIZE);
        resources.addResource("bonuskontor", R.drawable.bonuskontor, TAVERN_SIZE, TAVERN_SIZE);
        resources.addResource("bonuspermutation", R.drawable.bonuspermutation, TAVERN_SIZE, TAVERN_SIZE);
        resources.addResource("bonusremovepawn", R.drawable.bonusremovepawn, TAVERN_SIZE, TAVERN_SIZE);
    }

    @Override
    public void draw(ResourceRepository resources, Canvas canvas) {
        List<IVillage> villages = route.getVillages();

        Paint paint = new Paint();

        for (IVillage village : villages) {
            //ATM on dessine le trader pusi le merchant,
            // faudra dessiner l'un ou l'autre suivant le contenu...

            float posX = village.getPosition().getX();
            float posY = village.getPosition().getY();

            if (debug == true || village.getPawnType() == Merchant.class) {
                if (debug == true) {
                    paint.setColor(Color.GREEN);
                }
                else
                {
                    paint.setColor(village.getOwner().getPlayerColor().getColor());
                }

                canvas.drawOval(
                        new RectF(
                                resources.getPercentToScreenWidth(posX - KONTOR_MERCHANT_SIZE_X / 2),
                                resources.getPercentToScreenHeight(posY - KONTOR_MERCHANT_SIZE_Y / 2),
                                resources.getPercentToScreenWidth(posX + KONTOR_MERCHANT_SIZE_X / 2),
                                resources.getPercentToScreenHeight(posY + KONTOR_MERCHANT_SIZE_Y / 2)
                        ),
                        paint
                );
            }

            if (debug == true || village.getPawnType() == Trader.class) {
                if (debug == true) {
                    paint.setColor(Color.RED);
                }
                else
                {
                    paint.setColor(village.getOwner().getPlayerColor().getColor());
                }

                canvas.drawRect(
                        new RectF(
                                resources.getPercentToScreenWidth(posX - KONTOR_TRADER_SIZE_X / 2),
                                resources.getPercentToScreenHeight(posY - KONTOR_TRADER_SIZE_Y / 2),
                                resources.getPercentToScreenWidth(posX + KONTOR_TRADER_SIZE_X / 2),
                                resources.getPercentToScreenHeight(posY + KONTOR_TRADER_SIZE_Y / 2)
                        ),
                        paint
                );
            }
        }

        if (debug == true || this.route.getBonusMarker() != null) {
            String image;
            if (debug == true) {
                image = "bonusactiones3";
            } else {
                image = "bonus" + this.route.getBonusMarker().getType();
            }

            IPosition bonusPosition= this.route.getTavernPosition();

            Bitmap pict = resources.getScaledResource(image);

            int posX = resources.getPercentToScreenWidth(bonusPosition.getX()) - pict.getWidth() / 2;
            int posY = resources.getPercentToScreenHeight(bonusPosition.getY()) - pict.getHeight() / 2;

            canvas.drawBitmap(pict, posX, posY, paint);
        } else if (debug == true) {
            paint.setColor(Color.MAGENTA);
            IPosition bonusPosition= this.route.getTavernPosition();

            canvas.drawCircle(
                    resources.getPercentToScreenWidth(bonusPosition.getX()),
                    resources.getPercentToScreenHeight(bonusPosition.getY()),
                    resources.getPercentToScreenHeight(TAVERN_SIZE),
                    paint);
        }
    }

}
