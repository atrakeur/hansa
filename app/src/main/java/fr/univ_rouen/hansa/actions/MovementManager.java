package fr.univ_rouen.hansa.actions;

import fr.univ_rouen.hansa.actions.movement.Bursa;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.gameboard.TurnManager;

/**
 * Created by Valentin on 11/02/2015.
 */
public class MovementManager {

    private Stack<IMovement> stack;
    private static MovementManager instance= new MovementManager();

    private MovementManager(){stack = new Stack<>();}

    public static MovementManager getInstance(){return instance;}

    public void doMove(IMovement m) {
        if (m.isDone()) {
            throw new IllegalStateException("Can't do the same Movement twice");
        }
        m.doMovement();
        stack.push(m);
    }

    public IMovement rollbackMove(){
        IMovement m = stack.pop();
        if (!m.isDone()) {
            stack.push(m);
            throw new IllegalStateException("Can't rollback the same Movement twice");
        }

        m.doRollback();
        return m;
    }

    public boolean isEmpty(){return stack.isEmpty();}

}
