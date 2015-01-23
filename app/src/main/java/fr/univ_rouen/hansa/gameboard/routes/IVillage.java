package fr.univ_rouen.hansa.gameboard.routes;

import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;

public interface IVillage {

    public boolean isEmpty();
    public void setPawn(Pawn pawn);
    public Pawn pushPawn();

}
