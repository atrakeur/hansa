package fr.univ_rouen.hansa.gameboard.player;

import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;

public class HTPlayer implements IHTPlayer {
    private final PlayerColor color;
    private int action;

    public HTPlayer(PlayerColor color, int startingPlace) {

        this.color = color;
        this.action = 2;
    }

    @Override
    public IEscritoire getEscritoire() {
        //TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public PlayerColor getPlayerColor() {
        return color;
    }

    @Override
    public int getCount() {
        //TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public int getActionNumber() {
        return action;
    }
    @Override
    public void setActionNumber(int i) {
        action = action + i;

    }
}
