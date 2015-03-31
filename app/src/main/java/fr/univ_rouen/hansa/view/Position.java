package fr.univ_rouen.hansa.view;

public class Position implements IPosition {

    private final float x;

    private final float y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Position(double x, double y) {
        this.x = (float)x;
        this.y = (float)y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float[] getPosition() {
        return new float[] {x, y};
    }
}
