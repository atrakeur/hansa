package fr.univ_rouen.hansa.gameboard;

import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

public class TurnManager {
    private static TurnManager ourInstance = new TurnManager();

    public static TurnManager getInstance() {
        return ourInstance;
    }

    private TurnManager() {
    }

    private IHTPlayer getCurrentPlayer() {
        throw new UnsupportedOperationException();
        //TODO implementation
    }
}
