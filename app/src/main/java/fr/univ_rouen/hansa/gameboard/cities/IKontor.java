package fr.univ_rouen.hansa.gameboard.cities;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;

public interface IKontor<E extends Pawn> {

    public boolean isEmpty();
    public Class<E> getPawnClass();
    public void pushPawn(E pawn);
    public E popPawn();
    public IHTPlayer getOwner();
    public Privillegium getPrivillegium();
    public boolean hasVictoryPoint();

}
