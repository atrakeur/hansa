package fr.univ_rouen.hansa.exceptions;

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
