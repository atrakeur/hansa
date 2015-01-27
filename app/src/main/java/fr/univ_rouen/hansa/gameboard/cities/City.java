package fr.univ_rouen.hansa.gameboard.cities;

import java.util.List;

public class City implements ICity {

    private final int id;
    private final Power power;
    private final List<IKontor> kontors;

    public City(int id, Power power, List<IKontor> kontors) {
        if (kontors == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.power = power;
        this.kontors = kontors;
    }

    public int getId() {
        return id;
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
