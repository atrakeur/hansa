package fr.univ_rouen.hansa.gameboard.cities;

import java.util.List;

public class City implements ICity {

    private List<IKontor> kontors;
    private Power power;

    public City(Power pow, List<IKontor> ks) {
        if (ks == null) {
            throw new IllegalArgumentException();
        }
        power = pow;
        kontors = ks;
    }

    public IKontor getKontor(int i) {
        if (i < 0 || i > kontors.size()) {
            throw new IllegalArgumentException();
        }

        return kontors.get(i);
    }

    public List<IKontor> getKontors() {
        return kontors;
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
