package fr.univ_rouen.hansa.GameBoard.Player.Escritoire;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.GameBoard.BonusMarkers.BonusMarker;
import fr.univ_rouen.hansa.GameBoard.Pawns.Merchant;
import fr.univ_rouen.hansa.GameBoard.Pawns.Pawn;
import fr.univ_rouen.hansa.GameBoard.Pawns.Trader;

public class Escritoire implements IEscritoire {
    private List<Trader> clavisUrbis;
    private List<Trader> actiones;
    private List<Trader> privilegium;
    private List<Merchant> liberSophia;
    private List<Trader> bursa;
    private List<BonusMarker> tinPlate;
    private List<BonusMarker> bonusMarkers;

    private IPawnList stock;
    private IPawnList reserve;

    public Escritoire() {
        clavisUrbis = Lists.newArrayList();
        actiones = Lists.newArrayList();
        privilegium = Lists.newArrayList();
        liberSophia = Lists.newArrayList();
        bursa = Lists.newArrayList();
        tinPlate = Lists.newArrayList();
        bonusMarkers = Lists.newArrayList();

        stock = new PawnList();
        reserve = new PawnList();

        for (int i = 0; i < INIT_CLAVIS_URBIS; i++) {
            clavisUrbis.add(new Trader());
        }

        for (int i = 0; i < INIT_ACTIONES; i++) {
            actiones.add(new Trader());
        }

        for (int i = 0; i < INIT_PRIVILEGIUM; i++) {
            privilegium.add(new Trader());
        }

        for (int i = 0; i < INIT_LIBER_SOPHIA; i++) {
            liberSophia.add(new Merchant());
        }

        for (int i = 0; i < INIT_BURSA; i++) {
            bursa.add(new Trader());
        }
    }

    @Override
    public List<BonusMarker> getTinPlateContent() {
        return null;
    }

    @Override
    public List<BonusMarker> getUnusedBonusMarker() {
        return null;
    }

    @Override
    public List<BonusMarker> getUsedBonusMarker() {
        return null;
    }

    @Override
    public int bonusMarkerCount() {
        return bonusMarkers.size();
    }

    @Override
    public int clavisUrbisLevel() {
        int listSize = clavisUrbis.size();

        if (listSize == 0) {
            return 4;
        } else if (listSize == 1) {
            return 3;
        } else if (listSize == 2 || listSize == 3) {
            return 2;
        }

        return 1;
    }

    @Override
    public int actionesLevel() {
        int listSize = actiones.size();

        if (listSize % 2 == 0) {
            listSize--;
        }

        if (listSize == 0) {
            return 5;
        } else if (listSize == 1 || listSize == 2) {
            return 4;
        } else if (listSize == 3 || listSize == 4) {
            return 3;
        }

        return 2;
    }

    @Override
    public int privilegiumLevel() {
        return 4 - privilegium.size();
    }

    @Override
    public int liberSophiaLevel() {
        return 5 - privilegium.size();
    }

    @Override
    public int bursaLevel() {
        int listSize = bursa.size();

        if (listSize == 0) {
            return Integer.MAX_VALUE;
        } else if (listSize == 1) {
            return 7;
        } else if (listSize == 2) {
            return 5;
        }

        return 3;
    }

    @Override
    public Pawn increaseClavisUrbis() {
        return increasePower(clavisUrbis);
    }

    @Override
    public Pawn increaseActiones() {
        return increasePower(actiones);
    }

    @Override
    public Pawn increasePrivilegium() {
        return increasePower(privilegium);
    }

    @Override
    public Pawn increaseLiberSophia() {
        return increasePower(liberSophia);
    }

    @Override
    public Pawn increaseBursa() {
        return increasePower(bursa);
    }

    @Override
    public boolean moveStockToReserve(int merchants, int traders) {
        List<Pawn> pawns = Lists.newArrayList();

        try {
            pawns.addAll(stock.getMerchants(merchants));
            pawns.addAll(stock.getTraders(traders));

            reserve.addPawns(pawns);
        } catch (IllegalStateException e) {
            stock.addPawns(pawns); //FIXME use game exeptions

            throw e;
        }

        return true;
    }

    @Override
    public void addToStock(List<Pawn> pawns) {
        if (pawns == null) {
            throw new IllegalArgumentException();
        }

        stock.addPawns(pawns);
    }

    @Override
    public void addToReserve(List<Pawn> pawns) {
        if (pawns == null) {
            throw new IllegalArgumentException();
        }

        reserve.addPawns(pawns);
    }

    private Pawn increasePower(List<? extends Pawn> pawns) { //FIXME use game exeptions
        if (pawns.size() <= 0) {
            throw new IllegalStateException("Power already increase at is max.");
        }

        return pawns.remove(pawns.size() - 1);
    }
}