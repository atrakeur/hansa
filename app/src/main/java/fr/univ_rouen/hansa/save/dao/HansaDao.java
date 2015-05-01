package fr.univ_rouen.hansa.save.dao;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.save.dao.gameboard.GameBoardDao;
import fr.univ_rouen.hansa.save.dao.players.PlayerDao;

public class HansaDao {
    private GameBoardDao gameBoard;
    private int currentPlayer;
    private List<PlayerDao> players;

    public HansaDao() {
    }

    public HansaDao(GameBoard gameBoard) {
        this.gameBoard = new GameBoardDao(gameBoard);
        this.currentPlayer = TurnManager.getInstance().getPosition();
        this.players = Lists.newArrayList();

        for (IHTPlayer player : TurnManager.getInstance().getPlayers()) {
            players.add(new PlayerDao(player));
        }
    }

    public GameBoard getGameBoardEntity() {
        return gameBoard.daoToEntity();
    }

    public List<IHTPlayer> getPlayersEntities() {
        List<IHTPlayer> playersEntities= Lists.newArrayList();

        for (PlayerDao playerDao : players) {
            playersEntities.add(playerDao.daoToEntity());
        }

        return playersEntities;
    }

    public GameBoardDao getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoardDao gameBoard) {
        this.gameBoard = gameBoard;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public List<PlayerDao> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDao> players) {
        this.players = players;
    }
}
