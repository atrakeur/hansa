package fr.univ_rouen.hansa.exceptions;

/**
 * Exception triggered when a round isn't finished correctly
 */
public class UnfinishedRoundException extends GameException {

    public UnfinishedRoundException() {
        this("Round non fini");
    }

    public UnfinishedRoundException(String s) {
        super(s);
    }

}
