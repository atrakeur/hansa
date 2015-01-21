package fr.univ_rouen.hansa.Exceptions;

public class GameException extends Exception {

    public GameException()
    {
        this("Game Exception");
    }

    public GameException(String message)
    {
        super(message);
    }

}
