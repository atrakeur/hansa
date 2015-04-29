package fr.univ_rouen.hansa.gameboard.bonusmarkers;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.Kontor;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;


/**
 * Bonus which allow you to add a Kontor on the left of a City
 */
public class BonusKontor extends AbstractBonus implements IBonusMarker {
    private ICity city;
    private IHTPlayer player;
    private IVillage village;
    private Pawn pawn;
    private IKontor kontor;
    private List<Pawn> pawns;

    public BonusKontor() {
        super(BonusType.BonusKontor);
    }

    /**
     * initialise the city which will have a bonus Kontor
     */
    public void setCity(ICity city) {
        if (city == null) {
            throw new IllegalArgumentException();
        }
        if (city.getKontors().isEmpty()) {
            throw new IllegalStateException("City has to have at least one Kontor filed to use " +
                    "a BonusKontor on it");
        }
        this.city = city;
    }

    /**
     * initialise the pawn which will be in the Bonus Kontor
     */
    public void setVillage(IVillage village) {
        if (village == null) {
            throw new IllegalArgumentException();
        }
        if (!village.getOwner().getEscritoire().getBonusMarker().contains(this)) {
            throw new IllegalStateException("The pawn's player differs of the marker's owner");
        }
        this.village = village;
    }

    public void setPlayer(IHTPlayer p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        player = p;
    }

    public IHTPlayer getPlayer() {
        return player;
    }

    @Override
    public void doAction() {
        super.doAction();

        if (city == null) {
            throw new IllegalStateException("a city must have been set");
        }

        if (village == null) {
            throw new IllegalStateException("a village must have been set");
        }

        IHTPlayer p = getPlayer();

        if (!p.getEscritoire().getBonusMarker().contains(this)) {
            throw new IllegalStateException("The current player differs of the marker's owner");
        }

        for (ICity city : village.getRoute().getCities()) {
            city.getOwner().increaseScore();
        }
        IKontor<Pawn> k = new Kontor(village.getPawnType(), false, Privillegium.White);
        kontor = k;
        Pawn pa = village.pullPawn();
        //pawn use to remember which pawn is used ( for undo )
        pawn = pa;
        k.pushPawn(pa);
        city.pushAdditionalKontors(k);
        List<Pawn> ps = Lists.newArrayList();
        for (IVillage otherVillage : village.getRoute().getVillages()) {
            if (!otherVillage.equals(village)) {
                ps.add(otherVillage.pullPawn());
            }
        }
        pawns = ps;
        player.getEscritoire().getStock().addPawns(ps);
        player.setActionNumber(-1);

    }

    public void undoAction() {
        super.undoAction();

        if (city == null) {
            throw new IllegalStateException("a city must have been set");
        }

        if (village == null) {
            throw new IllegalStateException("a village must have been set");
        }

        IHTPlayer p = getPlayer();

        if (!p.getEscritoire().getBonusMarker().contains(this)) {
            throw new IllegalStateException("The current player differs of the marker's owner");
        }
        village.pushPawn(kontor.popPawn());
        city.getAdditionalKontors().remove(kontor);
        player.getEscritoire().getStock().removePawns(pawns);

        Iterator<Pawn> pawnIterator = pawns.iterator();

        for (IVillage otherVillage : village.getRoute().getVillages()) {
            if (!otherVillage.equals(village)) {
                otherVillage.pushPawn(pawnIterator.next());
            }
        }

        pawns.clear();
        for (ICity cities : village.getRoute().getCities()) {
            cities.getOwner().decreaseScore();
        }
        player.setActionNumber(1);
    }

    public ICity getCity() {
        return city;
    }

    public IVillage getVillage() {
        return village;
    }

    public Pawn getPawn() {
        return pawn;
    }

    public IKontor getKontor() {
        return kontor;
    }

    public List<Pawn> getPawns() {
        return Lists.newArrayList(pawns);
    }
}
