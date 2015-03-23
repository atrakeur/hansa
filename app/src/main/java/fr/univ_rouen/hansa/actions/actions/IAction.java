package fr.univ_rouen.hansa.actions.actions;

import java.util.List;

import fr.univ_rouen.hansa.actions.movement.IMovement;

public interface IAction {

    public List<IMovement> getMovements();
}
