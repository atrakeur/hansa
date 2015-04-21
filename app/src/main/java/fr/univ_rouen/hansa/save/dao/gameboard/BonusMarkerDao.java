package fr.univ_rouen.hansa.save.dao.gameboard;

import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusState;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;

public class BonusMarkerDao {
    private final String type;
    private BonusState state;

    public BonusMarkerDao(IBonusMarker bonusMarker) {
        this.type = bonusMarker.getType();
        this.state = bonusMarker.getState();

        //TODO save other arguments, but need an enum fot the type (!!)

    }
}
