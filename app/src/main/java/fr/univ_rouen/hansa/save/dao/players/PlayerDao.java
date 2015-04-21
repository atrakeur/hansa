package fr.univ_rouen.hansa.save.dao.players;

import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class PlayerDao {

    private PlayerColor color;
    private EscritoireDao escritoire;
    private int action;

    public PlayerDao() {
    }

    public PlayerDao(IHTPlayer player) {
        this.color = player.getPlayerColor();
        this.escritoire = new EscritoireDao(player.getEscritoire());
        this.action = player.getActionNumber();
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
}
