package fr.univ_rouen.hansa.gameboard.player.pawns;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class Trader implements Pawn {
    private final PlayerColor player;

    public Trader(IHTPlayer player) {
        this.player = player.getPlayerColor();
    }

    public Trader(PlayerColor player) {
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
