package fr.univ_rouen.hansa.gameboard.board;

import com.google.common.collect.Lists;

import java.util.EnumMap;
import java.util.List;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.view.display.HansaGameBoardDrawer;
import fr.univ_rouen.hansa.view.display.IDrawable;
import fr.univ_rouen.hansa.view.display.IDrawer;
import fr.univ_rouen.hansa.view.interactions.HansaPowerClickableArea;
import fr.univ_rouen.hansa.view.interactions.HansaSupplyClickableArea;
import fr.univ_rouen.hansa.view.interactions.IClickable;
import fr.univ_rouen.hansa.view.interactions.IClickableArea;

public class GameBoard extends RouteBoard implements IDrawable {

    public static boolean LOAD_FROM_SAVE;

    private final IDrawer drawer;
    private final EnumMap<Power, HansaPowerClickableArea> powerClickables;

    public GameBoard(int map) {
        super();

        if (map == 1) {
            this.setBackground(R.drawable.plateau23);
        } else {
            this.setBackground(R.drawable.plateau45);
        }

        drawer = new HansaGameBoardDrawer(this);
        powerClickables = new EnumMap<Power, HansaPowerClickableArea>(Power.class);
    }

    @Override
    public IDrawer getDrawer() {
        return this.drawer;
    }

    public IClickable[] getClickables() {
        List<IClickable> cliquables = Lists.newArrayList();

        for (IRoute route : getRoutes()) {
            cliquables.addAll(route.getVillages());
        }

        for (ICity city : getCities()) {
            cliquables.add(city);

            if (city.getPower() != Power.Null) {
                if (!powerClickables.containsKey(city.getPower())) {
                    powerClickables.put(city.getPower(), new HansaPowerClickableArea(city.getPower()));
                }
                cliquables.add(powerClickables.get(city.getPower()));
            }
        }

        return cliquables.toArray(new IClickable[cliquables.size()]);
    }
}
