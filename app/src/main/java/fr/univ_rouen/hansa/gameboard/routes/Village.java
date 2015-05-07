package fr.univ_rouen.hansa.gameboard.routes;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.view.IPosition;
import fr.univ_rouen.hansa.view.interactions.HansaVillageClickableArea;
import fr.univ_rouen.hansa.view.interactions.IClickableArea;

public class Village implements IVillage {

    private final IPosition position;
    private Pawn pawn;
    private IRoute route;

    private IClickableArea clickableArea;

    public Village(IPosition position) {
        this.position = position;

        this.clickableArea = new HansaVillageClickableArea(this);
    }

    @Override
    public IRoute getRoute() {
        if (route == null) {
            throw new IllegalStateException();
        }

        return route;
    }

    @Override
    public void setRoute(IRoute route) {
        if (this.route != null) {
            throw new IllegalStateException();
        }

        this.route = route;
    }

    @Override
    public boolean isEmpty() {
        return pawn == null;
    }

    @Override
    public IPosition getPosition() {
        return position;
    }

    @Override
    public IHTPlayer getOwner() {
        if (pawn != null) {
            return pawn.getPlayer();
        }

        return null;
    }

    @Override
    public Class<? extends Pawn> getPawnType() {
        if (pawn == null) {
            return null;
        }

        return pawn.getClass();
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

    @Override
    public IClickableArea getClickableArea() {
        return this.clickableArea;
    }

    @Override
    public List<IVillage> getNeighbours(IVillage village){
        List<IVillage> neighbours = Lists.newArrayList();
        IRoute route = village.getRoute();
        int villagePosition = route.getVillages().indexOf(village);
        if(villagePosition == 0) {
            neighbours.add(route.getVillage(1));
        } else if (villagePosition == route.getVillages().size()) {
            neighbours.add(route.getVillage(route.getVillages().size() - 1));
        }
    /*Action*/;
        return null;
    }
}
