package fr.univ_rouen.hansa.exceptions;

/**
 * Exception triggered by an invalid Pawn
 */
public class InvalidPionException extends GameException{

    public InvalidPionException() {
        this("Type de pion incorrect");
    }

    public InvalidPionException(String s) {
        super(s);
    }

}
