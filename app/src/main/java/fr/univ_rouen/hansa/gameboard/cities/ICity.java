package fr.univ_rouen.hansa.gameboard.cities;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.pawns.Pawn;

public interface ICity {

    public IKontor getKontor(int i);
    public List<IKontor<? extends Pawn>> getKontors();

    /**
     * Additional Kontors created by Bonus BonusKontor.
     * @return Additional Kontors List
     */
    public List<IKontor<? extends Pawn>> getAdditionalKontors();

    /**
     * push an additional kontor
     * @param kontor : kontor != null && !kontor.isEmpty()
     */
    public void pushAdditionalKontors(IKontor<? extends Pawn> kontor);
    public Power getPower();
    public boolean isCompletedCity();

}
