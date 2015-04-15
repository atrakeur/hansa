package fr.univ_rouen.hansa.gameboard;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class TurnManager {
    private static TurnManager ourInstance = new TurnManager();

    private List<IHTPlayer> players;

    private int position;

    public static TurnManager getInstance() {
        return ourInstance;
    }

    public List<IHTPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<IHTPlayer> players) {
        this.players = players;
    }

    public void setCurrentPlayer(int position) {
        this.position = position;
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

    public void nextPlayer() {
        if (++position >= players.size()) {
            position = 0;
        }
    }
}
