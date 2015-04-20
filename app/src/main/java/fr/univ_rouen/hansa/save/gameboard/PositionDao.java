package fr.univ_rouen.hansa.save.gameboard;

import fr.univ_rouen.hansa.view.IPosition;

public class PositionDao {
    private float x;
    private float y;

    public PositionDao(IPosition position) {
        this.x = position.getX();
        this.y = position.getY();
    }
}
