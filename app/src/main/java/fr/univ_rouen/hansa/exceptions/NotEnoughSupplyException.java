package fr.univ_rouen.hansa.exceptions;

/**
 * Exception triggered when there is not enough supply to do the requested action
 */
public class NotEnoughSupplyException extends GameException {

    public NotEnoughSupplyException() {
        this("Pas assez de ressource");
    }

    public NotEnoughSupplyException(String s) {
        super(s);
    }

}
