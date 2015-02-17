package fr.univ_rouen.hansa.gameboard.player.pawns;

import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

public class Merchant implements Pawn {
    private final IHTPlayer player;

    public Merchant(IHTPlayer player) {
        this.player = player;
    }

    @Override
    public IHTPlayer getPlayer() {
        return player;
    }
}
