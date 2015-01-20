package fr.univ_rouen.hansa.GameBoard.Routes;

import java.util.List;

import fr.univ_rouen.hansa.GameBoard.BonusMarkers.IBonusMarker;
import fr.univ_rouen.hansa.GameBoard.Cities.ICity;
import fr.univ_rouen.hansa.GameBoard.Pawns.Pawn;

public interface IRoute {

    public IVillage getVillage(int i);
    public List<ICity> getCities();
    public boolean isTradeRoute();
    public IBonusMarker getBonusMarker();
    public boolean isYourCity(ICity city);
    public List<Pawn> getPawns();

}
