package fr.univ_rouen.hansa.Exceptions;

public class UnfinishedRoundException extends GameException {

    public UnfinishedRoundException() {
        this("Round non fini");
    }

    public UnfinishedRoundException(String s) {
        super(s);
    }

}
