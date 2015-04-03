package fr.univ_rouen.hansa.exceptions;

/**
 * Exception created when there is no more space available on a route
 */
public class NoPlaceException extends GameException {

	public NoPlaceException() {
		this("Pas de place disponible");
	}
	
	public NoPlaceException(String s) {
		super(s);
	}

}
