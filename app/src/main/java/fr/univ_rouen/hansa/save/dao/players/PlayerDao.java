package fr.univ_rouen.hansa.save.dao.players;

import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class PlayerDao {

    private PlayerColor color;
    private EscritoireDao escritoire;
    private int action;

    public PlayerDao(IHTPlayer player) {
        this.color = player.getPlayerColor();
        this.escritoire = new EscritoireDao(player.getEscritoire());
        this.action = player.getActionNumber();
    }

}
