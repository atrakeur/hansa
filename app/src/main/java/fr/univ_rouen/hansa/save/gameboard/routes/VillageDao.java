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

        if (!village.isEmpty()) {
            Pawn villagePawn = village.pullPawn();
            this.pawn = new PawnDao(villagePawn);
            village.pushPawn(villagePawn);
        } else {
            this.pawn = null;
        }
    }
}
