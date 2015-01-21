package fr.univ_rouen.hansa.exceptions;

public class UnfinishedRoundException extends GameException {

    public UnfinishedRoundException() {
        this("Round non fini");
    }

    public UnfinishedRoundException(String s) {
        super(s);
    }

}
