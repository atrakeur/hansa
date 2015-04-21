package fr.univ_rouen.hansa.save.dao.gameboard;

import fr.univ_rouen.hansa.view.IPosition;

public class PositionDao {
    private float x;
    private float y;

    public PositionDao() {
    }

    public PositionDao(IPosition position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
