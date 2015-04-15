package fr.univ_rouen.hansa.actions.actions;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.actions.movement.IMovement;

public class Action implements IAction {

    private final Actions type;
    private final List<IMovement> movements;

    public Action(Actions type, List<IMovement> movements) {
        this.type = type;
        this.movements = Lists.newArrayList(movements);
    }

    public List<IMovement> getMovements() {
        return Collections.unmodifiableList(this.movements);
    }
}
