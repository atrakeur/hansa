package fr.univ_rouen.hansa.gameboard;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.exceptions.UnfinishedRoundException;
import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class TurnManager {
    public enum nextTurnRequire {
        actiones,
        bonusMarkers,
        none
    }

    private static TurnManager ourInstance = new TurnManager();

    private final List<IHTPlayer> players;
    private int position;

    public static TurnManager getInstance() {
        return ourInstance;
    }

    private TurnManager() {
        players = Lists.newArrayList();
    }

    public void addPlayers(List<PlayerColor> playersColors) {
        if (players.size() > 0) {
            players.clear();
        }

        position = 0;

        int i = 0;

        for (PlayerColor color : playersColors) {
            players.add(new HTPlayer(color, ++i));
        }
    }

    public IHTPlayer getCurrentPlayer() {
        return players.get(position);
    }

    public void nextPlayer(boolean force) {
        if (isNextTurnAvailible() != nextTurnRequire.none && !force) {
            throw new UnfinishedRoundException();
        }

        if (++position >= players.size()) {
            position = 0;
        }

        getCurrentPlayer().newTurn();
        MovementManager.getInstance().nextTurn();
    }

    public nextTurnRequire isNextTurnAvailible() {
        if (actionLeft() > 0) {
            return nextTurnRequire.actiones;
        } else if (getCurrentPlayer().getEscritoire().getTinPlateContent().size() > 0) {
            return nextTurnRequire.bonusMarkers;
        }

        return nextTurnRequire.none;
    }

    /**
     * Return the number of action left of the current player
     *
     * @return int represent the number of action left
     */
    private int actionLeft() {
        return getCurrentPlayer().getActionNumber() - MovementManager.getInstance().actionCounter();
    }
}
