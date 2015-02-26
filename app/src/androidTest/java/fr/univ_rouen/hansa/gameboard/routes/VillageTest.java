package fr.univ_rouen.hansa.gameboard.routes;

import junit.framework.TestCase;

import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.util.VillagePositions;


public class VillageTest extends TestCase {
    private IHTPlayer player = new HTPlayer(PlayerColor.purple, 3);
    private IVillage village;

    public void testIsEmpty() throws Exception {
        village = new Village(VillagePositions.ARNHEIM_DUISBURG_1);
        assertNotNull(village);
        Pawn p = new Merchant(player);
        assertTrue("village is empty", village.isEmpty());
        village.pushPawn(p);
        assertFalse("village is full", village.isEmpty());
        village.pullPawn();
        assertTrue("village is empty after pull", village.isEmpty());

    }

    public void testGetPosition() throws Exception {
        village = new Village(VillagePositions.ARNHEIM_MUNSTER_1);
        assertNotNull(village);
        assertTrue(VillagePositions.ARNHEIM_MUNSTER_1 == village.getPosition());
        assertFalse(VillagePositions.ARNHEIM_DUISBURG_3 == village.getPosition());

    }


    public void testGetOwner() throws Exception {
        village = new Village(VillagePositions.ARNHEIM_MUNSTER_1);
        assertNotNull(village);
        Trader p = new Trader(player);
        village.pushPawn(p);
        assertNotNull(village.pullPawn().getPlayer());
    }

    public void testPushPawn() throws Exception {
        village = new Village(VillagePositions.BREMEN_HAMBURG_4);
        Trader p = new Trader(player);
        village.pushPawn(p);
        assertNotNull(village.pullPawn());
        village.pushPawn(p);
        assertTrue(village.pullPawn() == p);
        village.pushPawn(p);
        assertNotNull(village.pullPawn());
    }


    public void testPushPawn2() throws Exception {
        village = new Village(VillagePositions.ARNHEIM_DUISBURG_1);
        assertNotNull(village);
        Merchant p = new Merchant(player);
        Merchant p1 = new Merchant(player);
        village.pushPawn(p);
        try {
            village.pushPawn(p1);
            fail("Invalid Affectation");

        } catch (IllegalStateException e) {
        }
    }

    public void testPullPawn() throws Exception {
        village = new Village(VillagePositions.BREMEN_HAMBURG_2);
        assertNotNull(village);
        Pawn p = new Merchant(player);
        village.pushPawn(p);
        assertNotNull(village.pullPawn());
        village.pushPawn(p);
        assertTrue(village.pullPawn() == p);
    }

    public void testPullNull() throws Exception {
        village = new Village(VillagePositions.BREMEN_HAMBURG_3);
        assertNotNull(village);
        try {
            village.pullPawn();
            fail("Invalid Affectation");
        } catch (IllegalStateException e) {
        }

    }
}
