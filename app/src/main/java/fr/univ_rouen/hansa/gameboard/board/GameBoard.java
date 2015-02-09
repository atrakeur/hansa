package fr.univ_rouen.hansa.gameboard.board;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.view.display.HansaGameBoardDrawer;
import fr.univ_rouen.hansa.view.display.IDrawable;
import fr.univ_rouen.hansa.view.display.IDrawer;

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
}
