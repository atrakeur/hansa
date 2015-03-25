package fr.univ_rouen.hansa.gameboard.cities;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.view.IPosition;

public class Coellen extends City {


    List<VictoryCoellen> victories;

    public Coellen(IPosition position, Power power, List<IKontor<? extends Pawn>> kontors) {
        super(position, power, kontors);
        for (Privillegium myprivi : Privillegium.values()) {
            victories.add(new VictoryCoellen(myprivi));
        }
    }
}
