package fr.univ_rouen.hansa.gameboard.player;

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
    public void decreaseScore() { score--; }

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
