package fr.univ_rouen.hansa.save.dao.players;

import android.util.Log;

import fr.univ_rouen.hansa.ai.ComputerStrategy;
import fr.univ_rouen.hansa.ai.StrategyType;
import fr.univ_rouen.hansa.gameboard.player.HTComputer;
import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.save.dao.Dao;

public class PlayerDao implements Dao<IHTPlayer> {

    private PlayerColor color;
    private EscritoireDao escritoire;
    private int action;
    private StrategyType computerStrategy;

    public PlayerDao() {
    }

    public PlayerDao(IHTPlayer player) {
        this.color = player.getPlayerColor();
        this.escritoire = new EscritoireDao(player.getEscritoire());
        this.action = player.getActionNumber();

        if (player instanceof HTComputer) {
            for (StrategyType strategyType : StrategyType.values()) {
                ComputerStrategy strategy = ((HTComputer) player).getStrategy();

                if (strategyType.getInstance().equals(strategy.getClass())) {
                    computerStrategy = strategyType;
                    break;
                }
            }

            if (computerStrategy == null) {
                Log.d("Error in Save", "No strategy found for AI, replace by random strategy");
                computerStrategy = StrategyType.randomStrategy;
            }
        }
    }

    @Override
    public IHTPlayer daoToEntity() {
        if (computerStrategy != null) {
            return new HTComputer(
                    color,
                    escritoire.daoToEntity(),
                    action,
                    computerStrategy
            );
        }

        return new HTPlayer(color, escritoire.daoToEntity(), action);
    }

    public PlayerColor getColor() {
        return color;
    }

    public void setColor(PlayerColor color) {
        this.color = color;
    }

    public EscritoireDao getEscritoire() {
        return escritoire;
    }

    public void setEscritoire(EscritoireDao escritoire) {
        this.escritoire = escritoire;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public StrategyType getComputerStrategy() {
        return computerStrategy;
    }

    public void setComputerStrategy(StrategyType computerStrategy) {
        this.computerStrategy = computerStrategy;
    }
}
