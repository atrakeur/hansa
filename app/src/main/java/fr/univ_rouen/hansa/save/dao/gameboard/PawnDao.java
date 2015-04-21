package fr.univ_rouen.hansa.save.dao.gameboard;

import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;

public class PawnDao {

    private PlayerColor playerColor;
    private PawnType pawnType;

    public PawnDao() {
    }

    public PawnDao(Pawn pawn) {
        this.playerColor = pawn.getPlayer().getPlayerColor();

        if (pawn.getClass().equals(Trader.class)) {
            this.pawnType = PawnType.Trader;
        } else {
            this.pawnType = PawnType.Merchant;
        }
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public PawnType getPawnType() {
        return pawnType;
    }

    public void setPawnType(PawnType pawnType) {
        this.pawnType = pawnType;
    }
}
