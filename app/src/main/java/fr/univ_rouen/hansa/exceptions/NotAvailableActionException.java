package fr.univ_rouen.hansa.exceptions;

/**
 * Erreur déclenchée par une action non disponible
 */
public class NotAvailableActionException extends GameException {

	public NotAvailableActionException() {
		this("Action non disponible");
	}
	
	public NotAvailableActionException(String s) {
		super(s);
	}

}