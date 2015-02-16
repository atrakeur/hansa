package fr.univ_rouen.hansa.gameboard.player.pawns;

import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class Trader implements Pawn {
    private IHTPlayer player;

    public Trader(IHTPlayer player) {
        this.player = player;
    }

    @Override
    public IHTPlayer getPlayer() {
        return player;
    }
}
