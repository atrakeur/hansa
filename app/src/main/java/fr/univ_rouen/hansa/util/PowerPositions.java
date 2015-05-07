package fr.univ_rouen.hansa.util;

import com.google.common.collect.Maps;

import java.util.EnumMap;

import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.view.IPosition;
import fr.univ_rouen.hansa.view.Position;

public class PowerPositions {

    public static final IPosition CLAVISURBIS  = new Position(0.95f, 0.9f);
    public static final IPosition ACTIONES     = new Position(0.64f, 0.85f);
    public static final IPosition PRIVILEGIUM  = new Position(0.561f, 0.05f);
    public static final IPosition LIBERSOPHIAE = new Position(0.1f, 0.145f);
    public static final IPosition BURSA        = new Position(0.94f, 0.06f);

    public static final EnumMap<Power, IPosition> powerToPosition = Maps.newEnumMap(Power.class);

    static {
        powerToPosition.put(Power.ClavisUrbis, CLAVISURBIS);
        powerToPosition.put(Power.Actiones, ACTIONES);
        powerToPosition.put(Power.Privillegium, PRIVILEGIUM);
        powerToPosition.put(Power.LiberSophiae, LIBERSOPHIAE);
        powerToPosition.put(Power.Bursa, BURSA);
    }

}
