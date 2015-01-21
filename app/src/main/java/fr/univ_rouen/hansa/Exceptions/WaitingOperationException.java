package fr.univ_rouen.hansa.Exceptions;

/**
 * Erreur générée a cause d'une opération en attente
 */
public class WaitingOperationException extends Exception {

	public WaitingOperationException() {
		this("Operation en attente");
	}
	
	public WaitingOperationException(String s) {
		super(s);
	}

}
