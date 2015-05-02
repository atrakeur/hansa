package fr.univ_rouen.hansa.exceptions;

public class FinishedRoundException extends GameException {

    public FinishedRoundException() {
        this("Round fini");
    }

    public FinishedRoundException(String s) {
        super(s);
    }

}

