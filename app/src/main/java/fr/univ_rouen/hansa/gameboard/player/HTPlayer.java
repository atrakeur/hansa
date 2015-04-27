package fr.univ_rouen.hansa.gameboard.player;

import fr.univ_rouen.hansa.gameboard.player.escritoire.Escritoire;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;

public class HTPlayer extends ScorePlayer implements IHTPlayer {
    private final PlayerColor color;
    private final IEscritoire escritoire;

    private int action;

    /**
     * Init player from a saveguard
     *
     * @param color the color of the player
     * @param escritoire of the player
     * @param action number of action the player can make
     */
    public HTPlayer(PlayerColor color, IEscritoire escritoire, int action) {
        super();

        this.color = color;
        this.escritoire = escritoire;
        this.action = action;
    }

    /**
     * Init a player for a new game
     *
     * @param color the color of the player
     * @param startingPlace the place of the player at the begining of the party (1 to x)
     */
    public HTPlayer(PlayerColor color, int startingPlace) {
        super();

        this.color = color;
        this.escritoire = new Escritoire(this, startingPlace);

        this.action = 2;
    }

    @Override
    public IEscritoire getEscritoire() {
        return escritoire;
    }

    @Override
    public PlayerColor getPlayerColor() {
        return color;
    }

    @Override
    public int getActionNumber() {
        return action;
    }

    @Override
    public void setActionNumber(int i) {
        action = action + i;
    }

    @Override
    public void newTurn() {
        action = escritoire.actionesLevel();
    }


}
