package fr.univ_rouen.hansa.actions.movement;

import junit.framework.TestCase;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusState;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;

public class PlayBonusTest extends TestCase {

    class BonusMarker implements IBonusMarker {
        private BonusState state = BonusState.onHand;

        @Override
        public BonusState getState() {
            return state;
        }

        @Override
        public void setState(BonusState state) {
            this.state = state;
        }

        @Override
        public void doAction() {
            setState(BonusState.used);
        }

        @Override
        public void undoAction() {
            setState(BonusState.onHand);
        }
    }

    public void testConstructorWithValidParameter() throws Exception {
        PlayBonus action = new PlayBonus(new BonusMarker());
        assertFalse(action.isDone());
        assertEquals(action.getActionDone(), Actions.playBonus);
    }


    public void testConstructorWithInvalidBonusMarkerState() throws Exception {
        IBonusMarker bonus = new BonusMarker();
        bonus.setState(BonusState.inPlate);
        try {
            new PlayBonus(bonus);
            fail("Constructor should have raised an IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertEquals(ex.getMessage(), "The bonus argument is null or its state is not onHand");
        }
    }


    public void testDoValidMovementAndRollback() throws Exception {
        PlayBonus action = new PlayBonus(new BonusMarker());
        action.doMovement();
        assertTrue(action.isDone());

        action.doRollback();
        assertFalse(action.isDone());
    }

    public void testInvalidInitialBonusMarkerState() throws Exception {
        IBonusMarker bonus = new BonusMarker();
        PlayBonus action = new PlayBonus(bonus);
        bonus.setState(BonusState.inPlate);
        try {
            action.doMovement();
            fail("doMovement() should have raised an IllegalStateException");
        } catch (IllegalStateException ex) {
            assertEquals(ex.getMessage(), "Action already done or invalid bonus state");
        }
    }

    public void testInvalidRollbackBonusMarkerState() throws Exception {
        IBonusMarker bonus = new BonusMarker();
        PlayBonus action = new PlayBonus(bonus);
        action.doMovement();
        bonus.setState(BonusState.inPlate);

        try {
            action.doRollback();
            fail("doRollback() should have raised an IllegalStateException");
        } catch (IllegalStateException ex) {
            assertEquals(ex.getMessage(), "Action already undone or invalid bonus state");
        }
    }

    public void testInvalidEarlyRollback() throws Exception {
        PlayBonus action = new PlayBonus(new BonusMarker());
        try {
            action.doRollback();
            fail("doRollback() should have raised an IllegalStateException");
        } catch (IllegalStateException ex) {
            assertEquals(ex.getMessage(), "Action already undone or invalid bonus state");
        }
    }

    public void testInvalidDoMovementTwiceInARow() throws Exception {
        PlayBonus action = new PlayBonus(new BonusMarker());
        action.doMovement();
        try {
            action.doMovement();
            fail("doMovement() should have raised an IllegalStateException");
        } catch (IllegalStateException ex) {
            assertEquals(ex.getMessage(), "Action already done or invalid bonus state");
        }
    }
}