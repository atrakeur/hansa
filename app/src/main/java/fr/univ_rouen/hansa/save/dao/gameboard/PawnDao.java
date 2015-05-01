package fr.univ_rouen.hansa.save.dao.gameboard;

import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.save.dao.Dao;

public class PawnDao implements Dao<Pawn> {

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

    @Override
    public Pawn daoToEntity() {
        IHTPlayer player = null;

        for (IHTPlayer ihtPlayer : TurnManager.getInstance().getPlayers()) {
            if (ihtPlayer.getPlayerColor() == playerColor) {
                player = ihtPlayer;
                break;
            }
        }

        if (player == null) {
            throw new GameException("Error during loading : a player is missing");
        }

        if (pawnType == PawnType.Trader) {
            return new Trader(player);
        } else {
            return new Merchant(player);
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
