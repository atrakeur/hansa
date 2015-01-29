package fr.univ_rouen.hansa.gameboard.routes;

import fr.univ_rouen.hansa.gameboard.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class Village implements IVillage {

    private Pawn pawn;
    private IRoute route;

    @Override
    public boolean isEmpty() {
        return pawn == null;
    }

    @Override
    public void setRoute(IRoute route) {
        if (route == null) {
            throw new IllegalArgumentException();
        }

        if (this.route != null) {
            throw new IllegalStateException("Route already initialised");
        }

        this.route = route;
    }

    @Override
    public IHTPlayer getOwner() {
        if (pawn != null) {
            return pawn.getPlayer();
        }

        return null;
    }

    @Override
    public void pushPawn(Pawn pawn) {
        if (!isEmpty()) {
            throw new IllegalStateException("Village already taken");
        }

        this.pawn = pawn;
    }

    @Override
    public Pawn pullPawn() {
        if (isEmpty()) {
            throw new IllegalStateException("Village has no pawn");
        }

        Pawn pawn = this.pawn;
        this.pawn = null;

        return pawn;
    }
}
