package fr.univ_rouen.hansa.exceptions;

/**
 * Exception triggered when an action that isn't available is used
 */
public class NotAvailableActionException extends GameException {

	public NotAvailableActionException() {
		this("Action non disponible");
	}
	
	public NotAvailableActionException(String s) {
		super(s);
	}

}
