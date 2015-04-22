package fr.univ_rouen.hansa.view.display;

import android.graphics.Canvas;
import android.view.View;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.view.interactions.IClickable;
import fr.univ_rouen.hansa.view.utils.ResourceRepository;

public class HansaGameBoardDrawer implements IDrawer {

    private boolean debug = false;

    GameBoard board;
    List<ICity> cities;
    List<IRoute> routes;

    public HansaGameBoardDrawer(GameBoard board) {
        this.board = board;

        this.cities = board.getCities();
        this.routes = board.getRoutes();
    }

    public void load(ResourceRepository resources) {
        resources.addResource("background", board.getBackground(), 1, 1);

        for (ICity city : cities) {
            city.getDrawer().load(resources);
        }

        for (IRoute route : routes) {
            route.getDrawer().load(resources);
        }
    }

    @Override
    public void draw(ResourceRepository resources, Canvas canvas) {
        canvas.drawBitmap(resources.getScaledResource("background"), 0, 0, null);

        if (debug) {
            for (IClickable clickable: board.getClickables()) {
                clickable.getClickableArea().drawDebug(resources, canvas);
            }
        }

        for (ICity city : cities) {
            city.getDrawer().draw(resources, canvas);
        }

        for (IRoute route : routes) {
            route.getDrawer().draw(resources, canvas);
        }



    }
}
