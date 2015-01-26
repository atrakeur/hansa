package fr.univ_rouen.hansa.gameboard.bonusmarkers;

public interface IBonusMarker {
    public BonusState getState();
    public void doAction();
}
