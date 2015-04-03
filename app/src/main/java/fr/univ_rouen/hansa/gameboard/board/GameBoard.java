package fr.univ_rouen.hansa.gameboard.board;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.view.display.HansaGameBoardDrawer;
import fr.univ_rouen.hansa.view.display.IDrawable;
import fr.univ_rouen.hansa.view.display.IDrawer;
import fr.univ_rouen.hansa.view.interactions.HansaSupplyClickableArea;
import fr.univ_rouen.hansa.view.interactions.IClickable;
import fr.univ_rouen.hansa.view.interactions.IClickableArea;

public class GameBoard extends PlayersBoard implements IDrawable {

    private final IDrawer drawer;

    private TurnManager manager;

    GameBoard(List<PlayerColor> playersColors) {
        super();

        drawer = new HansaGameBoardDrawer(this);
        manager = TurnManager.getInstance();
        manager.addPlayers(playersColors);
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

        for (ICity city: getCities()) {
            cliquables.add(city);
            //TODO add power cliquable area
        }

        //TODO remove that shit
        cliquables.add(new IClickable() {
            @Override
            public IClickableArea getClickableArea() {
                return new HansaSupplyClickableArea();
            }
        });

        return cliquables.toArray(new IClickable[cliquables.size()]);
    }
}
