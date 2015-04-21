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

    public EscritoireDao(IEscritoire escritoire) {
        clavisUrbis = Lists.newArrayList();
        actiones = Lists.newArrayList();
        privilegium = Lists.newArrayList();
        liberSophiae = Lists.newArrayList();
        bursa = Lists.newArrayList();

        List<List<Pawn>> saveList = escritoire.savePowers();

        for (Pawn pawn : saveList.get(0)) {
            clavisUrbis.add(new PawnDao(pawn));
        }

        for (Pawn pawn : saveList.get(1)) {
            clavisUrbis.add(new PawnDao(pawn));
        }

        for (Pawn pawn : saveList.get(2)) {
            clavisUrbis.add(new PawnDao(pawn));
        }

        for (Pawn pawn : saveList.get(3)) {
            clavisUrbis.add(new PawnDao(pawn));
        }

        for (Pawn pawn : saveList.get(4)) {
            clavisUrbis.add(new PawnDao(pawn));
        }

        stock = new PawnListDao(escritoire.getStock());
        supply = new PawnListDao(escritoire.getSupply());
    }
}
