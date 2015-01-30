package fr.univ_rouen.hansa.gameboard.cities;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.pawns.Pawn;

public class City implements ICity {

    private final Power power;
    private final List<IKontor<? extends Pawn>> kontors;

    public City(Power power, List<IKontor<? extends Pawn>> kontors) {
        if (kontors == null) {
            throw new IllegalArgumentException();
        }

        this.power = power;
        this.kontors = kontors;
    }

    public IKontor getKontor(int i) {
        if (i < 0 || i > kontors.size()) {
            throw new IllegalArgumentException();
        }

        return kontors.get(i);
    }

    public List<IKontor<? extends Pawn>> getKontors() {
        return kontors;
    }

    public Power getPower() {
        return power;
    }

    public boolean isCompletedCity() {
        for (IKontor k : kontors) {
            if (k.isEmpty()) {
                return false;
            }

        }

        return true;
    }
}
