package fr.univ_rouen.hansa.Exceptions;

/**
 * Erreur déclenchée si il n'y a plus de place dans la ville ou sur la route
 */
public class NoPlaceException extends GameException {

	public NoPlaceException() {
		this("Pas de place disponible");
	}
	
	public NoPlaceException(String s) {
		super(s);
	}

}
