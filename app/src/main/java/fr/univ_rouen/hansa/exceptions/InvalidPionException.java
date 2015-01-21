package fr.univ_rouen.hansa.exceptions;

public class InvalidPionException extends GameException{

    public InvalidPionException() {
        this("Type de pion incorrect");
    }

    public InvalidPionException(String s) {
        super(s);
    }

}
