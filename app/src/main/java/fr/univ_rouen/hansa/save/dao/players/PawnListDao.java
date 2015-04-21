package fr.univ_rouen.hansa.save.dao.players;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.player.escritoire.IPawnList;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.save.dao.gameboard.PawnDao;

public class PawnListDao {
    List<PawnDao> traders;
    List<PawnDao> merchants;

    public PawnListDao() {
    }

    public PawnListDao(IPawnList pawnList) {
        traders = Lists.newArrayList();
        merchants = Lists.newArrayList();

        for (Pawn pawn : pawnList.getTraders()) {
            traders.add(new PawnDao(pawn));
        }

        for (Pawn pawn : pawnList.getMerchants()) {
            merchants.add(new PawnDao(pawn));
        }
    }

    public List<PawnDao> getTraders() {
        return traders;
    }

    public void setTraders(List<PawnDao> traders) {
        this.traders = traders;
    }

    public List<PawnDao> getMerchants() {
        return merchants;
    }

    public void setMerchants(List<PawnDao> merchants) {
        this.merchants = merchants;
    }
}
