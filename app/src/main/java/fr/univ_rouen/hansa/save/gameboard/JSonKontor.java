package fr.univ_rouen.hansa.save.gameboard;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;

public class JSonKontor {

    private final PawnType pawnType;
    private final boolean victoryPoint;
    private JSonPawn pawn;
    private Privillegium privillegium;

    public JSonKontor(IKontor<Pawn> kontor) {
        if (kontor.getPawnClass().equals(Trader.class)) {
            this.pawnType = PawnType.Trader;
        } else {
            this.pawnType = PawnType.Merchant;
        }

        this.victoryPoint = false; //TODO utiliser la méthode de Steeven
        this.privillegium = kontor.getPrivillegium();

        Pawn kontorPawn = kontor.popPawn();

        if (kontorPawn != null) {
            this.pawn = new JSonPawn(kontorPawn);
            kontor.pushPawn(kontorPawn);
        } else {
            this.pawn = null;
        }
    }
}
