package fr.univ_rouen.hansa.gameboard;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.exceptions.UnfinishedRoundException;
import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class TurnManager {
    public enum NextTurnRequire {
        actiones("Il vous reste des Actions"),
        bonusMarkers("Il vous restes des Jetons Bonus à placer"),
        none("");

        private String message;
        NextTurnRequire(String message){
            this.message = message;
        }
        public String getMessage(){
            return message;
        }
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
        NextTurnRequire req;
        if ((req = isNextTurnAvailible()) != NextTurnRequire.none && !force) {
            throw new UnfinishedRoundException(req.getMessage());
        }

        if (++position >= players.size()) {
            position = 0;
        }

        getCurrentPlayer().newTurn();
    }

    public NextTurnRequire isNextTurnAvailible() {
        if (getCurrentPlayer().getActionNumber() > 0) {
            return NextTurnRequire.actiones;
        } else if (getCurrentPlayer().getEscritoire().getTinPlateContent().size() > 0) {
            return NextTurnRequire.bonusMarkers;
        }

        return NextTurnRequire.none;
    }
}
