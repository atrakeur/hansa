package fr.univ_rouen.hansa.actions.movement;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.exceptions.NotAvailableActionException;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class IncreasePower implements IMovement {
    private final IHTPlayer player;
    private final Power power;
    private final IRoute route;

    private boolean actionDone;

    public IncreasePower(IHTPlayer player, ICity city, IRoute route) {
        if (city == null || route == null) {
            throw new IllegalArgumentException();
        }

        if (city.getPower() == Power.Null) {
            throw new NotAvailableActionException("Need a power in the city for make this action");
        }

        this.player = player;
        this.power = city.getPower();
        this.route = route;

        this.actionDone = false;
    }

    @Override
    public boolean isDone() {
        return actionDone;
    }

    @Override
    public Actions getActionDone() {
        return Actions.increasePower;
    }

    @Override
    public void doMovement() {
        for (IVillage village : route.getVillages()) {
            if (village.getOwner() == null || player.equals(village.getOwner())) {
                throw new NotAvailableActionException();
            }
        }

        //TODO
    }

    @Override
    public void doRollback() {
        //TODO
    }
}
