package fr.univ_rouen.hansa.exceptions;

/**
 * Exception triggered by a pending operation not terminated prior to requested action
 */
public class WaitingOperationException extends Exception {

	public WaitingOperationException() {
		this("Operation en attente");
	}
	
	public WaitingOperationException(String s) {
		super(s);
	}

}
