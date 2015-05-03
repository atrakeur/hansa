package fr.univ_rouen.hansa.gameboard.cities;


import fr.univ_rouen.hansa.util.PowerPositions;
import fr.univ_rouen.hansa.view.Position;

public enum Power {
    Null(0,0),
    ClavisUrbis(PowerPositions.CLAVISURBIS.getX(),PowerPositions.CLAVISURBIS.getY()),
    Actiones(PowerPositions.ACTIONES.getX(),PowerPositions.ACTIONES.getY()),
    Privillegium(PowerPositions.PRIVILEGIUM.getX(),PowerPositions.PRIVILEGIUM.getY()),
    LiberSophiae(PowerPositions.LIBERSOPHIAE.getX(),PowerPositions.LIBERSOPHIAE.getY()),
    Bursa(PowerPositions.BURSA.getX(),PowerPositions.BURSA.getY());

    private float x;
    private float y;
    private Position pos;
     Power(float i, float i1) {
        x = i;
        y = i1;
    }

    public float getX() {return x;}
    public float getY() {return y;}
    public Position getPosition() {return new Position(this.getX(), this.getY());}


}

