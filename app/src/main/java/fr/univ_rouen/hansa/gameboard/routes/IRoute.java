package fr.univ_rouen.hansa.gameboard.routes;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.pawns.Pawn;

public interface IRoute {

    public IVillage getVillage(int i);
    public List<IVillage> getVillages();
    public List<ICity> getCities();
    public boolean isTradeRoute();
    public IBonusMarker getBonusMarker();
    public boolean isYourCity(ICity city);
    public List<Pawn> getPawns();

}
