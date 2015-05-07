package fr.univ_rouen.hansa.save.dao.gameboard.routes;

import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.gameboard.routes.Village;
import fr.univ_rouen.hansa.save.dao.Dao;
import fr.univ_rouen.hansa.save.dao.gameboard.PawnDao;
import fr.univ_rouen.hansa.save.dao.gameboard.PositionDao;

public class VillageDao implements Dao<IVillage> {
    private PositionDao position;
    private PawnDao pawn;

    public VillageDao() {
    }

    public VillageDao(IVillage village) {
        this.position = new PositionDao(village.getPosition());

        if (!village.isEmpty()) {
            Pawn villagePawn = village.pullPawn();
            this.pawn = new PawnDao(villagePawn);
            village.pushPawn(villagePawn);
        } else {
            this.pawn = null;
        }
    }

    @Override
    public IVillage daoToEntity() {
        IVillage village = new Village(position.daoToEntity());

        if (pawn != null) {
            village.pushPawn(pawn.daoToEntity());
        }

        return village;
    }

    public PositionDao getPosition() {
        return position;
    }

    public void setPosition(PositionDao position) {
        this.position = position;
    }

    public PawnDao getPawn() {
        return pawn;
    }

    public void setPawn(PawnDao pawn) {
        this.pawn = pawn;
    }
}
