package fr.univ_rouen.hansa.gameboard;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.actions.MovementFactory;
import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.activity.GameActivity;
import fr.univ_rouen.hansa.exceptions.UnfinishedRoundException;
import fr.univ_rouen.hansa.gameboard.player.HTComputer;
import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.strategies.RandomStrategy;

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

    public void initFromSave(List<IHTPlayer> players, int position) {
        this.players.clear();
        this.players.addAll(players);

        this.position = position;
    }

    public void addPlayers(List<PlayerColor> playersColors) {
        List<Object> playersDefs = Lists.newArrayList();

        for (PlayerColor color : playersColors) {
            playersDefs.add("Player");
        }

        this.addPlayers(playersDefs, playersColors);
    }

    public void addPlayers(List<Object> playersDefs, List<PlayerColor> playersColors) {
        if (playersColors.size() != playersDefs.size()) {
            throw new IllegalArgumentException("PlayerDef and players colors must be of the same size");
        }

        if (players.size() > 0) {
            players.clear();
        }

        position = 0;

        for (int i = 0; i < playersDefs.size(); i++) {
            PlayerColor color = playersColors.get(i);
            Object playerDef = playersDefs.get(i);

            IHTPlayer player = null;
            if (playerDef.equals("Player")) {
                player = new HTPlayer(color, i + 1);
            } else {
                player = new HTComputer(color, i + 1, new RandomStrategy());
            }
            players.add(player);
        }
    }

    public IHTPlayer getCurrentPlayer() {
        return players.get(position);
    }

    public IHTPlayer getCurrentPlayingPlayer() {
        //Special case when there is a pawn to replace
        if (MovementManager.getInstance().hasPawnToReplace()) {
            return MovementManager.getInstance().getPawnToReplace().getPlayer();
        }

        return this.getCurrentPlayer();
    }

    public void nextPlayer(boolean force) {
        if (isNextTurnAvailable() != nextTurnRequire.none && !force) {
            throw new UnfinishedRoundException();
        }

        //Cas spécial si on est en train de replacer les pions d'un autre joueur
        if (getCurrentPlayer() != getCurrentPlayingPlayer()) {
            IMovement m = MovementFactory.getInstance().makeMovement(null, null);
            MovementManager.getInstance().doMove(m);
        } else {
            if (++position >= players.size()) {
                position = 0;
            }

            getCurrentPlayer().newTurn();
            MovementManager.getInstance().nextTurn();
            GameActivity.getInstance().onResume();
        }
    }

    public nextTurnRequire isNextTurnAvailable() {
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

    /**
     * Return a copy of the list of the players
     *
     * @return a copy of the list of the players
     */
    public List<IHTPlayer> getPlayers() {
        return Lists.newArrayList(players);
    }

    public int getPosition() {
        return position;
    }
}
