package fr.univ_rouen.hansa.gameboard.player;

import fr.univ_rouen.hansa.gameboard.player.escritoire.Escritoire;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;

public class HTPlayer extends ScorePlayer implements IHTPlayer {
    private final PlayerColor color;
    private final IEscritoire escritoire;

    public HTPlayer(PlayerColor color, int startingPlace) {
        super();

        this.color = color;

        this.escritoire = new Escritoire(this, startingPlace);
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
