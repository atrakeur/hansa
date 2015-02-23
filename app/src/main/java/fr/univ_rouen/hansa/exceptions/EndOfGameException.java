package fr.univ_rouen.hansa.exceptions;

/**
 * Exception triggered because of an End game event
 */
public class EndOfGameException extends GameException {

	public EndOfGameException() {
		this("Le jeu est terminé");
	}
	
	public EndOfGameException(String s) {
		super(s);
	}

}
