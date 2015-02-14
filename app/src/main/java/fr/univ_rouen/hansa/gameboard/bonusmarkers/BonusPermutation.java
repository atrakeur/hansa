package fr.univ_rouen.hansa.gameboard.bonusmarkers;


import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.pawns.Pawn;

/**
 * Bonus which allow you to permute two Kontor in a City
 */
public class BonusPermutation extends StatedBonus implements IBonusMarker {

    private IKontor<Pawn> k1;
    private IKontor<Pawn> k2;

    public BonusPermutation() {
        super();
    }

    @Override
    public void doAction() {
        super.doAction();
        if (k1 == null || k2 == null){
            throw new NullPointerException();
        }
        if (k1.isEmpty() || k2.isEmpty()) {
            throw new IllegalStateException("A kontor is empty");
        }
        //TODO check if k1 & k2 are in the same city
        Pawn p1 = k1.pushPawn();
        Pawn p2 = k2.pushPawn();
        k1.pullPawn(p2);
        k2.pullPawn(p1);
    }
    @Override
    public void undoAction() {
        super.undoAction();
        if (k1 == null || k2 == null){
            throw new NullPointerException();
        }
        if (k1.isEmpty() || k2.isEmpty()) {
            throw new IllegalStateException("A kontor is empty");
        }
        //TODO check if k1 & k2 are in the same city
        Pawn p1 = k1.pushPawn();
        Pawn p2 = k2.pushPawn();
        k1.pullPawn(p2);
        k2.pullPawn(p1);
    }

    public void setKontor1(IKontor<Pawn> k){
        if (k == null){
            throw new NullPointerException();
        }
        k1 = k;
    }
    public void setKontor2(IKontor<Pawn> k){
        if (k == null){
            throw new NullPointerException();
        }
        k2 = k;
    }
}
