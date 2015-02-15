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

    public void doBursaMove(){
            IMovement m = new Bursa(TurnManager.getInstance().getCurrentPlayer());
            m.doMovement();
            stack.push(m);
    }

    public void doBursaMove(int merchant){
        IMovement m = new Bursa(TurnManager.getInstance().getCurrentPlayer(), merchant);
        m.doMovement();
        stack.push(m);
    }

    public void rollbackMove(){
        stack.pop().doRollback();
    }

    public boolean isEmpty(){return stack.isEmpty();}

}
