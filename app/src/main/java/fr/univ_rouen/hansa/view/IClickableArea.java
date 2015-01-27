package fr.univ_rouen.hansa.view;

public interface IClickableArea {

    public enum Type {
        village,
        city,
        power,
        stock,
        supply,
        bonus
    }

    public Type getType();
    
}
