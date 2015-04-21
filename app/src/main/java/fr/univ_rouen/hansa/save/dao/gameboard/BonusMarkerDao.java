package fr.univ_rouen.hansa.save.dao.gameboard;

import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusState;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;

public class BonusMarkerDao {
    private IBonusMarker.BonusType type;
    private BonusState state;

    public BonusMarkerDao() {
    }

    public BonusMarkerDao(IBonusMarker bonusMarker) {
        this.type = bonusMarker.getType();
        this.state = bonusMarker.getState();
    }

    public IBonusMarker.BonusType getType() {
        return type;
    }

    public BonusState getState() {
        return state;
    }

    public void setState(BonusState state) {
        this.state = state;
    }
}
