package fr.univ_rouen.hansa.gameboard.player;

import com.google.gson.annotations.Expose;

public class ScorePlayer implements IScorePlayer {
    @Expose
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
