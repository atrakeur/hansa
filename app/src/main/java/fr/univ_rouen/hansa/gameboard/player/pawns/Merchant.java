package fr.univ_rouen.hansa.gameboard.player.pawns;

import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class Merchant implements Pawn {
    private final PlayerColor playerColor;
    private IHTPlayer player;

    public Merchant(IHTPlayer player) {
        this.player = player;
        this.playerColor = player.getPlayerColor();
    }

    public Merchant(PlayerColor player) {
        this.playerColor = player;
    }

    @Override
    public IHTPlayer getPlayer() {
        if (player != null) {
            return player;
        }

        for (IHTPlayer ihtPlayer : TurnManager.getInstance().getPlayers()) {
            if (ihtPlayer.getPlayerColor() == playerColor) {
                return ihtPlayer;
            }
        }

        throw new GameException("Player not found");
    }
}
