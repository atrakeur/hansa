package fr.univ_rouen.hansa.gameboard.player;

import fr.univ_rouen.hansa.gameboard.GameBoard;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;

public class ScorePlayer implements IScorePlayer {
    private int score;

    public ScorePlayer() {
        score = 0;
    }

    @Override
    public void increaseScore() {
        score++;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int getFinalScore() {
        int finalscore = score;

        //TODO
        throw new UnsupportedOperationException();
    }
}