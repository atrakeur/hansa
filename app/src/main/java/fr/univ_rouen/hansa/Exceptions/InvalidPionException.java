package fr.univ_rouen.hansa.Exceptions;

public class InvalidPionException extends GameException{

    public InvalidPionException() {
        this("Type de pion incorrect");
    }

    public InvalidPionException(String s) {
        super(s);
    }

}
