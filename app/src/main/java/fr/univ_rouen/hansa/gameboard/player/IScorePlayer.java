package fr.univ_rouen.hansa.gameboard.player;

public interface IScorePlayer {

    /**
     * Increase score of the player
     *
     * @post if the score is upper 20, EndOfGameException is launch
     */
    void increaseScore();

    /**
     * Decrease score of the player
     */
    void decreaseScore();

    /**
     * Return the current score of the player
     *
     * @return the current score
     */
    int getScore();

    /**
     * set score
     */
    void setScore(int score);

}
