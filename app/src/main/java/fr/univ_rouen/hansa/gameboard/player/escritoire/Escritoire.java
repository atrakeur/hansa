package fr.univ_rouen.hansa.gameboard.player.escritoire;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.exceptions.NotEnoughSupplyException;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.cities.Power;

public class Escritoire implements IEscritoire {
    private List<Trader> clavisUrbis;
    private List<Trader> actiones;
    private List<Trader> privilegium;
    private List<Merchant> liberSophiae;
    private List<Trader> bursa;
    private List<IBonusMarker> tinPlate;
    private List<IBonusMarker> bonusMarkers;

    private IPawnList stock;
    private IPawnList supply;

    public Escritoire(int startingPlace) {
        clavisUrbis = Lists.newArrayList();
        actiones = Lists.newArrayList();
        privilegium = Lists.newArrayList();
        liberSophiae = Lists.newArrayList();
        bursa = Lists.newArrayList();
        tinPlate = Lists.newArrayList();
        bonusMarkers = Lists.newArrayList();

        stock = new PawnList();
        supply = new PawnList();

        List<Pawn> initStock = Lists.newArrayList();
        List<Pawn> initSupply = Lists.newArrayList();

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
            liberSophiae.add(new Merchant());
        }

        for (int i = 0; i < INIT_BURSA; i++) {
            bursa.add(new Trader());
        }

        for (int i = 0; i < INIT_STOCK - startingPlace; i++) {
            initStock.add(new Trader());
        }

        stock.addPawns(initStock);

        for (int i = 0; i < INIT_SUPPLY + startingPlace; i++) {
            initSupply.add(new Trader());
        }

        initSupply.add(new Merchant());

        supply.addPawns(initSupply);
    }

    @Override
    public List<IBonusMarker> getTinPlateContent() {
        return tinPlate;
    }

    @Override
    public List<IBonusMarker> getUnusedBonusMarker() {
        return null;
    }

    @Override
    public List<IBonusMarker> getUsedBonusMarker() {
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
        return 5 - liberSophiae.size();
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
    public void increasePower(Power power) {
        Pawn pawn;

        switch (power) {
            case Actiones:
                increasePower(actiones);
                break;
            case Bursa:
                increasePower(bursa);
                break;
            case ClavisUrbis:
                increasePower(clavisUrbis);
                break;
            case LiberSophiae:
                increasePower(liberSophiae);
                break;
            case Privillegium:
                increasePower(privilegium);
                break;
            default:
                throw new GameException("Power is set to " + power + ", wait another value");
        }


    }

    @Override
    public void decreasePower(Power power) {
        switch (power) {
            case Actiones:
                actiones.addAll(supply.popTraders(1));
                break;
            case Bursa:
                bursa.addAll(supply.popTraders(1));
                break;
            case ClavisUrbis:
                clavisUrbis.addAll(supply.popTraders(1));
                break;
            case LiberSophiae:
                liberSophiae.addAll(supply.popMerchants(1));
                break;
            case Privillegium:
                privilegium.addAll(supply.popTraders(1));
                break;
            default:
                throw new GameException("Power is set to " + power + ", wait another value");
        }
    }

    @Override
    public boolean moveStockToSupply(int merchants, int traders) {
        List<Pawn> pawns = Lists.newArrayList();

        try {
            pawns.addAll(stock.popMerchants(merchants));
            pawns.addAll(stock.popTraders(traders));

            supply.addPawns(pawns);
        } catch (NotEnoughSupplyException e) {
            stock.addPawns(pawns);

            throw e;
        }

        return true;
    }

    @Override
    public boolean moveSupplyToStock(int merchants, int traders) {
        List<Pawn> pawns = Lists.newArrayList();

        try {
            pawns.addAll(supply.popMerchants(merchants));
            pawns.addAll(supply.popTraders(traders));

            stock.addPawns(pawns);
        } catch (NotEnoughSupplyException e) {
            supply.addPawns(pawns);

            throw e;
        }

        return true;
    }

    @Override
    public List<Pawn> popFromSupply(int merchants, int traders) {
        List<Pawn> pawns = Lists.newArrayList();

        try {
            pawns.addAll(supply.popMerchants(merchants));
            pawns.addAll(supply.popTraders(traders));
        } catch (NotEnoughSupplyException e) {
            supply.addPawns(pawns);

            throw e;
        }

        return pawns;
    }

    //Attention, cette méthode ne doit être appelé que dans le cas d'annulation d'une action
    @Override
    public List<Pawn> removeFromStock(List<Pawn> pawns) {
        try {
            return stock.removePawns(pawns);
        } catch (Exception e) {
            throw new NotEnoughSupplyException();
        }
    }

    @Override
    public List<Pawn> removeFromSupply(List<Pawn> pawns) {
        try {
            return supply.removePawns(pawns);
        } catch (Exception e) {
            throw new NotEnoughSupplyException();
        }
    }

    @Override
    public void addToStock(List<Pawn> pawns) {
        if (pawns == null) {
            throw new IllegalArgumentException();
        }

        stock.addPawns(pawns);
    }

    @Override
    public void addToSupply(List<Pawn> pawns) {
        if (pawns == null) {
            throw new IllegalArgumentException();
        }

        supply.addPawns(pawns);
    }

    private void increasePower(List<? extends Pawn> pawns) {
        if (pawns.size() <= 0) {
            throw new NotEnoughSupplyException("Power already increase at is max.");
        }

        supply.addPawns(Lists.newArrayList(pawns.remove(pawns.size() - 1)));
    }

    @Override
    public boolean enoughSupply(int merchants, int traders) {
        return supply.enoughPawns(merchants, traders);
    }

    @Override
    public IPawnList getStock() {
        return stock;
    }

    @Override
    public IPawnList getSupply() {
        return supply;
    }
}
