package fr.univ_rouen.hansa.GameBoard.Cities;

import java.util.List;

public interface ICity {

    IKontor getKontor(int i);
    List<IKontor> getKontors();
    boolean isCompletedCity();

}
