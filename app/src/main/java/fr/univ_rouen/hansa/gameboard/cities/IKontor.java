package fr.univ_rouen.hansa.gameboard.cities;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;

public interface IKontor<E extends Pawn> {

    boolean isEmpty();
    Class<E> getPawnClass();
    void pushPawn(E pawn);
    E popPawn();
    IHTPlayer getOwner();
    Privillegium getPrivillegium();
    boolean hasVictoryPoint();

}
