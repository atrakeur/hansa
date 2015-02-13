package fr.univ_rouen.hansa.gameboard.board;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.PlayersBoard;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.Route;
import fr.univ_rouen.hansa.view.display.HansaGameBoardDrawer;
import fr.univ_rouen.hansa.view.display.IDrawable;
import fr.univ_rouen.hansa.view.display.IDrawer;
import fr.univ_rouen.hansa.view.interactions.IClickable;

public class GameBoard extends PlayersBoard implements IDrawable {

    private final IDrawer drawer;

    private TurnManager manager;

    GameBoard() {
        drawer = new HansaGameBoardDrawer(this);
        manager = TurnManager.getInstance();
    }

    @Override
    public IDrawer getDrawer() {
        return this.drawer;
    }

    public IClickable[] getClickables() {
        List<IClickable> cliquables = Lists.newArrayList();

        for (IRoute route: getRoutes()) {
            cliquables.addAll(route.getVillages());
        }

        return cliquables.toArray(new IClickable[cliquables.size()]);
    }
}
