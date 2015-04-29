package fr.univ_rouen.hansa.save.dao.players;

import fr.univ_rouen.hansa.gameboard.player.HTComputer;
import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.save.dao.Dao;

public class PlayerDao implements Dao<IHTPlayer> {

    private PlayerColor color;
    private EscritoireDao escritoire;
    private int action;
    //TODO fix that
    //private Strategy computerStrategy;

    public PlayerDao() {
    }

    public PlayerDao(IHTPlayer player) {
        this.color = player.getPlayerColor();
        this.escritoire = new EscritoireDao(player.getEscritoire());
        this.action = player.getActionNumber();

        if (player instanceof HTComputer) {
            //TODO fix that
            //computerStrategy = ((HTComputer) player).getStrategy();
        }
    }

    @Override
    public IHTPlayer daoToEntity() {
        //TODO fix that
        /*if (computerStrategy != null) {
            return new HTComputer(
                    color,
                    escritoire.daoToEntity(),
                    action,
                    computerStrategy.getComputerStrategy()
            );
        }*/

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

    //TODO fix that
    /*public Strategy getComputerStrategy() {
        return computerStrategy;
    }

    public void setComputerStrategy(Strategy computerStrategy) {
        this.computerStrategy = computerStrategy;
    }*/
}
