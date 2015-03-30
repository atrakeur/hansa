package fr.univ_rouen.hansa.exceptions;

import fr.univ_rouen.hansa.view.interactions.IClickableArea;

public class ReplaceMovedPawn extends GameException {

    private IClickableArea destination = null;

    public ReplaceMovedPawn() {
        this("Un pion doit etre déplacé avant d'effectuer l'action");
    }

    public ReplaceMovedPawn(String s) {
        super(s);
    }

    public ReplaceMovedPawn(IClickableArea destination) {
        this.destination = destination;
    }

    public IClickableArea getDestination() {
        return destination;
    }

}
