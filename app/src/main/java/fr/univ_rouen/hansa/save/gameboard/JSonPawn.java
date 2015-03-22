package fr.univ_rouen.hansa.save.gameboard;

import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;

public class JSonPawn {

    private PlayerColor playerColor;
    private PawnType pawnType;

    public JSonPawn(Pawn pawn) {
        this.playerColor = pawn.getPlayer().getPlayerColor();

        if (pawn.getClass().equals(Trader.class)) {
            this.pawnType = PawnType.Trader;
        } else {
            this.pawnType = PawnType.Merchant;
        }
    }
}
