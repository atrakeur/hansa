package fr.univ_rouen.hansa.gameboard.bonusmarkers;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.Kontor;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;


/**
 * Bonus which allow you to add a Kontor on the left of a City
 */
public class BonusKontor extends StatedBonus implements IBonusMarker {
    private ICity city;
    private Pawn pawn;

    public BonusKontor() {
        super();
    }

    /**
     * initialise the city which will have a bonus Kontor
     */
    public void setCity(ICity city) {
        if (city == null) {
            throw new IllegalArgumentException();
        }
        if (city.getKontors().isEmpty()) {
            throw new IllegalStateException("City has to have at least one Kontor filed to use " +
                    "a BonusKontor on it");
        }
        this.city = city;
    }

    /**
     * initialise the pawn which will be in the Bonus Kontor
     */
    public void setPawn(Pawn pawn) {
        if (pawn == null) {
            throw new IllegalArgumentException();
        }
        if (!pawn.getPlayer().getEscritoire().getBonusMarker().contains(this)) {
            throw new IllegalStateException("The pawn's player differs of the marker's owner");
        }
        this.pawn = pawn;
    }

    @Override
    public void doAction() {
        super.doAction();

        if (city == null) {
            throw new IllegalStateException("a city must have been set");
        }

        if (pawn == null) {
            throw new IllegalStateException("a pawn must have been set");
        }

        IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();

        if (!player.getEscritoire().getBonusMarker().contains(this)) {
            throw new IllegalStateException("The current player differs of the marker's owner");
        }

        IKontor<Pawn> k = new Kontor(pawn.getClass(), false, Privillegium.White);
        //TODO enlever le pion de la reserve du joueur ? (cf undo)
        k.pushPawn(pawn);
        city.pushAdditionalKontors(k);
    }

    public void undoAction() {
        super.undoAction();

        if (city == null) {
            throw new IllegalStateException("a city must have been set");
        }

        if (pawn == null) {
            throw new IllegalStateException("a pawn must have been set");
        }

        IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();

        if (!player.getEscritoire().getBonusMarker().contains(this)) {
            throw new IllegalStateException("The current player differs of the marker's owner");
        }

        //TODO remettre le pion dans la reserve du joueur ? (cf do)
        city.getAdditionalKontors().remove(city.getAdditionalKontors().size() - 1);
    }
}
