package fr.univ_rouen.hansa.save.dao.gameboard.cities;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.Kontor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.save.dao.Dao;
import fr.univ_rouen.hansa.save.dao.gameboard.PawnDao;
import fr.univ_rouen.hansa.save.dao.gameboard.PawnType;

public class KontorDao implements Dao<IKontor> {

    private PawnType pawnType;
    private boolean victoryPoint;
    private PawnDao pawn;
    private Privillegium privillegium;

    public KontorDao() {
    }

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

    @Override
    public IKontor daoToEntity() {
        IKontor kontor = null;

        if (pawnType == PawnType.Trader) {
            kontor = new Kontor(Trader.class, victoryPoint, privillegium);
        } else {
            kontor = new Kontor(Merchant.class, victoryPoint, privillegium);
        }

        if (pawn != null) {
            kontor.pushPawn(pawn.daoToEntity());
        }

        return kontor;
    }

    public PawnType getPawnType() {
        return pawnType;
    }

    public void setPawnType(PawnType pawnType) {
        this.pawnType = pawnType;
    }

    public boolean isVictoryPoint() {
        return victoryPoint;
    }

    public void setVictoryPoint(boolean victoryPoint) {
        this.victoryPoint = victoryPoint;
    }

    public PawnDao getPawn() {
        return pawn;
    }

    public void setPawn(PawnDao pawn) {
        this.pawn = pawn;
    }

    public Privillegium getPrivillegium() {
        return privillegium;
    }

    public void setPrivillegium(Privillegium privillegium) {
        this.privillegium = privillegium;
    }
}
