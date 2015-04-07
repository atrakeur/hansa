package fr.univ_rouen.hansa.actions.movement;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.exceptions.NotAvailableActionException;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class IncreasePower implements IMovement {
    private final IHTPlayer player;
    private final Power power;
    private final IRoute route;
    private final List<Pawn> pawns;

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

        pawns = Lists.newArrayList();

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
        if (pawns.size() > 0 || actionDone) {
            throw new IllegalStateException("Movement already done");
        }

        if (!route.isTradeRoute(player)) {
            throw new NotAvailableActionException();
        }

        for (IVillage village : route.getVillages()) {
            pawns.add(village.pullPawn());
        }

        player.getEscritoire().addToStock(pawns);

        player.getEscritoire().increasePower(power);
        for(ICity city : route.getCities()){
            if (city.getOwner() != null) {
                city.getOwner().increaseScore();
            }
        }
        actionDone = true;
    }

    @Override
    public void doRollback() {
        if (!route.isEmpty()) {
            throw new NotAvailableActionException();
        }

        try {
            player.getEscritoire().removeFromStock(pawns);

            for (int i = 0; i < pawns.size(); i++) {
                route.getVillage(i).pushPawn(pawns.get(i));
            }

            pawns.clear();

            player.getEscritoire().decreasePower(power);
        } catch (Exception e) {
            throw new NotAvailableActionException();
        }
        for(ICity city : route.getCities()){
            if (city.getOwner() != null) {
                city.getOwner().decreaseScore();
            }
        }

        actionDone = false;
    }

    @Override
    public int getPawnReplaceMove() {
        return 0;
    }

    @Override
    public int getMergeableMove() {
        return 0;
    }
}
