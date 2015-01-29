package fr.univ_rouen.hansa.gameboard.cities;

import java.util.List;

public interface ICity {

    public IKontor getKontor(int i);
    public List<IKontor> getKontors();
    public Power getPower();
    public boolean isCompletedCity();

}
