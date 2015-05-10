package fr.univ_rouen.hansa.gameboard.player.pawns;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class Merchant implements Pawn {
    private final PlayerColor player;

    public Merchant(IHTPlayer player) {
        this.player = player.getPlayerColor();
    }

    public Merchant(PlayerColor player) {
        this.player = player;
    }

    @Override
    public IHTPlayer getPlayer() {
        for (IHTPlayer ihtPlayer : TurnManager.getInstance().getPlayers()) {
            if (ihtPlayer.getPlayerColor() == player) {
                return ihtPlayer;
            }
        }

        return null;
    }
}
