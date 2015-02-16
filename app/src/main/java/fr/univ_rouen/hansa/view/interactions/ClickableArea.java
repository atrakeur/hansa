package fr.univ_rouen.hansa.view.interactions;

import fr.univ_rouen.hansa.view.interactions.IClickableArea;

public abstract class ClickableArea implements IClickableArea {

    private final Type type;

    //TODO add id to implementation of Bonus Place
    public ClickableArea(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

}
