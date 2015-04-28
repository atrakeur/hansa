package fr.univ_rouen.hansa.gameboard.bonusmarkers;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;

/**
 * Bonus which allow you to permute two Kontor in a City
 */
public class BonusPermutation extends AbstractBonus implements IBonusMarker {

    private IKontor<Pawn> k1;
    private IKontor<Pawn> k2;
    private ICity city;

    public BonusPermutation() {
        super(BonusType.BonusPermutation);
    }

    @Override
    public void doAction() {
        super.doAction();
        if (k1 == null || k2 == null) {
            throw new NullPointerException();
        }
        if (k1.isEmpty() || k2.isEmpty()) {
            throw new IllegalStateException("A kontor is empty");
        }
        ICity c = getCity();
        List<IKontor<? extends Pawn>> k = c.getKontors();
        Pawn p1 = null;
        Pawn p2 = null;
        //TODO check if k1 & k2 are in the same city
        for (int i = 0; i < k.size(); i++) {
            if (k.get(i).equals(k1)) {
                p1 = k.get(i).popPawn();
            } else if (k.get(i).equals(k2)) {
                p2 = k.get(i).popPawn();
            }
        }
        if (p1 == null || p2 == null) {
            throw new IllegalStateException();
        }
        k1.pushPawn(p2);
        k2.pushPawn(p1);
    }

    @Override
    public void undoAction() {
        super.undoAction();
        if (k1 == null || k2 == null) {
            throw new NullPointerException();
        }
        if (k1.isEmpty() || k2.isEmpty()) {
            throw new IllegalStateException("A kontor is empty");
        }
        ICity c = getCity();
        List<IKontor<? extends Pawn>> k = c.getKontors();
        Pawn p1 = null;
        Pawn p2 = null;
        //TODO check if k1 & k2 are in the same city
        for (int i = 0; i < k.size(); i++) {
            if (k.get(i).equals(k1)) {
                p1 = k.get(i).popPawn();
            } else if (k.get(i).equals(k2)) {
                p2 = k.get(i).popPawn();
            }
        }
        if (p1 == null || p2 == null) {
            throw new IllegalStateException();
        }
        k1.pushPawn(p2);
        k2.pushPawn(p1);
    }

    public void setKontor1(IKontor<Pawn> k) {
        if (k == null) {
            throw new NullPointerException();
        }
        k1 = k;
    }

    public void setKontor2(IKontor<Pawn> k) {
        if (k == null) {
            throw new NullPointerException();
        }
        k2 = k;
    }

    public void setCity(ICity c) {
        if (c == null) {
            throw new IllegalArgumentException();
        }
        city = c;
    }

    public IKontor getKontor1() {
        return k1;
    }

    public IKontor getKontor2() {
        return k2;
    }

    public ICity getCity() {
        return city;
    }
}
