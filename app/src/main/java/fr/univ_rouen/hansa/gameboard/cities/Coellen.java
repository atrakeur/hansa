package fr.univ_rouen.hansa.gameboard.cities;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.view.IPosition;

public class Coellen extends City {


    private final List<VictoryCoellen> victories;

    public Coellen(IPosition position, Power power, List<IKontor<? extends Pawn>> kontors) {
        super(position, power, kontors);
        victories = Lists.newArrayList();
        for (Privillegium myprivi : Privillegium.values()) {
            victories.add(new VictoryCoellen(myprivi));
        }
    }

    public List<VictoryCoellen> getVictories() {
        return victories;
    }

    public VictoryCoellen getVictoryCoellen(Privillegium privillegium){
        for (VictoryCoellen victory : victories){
            if(victory.getPrivillegiumRequis() == privillegium){
                return victory;
            }
        }
        return null;
    }
}
