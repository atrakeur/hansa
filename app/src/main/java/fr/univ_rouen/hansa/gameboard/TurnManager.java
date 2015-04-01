package fr.univ_rouen.hansa.gameboard;

import com.google.common.collect.Lists;
import com.google.gson.annotations.Expose;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class TurnManager {
    private static TurnManager ourInstance = new TurnManager();
    @Expose
    private final List<IHTPlayer> players;
    @Expose
    private int position;

    public static TurnManager getInstance() {
        return ourInstance;
    }

    public static void loadInstance(TurnManager manager){ourInstance = manager;}

    public  List<IHTPlayer> getPlayers(){ return players;}

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
