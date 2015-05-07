package fr.univ_rouen.hansa.save.dao.gameboard;

import fr.univ_rouen.hansa.save.dao.Dao;
import fr.univ_rouen.hansa.view.IPosition;
import fr.univ_rouen.hansa.view.Position;

public class PositionDao implements Dao<IPosition> {
    private float x;
    private float y;

    public PositionDao() {
    }

    public PositionDao(IPosition position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    @Override
    public IPosition daoToEntity() {
        return new Position(x, y);
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
