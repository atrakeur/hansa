package fr.univ_rouen.hansa.actions;

import junit.framework.TestCase;

import fr.univ_rouen.hansa.actions.movement.LiberSophia;
import fr.univ_rouen.hansa.gameboard.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.Village;

public class LiberSophiaTest extends TestCase {

    public void testMovement() throws Exception {
        Village source = new Village();
        Village destination = new Village();

        LiberSophia liberSophia = new LiberSophia(source, destination);

        source.pushPawn(new Trader());

        assertFalse(liberSophia.isDone());

        liberSophia.doMovement();

        assertTrue("After movement, source need to be empty", source.isEmpty());
        assertFalse("After movement, destination need to not be empty", destination.isEmpty());

        assertTrue(liberSophia.isDone());

        liberSophia.doRollback();

        assertFalse("After rollback, source need to not be empty", source.isEmpty());
        assertTrue("After rollback, destination need to be empty", destination.isEmpty());

        assertFalse(liberSophia.isDone());
    }

}