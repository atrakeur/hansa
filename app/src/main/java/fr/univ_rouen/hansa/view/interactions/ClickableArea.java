package fr.univ_rouen.hansa.view.interactions;

import fr.univ_rouen.hansa.view.interactions.IClickableArea;

public class ClickableArea implements IClickableArea {

    private final Type type;
    private final int id;

    //TODO add id to implementation of Bonus Place
    public ClickableArea(Type type, int id) {
        this.type = type;
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }
}
