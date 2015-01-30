package fr.univ_rouen.hansa.gameboard.player;

import fr.univ_rouen.hansa.gameboard.player.escritoire.Escritoire;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;

public class HTPlayer implements IHTPlayer {
    private final PlayerColor color;
    private final IEscritoire escritoire;

    public HTPlayer(PlayerColor color, int startingPlace) {
        this.color = color;

        this.escritoire = new Escritoire(startingPlace);
    }

    @Override
    public IEscritoire getEscritoire() {
        return escritoire;
    }

    @Override
    public PlayerColor getPlayerColor() {
        return color;
    }
}
