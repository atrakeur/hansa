package fr.univ_rouen.hansa.gameboard.bonusmarkers;

/**
 * This interface represents the Pattern Visitor for the interface IBonusMarker
 */
public interface IVisitorBonusMarker {
    /**
     * If the object visited is the default one
     * You would probably want to throw an UnsupportedOperationException to this
     * @param marker the marker to visit
     */
    void visit(IBonusMarker marker);
    void visit(BonusActiones marker);
    void visit(BonusEscritoire marker);
    void visit(BonusKontor marker);
    void visit(BonusPermutation marker);
    void visit(BonusRemovePawns marker);
}
