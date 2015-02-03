package fr.univ_rouen.hansa.gameboard.cities;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;

public interface IKontor<E extends Pawn> {

    public boolean isEmpty();
    public void pullPawn(E pawn);
    public E pushPawn();
    public Privillegium getPrivillegium();

}
