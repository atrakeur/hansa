package fr.univ_rouen.hansa.gameboard.cities;

import java.util.List;

public interface ICity {

    IKontor getKontor(int i);
    List<IKontor> getKontors();
    boolean isCompletedCity();

}
