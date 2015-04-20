package fr.univ_rouen.hansa.save.gameboard.routes;

import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.save.gameboard.PawnDao;
import fr.univ_rouen.hansa.save.gameboard.PositionDao;

public class VillageDao {
    private PositionDao position;
    private PawnDao pawn;

    public VillageDao(IVillage village) {
        this.position = new PositionDao(village.getPosition());

        Pawn villagePawn = village.pullPawn();

        if (villagePawn != null) {
            this.pawn = new PawnDao(villagePawn);
            village.pushPawn(villagePawn);
        } else {
            this.pawn = null;
        }
    }
}
