package fr.univ_rouen.hansa.gameboard.cities;


import java.util.ArrayList;
import java.util.List;

public class City  implements ICity{

    //les attributs
    private  List<IKontor> kontors;
    private Power power;


    // constructeur
     public City(Power pow, List ks){
         if(ks==null) {
             throw new IllegalArgumentException();
         }
         power =pow;
         kontors =ks;
     }

    // les méthodes
    public IKontor getKontor(int i) {
        if( i<-1 || i>kontors.size()) throw new  IllegalArgumentException();
        return kontors.get(i);
    }


    public List<IKontor> getKontors() {
        return kontors;
    }


    public boolean isCompletedCity() {
        for(IKontor k : kontors ){
            if(k.isEmpty()) return false;
        }
        return true;

    }
}
