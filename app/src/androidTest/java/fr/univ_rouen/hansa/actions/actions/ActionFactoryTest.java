package fr.univ_rouen.hansa.actions.actions;

import com.google.common.collect.Lists;

import junit.framework.TestCase;

import java.util.List;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;

public class ActionFactoryTest extends TestCase {

    public void testActionsMergeCorrectly() {
        //Given
        List<IMovement> moves = Lists.newArrayList();
        moves.add(fakeMergeableMovement(null, 2));
        moves.add(fakeMergeableMovement(null, 2));
        ActionFactory factory = new ActionFactory();

        //When
        List<IAction> actions = factory.getActions(moves);

        //Then
        assertEquals(1, actions.size());
    }

    public void testActionsMergeConsecutiveFromCounter() {
        //Given
        List<IMovement> moves = Lists.newArrayList();
        moves.add(fakeMergeableMovement(null, 2));
        moves.add(fakeMergeableMovement(null, 2));
        moves.add(fakeMergeableMovement(null, 2));
        moves.add(fakeMergeableMovement(null, 2));
        ActionFactory factory = new ActionFactory();

        //When
        List<IAction> actions = factory.getActions(moves);

        //Then
        assertEquals(2, actions.size());
        for (IAction action: actions) {
            assertEquals(2, action.getMovements().size());
        }
    }

    public void testActionsMergeConsecutiveFromType() {
        //Given
        List<IMovement> moves = Lists.newArrayList();
        moves.add(fakeMergeableMovement(null, 4));
        moves.add(fakeMergeableMovement(null, 4));
        moves.add(fakeMergeableMovement(Actions.liberSophia, 4));
        moves.add(fakeMergeableMovement(Actions.liberSophia, 4));
        ActionFactory factory = new ActionFactory();

        //When
        List<IAction> actions = factory.getActions(moves);

        //Then
        assertEquals(2, actions.size());
        for (IAction action: actions) {
            assertEquals(2, action.getMovements().size());
        }
    }

    public void testActionsMergeNotConsecutiveFromCounter() {
        //Given
        List<IMovement> moves = Lists.newArrayList();
        moves.add(fakeMergeableMovement(null, 2));
        moves.add(fakeMergeableMovement(null, 2));
        moves.add(fakeMergeableMovement(null, 0));
        moves.add(fakeMergeableMovement(null, 0));
        moves.add(fakeMergeableMovement(null, 2));
        moves.add(fakeMergeableMovement(null, 2));
        ActionFactory factory = new ActionFactory();

        //When
        List<IAction> actions = factory.getActions(moves);

        //Then
        assertEquals(4, actions.size());
    }

    public void testActionsMergeNotConsecutiveFromType() {
        //Given
        List<IMovement> moves = Lists.newArrayList();
        moves.add(fakeMergeableMovement(null, 4));
        moves.add(fakeMergeableMovement(null, 4));
        moves.add(fakeMergeableMovement(Actions.liberSophia, 4));
        moves.add(fakeMergeableMovement(Actions.liberSophia, 4));
        moves.add(fakeMergeableMovement(null, 4));
        moves.add(fakeMergeableMovement(null, 4));
        ActionFactory factory = new ActionFactory();

        //When
        List<IAction> actions = factory.getActions(moves);

        //Then
        assertEquals(3, actions.size());
        for (IAction action: actions) {
            assertEquals(2, action.getMovements().size());
        }
    }

    public void testReplaceCountCorrectly() {
        //Given
        List<IMovement> moves = Lists.newArrayList();
        moves.add(fakeReplaceableMovement(null, 1));
        moves.add(fakeReplaceableMovement(null, 1));
        moves.add(fakeReplaceableMovement(null,-1));
        ActionFactory factory = new ActionFactory();

        //When
        int replaces = factory.getMaxReplaceMoves(moves);

        //Then
        assertEquals(1, replaces);
    }

    public IMovement fakeReplaceableMovement(Actions type, final int replaceCount) {
        return this.fakeMovement(type, replaceCount, 0);
    }

    public IMovement fakeMergeableMovement(Actions type, final int mergeableCount) {
        return this.fakeMovement(type, 0, mergeableCount);
    }

    public IMovement fakeMovement(final Actions type, final int replaceMove, final int mergeableCount) {
        return new IMovement() {
            public boolean isDone() {
                throw new UnsupportedOperationException();
            }
            public Actions getActionDone() {
                return type;
            }
            public void doMovement() {
                throw new UnsupportedOperationException();
            }
            public void doRollback() {
                throw new UnsupportedOperationException();
            }
            public Pawn getPawnToReplace() {
                return new Trader(new HTPlayer(PlayerColor.blue, 1));
            }
            public int getPawnReplaceMove() {
                return replaceMove;
            }
            public int getMergeableMove() {
                return mergeableCount;
            }
        };
    }

}
