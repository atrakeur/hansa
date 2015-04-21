package fr.univ_rouen.hansa.save.dao.gameboard;

import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusActiones;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusEscritoire;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusKontor;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusPermutation;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusRemovePawns;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusState;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.save.dao.Dao;

public class BonusMarkerDao implements Dao<IBonusMarker> {
    private IBonusMarker.BonusType type;
    private BonusState state;

    public BonusMarkerDao() {
    }

    public BonusMarkerDao(IBonusMarker bonusMarker) {
        this.type = bonusMarker.getType();
        this.state = bonusMarker.getState();
    }

    @Override
    public IBonusMarker daoToEntity() {
        if (type == IBonusMarker.BonusType.BonusActiones) {
            //FIXME wrong value
            return new BonusActiones(0);
        } else if (type == IBonusMarker.BonusType.BonusEscritoire) {
            return new BonusEscritoire();
        } else if (type == IBonusMarker.BonusType.BonusKontor) {
            return new BonusKontor();
        } else if (type == IBonusMarker.BonusType.BonusPermutation) {
            return new BonusPermutation();
        } else if (type == IBonusMarker.BonusType.BonusRemovePawns) {
            return new BonusRemovePawns();
        }

        throw new GameException("Error during loading : wrong bonus marker type");
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
