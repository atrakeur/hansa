package fr.univ_rouen.hansa.gameboard.player;

public interface IScorePlayer {

    /**
     * Increase score of the player
     *
     * @post if the score is upper 20, EndOfGameException is launch
     */
    public void increaseScore();
    
    /**
     * Decrease score of the player
     */
    public void decreaseScore();

    /**
     * Return the current score of the player
     *
     * @return the current score
     */
    public int getScore();

    /**
     * Return the final score after the end of the game, with all the calculs
     *
     * @return the final score
     */
    public int getFinalScore();

}
