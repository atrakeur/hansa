package fr.univ_rouen.hansa.save.dao.players;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.save.dao.gameboard.BonusMarkerDao;
import fr.univ_rouen.hansa.save.dao.gameboard.PawnDao;

public class EscritoireDao {
    private List<PawnDao> clavisUrbis;
    private List<PawnDao> actiones;
    private List<PawnDao> privilegium;
    private List<PawnDao> liberSophiae;
    private List<PawnDao> bursa;
    private List<BonusMarkerDao> tinPlate;
    private List<BonusMarkerDao> bonusMarkers;

    private PawnListDao stock;
    private PawnListDao supply;

    public EscritoireDao() {
    }

    public EscritoireDao(IEscritoire escritoire) {
        clavisUrbis = Lists.newArrayList();
        actiones = Lists.newArrayList();
        privilegium = Lists.newArrayList();
        liberSophiae = Lists.newArrayList();
        bursa = Lists.newArrayList();

        List<List<? extends Pawn>> saveList = escritoire.savePowers();

        for (Pawn pawn : saveList.get(0)) {
            clavisUrbis.add(new PawnDao(pawn));
        }

        for (Pawn pawn : saveList.get(1)) {
            actiones.add(new PawnDao(pawn));
        }

        for (Pawn pawn : saveList.get(2)) {
            privilegium.add(new PawnDao(pawn));
        }

        for (Pawn pawn : saveList.get(3)) {
            liberSophiae.add(new PawnDao(pawn));
        }

        for (Pawn pawn : saveList.get(4)) {
            bursa.add(new PawnDao(pawn));
        }

        stock = new PawnListDao(escritoire.getStock());
        supply = new PawnListDao(escritoire.getSupply());
    }

    public List<PawnDao> getClavisUrbis() {
        return clavisUrbis;
    }

    public void setClavisUrbis(List<PawnDao> clavisUrbis) {
        this.clavisUrbis = clavisUrbis;
    }

    public List<PawnDao> getActiones() {
        return actiones;
    }

    public void setActiones(List<PawnDao> actiones) {
        this.actiones = actiones;
    }

    public List<PawnDao> getPrivilegium() {
        return privilegium;
    }

    public void setPrivilegium(List<PawnDao> privilegium) {
        this.privilegium = privilegium;
    }

    public List<PawnDao> getLiberSophiae() {
        return liberSophiae;
    }

    public void setLiberSophiae(List<PawnDao> liberSophiae) {
        this.liberSophiae = liberSophiae;
    }

    public List<PawnDao> getBursa() {
        return bursa;
    }

    public void setBursa(List<PawnDao> bursa) {
        this.bursa = bursa;
    }

    public List<BonusMarkerDao> getTinPlate() {
        return tinPlate;
    }

    public void setTinPlate(List<BonusMarkerDao> tinPlate) {
        this.tinPlate = tinPlate;
    }

    public List<BonusMarkerDao> getBonusMarkers() {
        return bonusMarkers;
    }

    public void setBonusMarkers(List<BonusMarkerDao> bonusMarkers) {
        this.bonusMarkers = bonusMarkers;
    }

    public PawnListDao getStock() {
        return stock;
    }

    public void setStock(PawnListDao stock) {
        this.stock = stock;
    }

    public PawnListDao getSupply() {
        return supply;
    }

    public void setSupply(PawnListDao supply) {
        this.supply = supply;
    }
}
