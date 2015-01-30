package fr.univ_rouen.hansa.gameboard.cities;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.pawns.Pawn;

public interface ICity {

    public IKontor getKontor(int i);
    public List<IKontor<? extends Pawn>> getKontors();
    public Power getPower();
    public boolean isCompletedCity();

}
