package fr.univ_rouen.hansa.exceptions;

/**
 * Désigne une erreur générée a cause d'une fin de tour
 */
public class EndOfGameException extends GameException {

	public EndOfGameException() {
		this("Le jeu est terminé");
	}
	
	public EndOfGameException(String s) {
		super(s);
	}

}
