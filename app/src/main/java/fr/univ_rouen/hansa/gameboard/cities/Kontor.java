package fr.univ_rouen.hansa.gameboard.cities;


import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.pawns.Pawn;

public class Kontor<E extends Pawn> implements IKontor<E>{
    //attributs
    private E pawn;
    private Privillegium privillegium;

    //contructeur
    public Kontor(Privillegium p){
        pawn=null;
        privillegium = p;
    }

    //les méthodes
    public boolean isEmpty() {
        if(pawn==null) return true;
        else return false;
    }



    public E pushPawn() {
        E p = pawn ;
        pawn=null;
        return p;
    }


    public Privillegium getPrivillegium() {
        return privillegium;
    }

    //commandes
    public void pullPawn(E p) {
        if( p==null) throw new IllegalArgumentException();
        if(!this.isEmpty()) throw new  IllegalArgumentException();
        pawn = p;
    }

}
