package fr.univ_rouen.hansa.save.dao.gameboard.cities;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.save.dao.gameboard.PawnDao;
import fr.univ_rouen.hansa.save.dao.gameboard.PawnType;

public class KontorDao {

    private PawnType pawnType;
    private boolean victoryPoint;
    private PawnDao pawn;
    private Privillegium privillegium;

    public KontorDao(IKontor<Pawn> kontor) {
        if (kontor.getPawnClass().equals(Trader.class)) {
            this.pawnType = PawnType.Trader;
        } else {
            this.pawnType = PawnType.Merchant;
        }

        this.victoryPoint = kontor.hasVictoryPoint();
        this.privillegium = kontor.getPrivillegium();

        Pawn kontorPawn = kontor.popPawn();

        if (kontorPawn != null) {
            this.pawn = new PawnDao(kontorPawn);
            kontor.pushPawn(kontorPawn);
        } else {
            this.pawn = null;
        }
    }
}
