package fr.univ_rouen.hansa.exceptions;

/**
 * Exception triggered by a GameLogic
 */
public class GameException extends RuntimeException {

    public GameException()
    {
        this("Game Exception");
    }

    public GameException(String message)
    {
        super(message);
    }

}
