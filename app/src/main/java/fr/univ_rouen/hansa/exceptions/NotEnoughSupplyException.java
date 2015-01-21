package fr.univ_rouen.hansa.exceptions;

public class NotEnoughSupplyException extends GameException {

    public NotEnoughSupplyException() {
        this("Pas assez de ressource");
    }

    public NotEnoughSupplyException(String s) {
        super(s);
    }

}
