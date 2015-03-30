package fr.univ_rouen.hansa.gameboard;

import com.google.common.collect.Lists;

import java.util.List;

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
    private IHTPlayer specialPlayer;

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
        if (specialPlayer != null) {
            specialPlayer = null;
        } else {
            if (isNextTurnAvailible() != nextTurnRequire.none && !force) {
                throw new UnfinishedRoundException();
            }

            if (++position >= players.size()) {
                position = 0;
            }

            getCurrentPlayer().newTurn();
        }
    }

    public nextTurnRequire isNextTurnAvailible() {
        if (getCurrentPlayer().getActionNumber() > 0) {
            return nextTurnRequire.actiones;
        } else if (getCurrentPlayer().getEscritoire().getTinPlateContent().size() > 0) {
            return nextTurnRequire.bonusMarkers;
        }

        return nextTurnRequire.none;
    }

    /**
     * Methode use for replace pawn of other player
     *
     * @param player of the pawn who need to be replace
     */
    public void setSpecialPlayer(IHTPlayer player) {
        this.specialPlayer = player;
    }
}
