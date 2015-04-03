package fr.univ_rouen.hansa.actions;

import fr.univ_rouen.hansa.actions.actions.ActionFactory;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;

public class MovementManager {

    private static MovementManager instance = new MovementManager();

    private final Stack<IMovement> stack;
    private ActionFactory actionFactory;

    private MovementManager() {
        stack = new Stack<IMovement>();

        actionFactory = new ActionFactory();
    }

    public static MovementManager getInstance() {
        return instance;
    }

    public void doMove(IMovement m) {
        if (m.isDone()) {
            throw new IllegalStateException("Can't do the same Movement twice");
        }

        m.doMovement();
        stack.push(m);
    }

    public IMovement rollbackMove() {
        IMovement m = stack.pop();
        if (!m.isDone()) {
            stack.push(m);
            throw new IllegalStateException("Can't rollback the same Movement twice");
        }

        m.doRollback();
        return m;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void nextTurn() {
        stack.clear();
        actionFactory = new ActionFactory();
    }

    public int actionCounter() {
        return actionFactory.getActions(stack.getStackContent()).size();
    }

    public Pawn getPawnToReplace() {
        return this.actionFactory.getPawnToReplace(stack.getStackContent());
    }

    public boolean hasPawnToReplace() {
        return this.actionFactory.hasPawnToReplace(stack.getStackContent());
    }

}
