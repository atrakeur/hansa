package fr.univ_rouen.hansa.gameboard.routes;

import com.google.common.collect.Lists;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.cities.City;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.Kontor;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.util.CityPositions;
import fr.univ_rouen.hansa.util.TavernPositions;
import fr.univ_rouen.hansa.util.VillagePositions;
import fr.univ_rouen.hansa.view.IPosition;


public class VillageTest extends TestCase {
    private IHTPlayer player = new HTPlayer(PlayerColor.purple, 3);
    private IVillage village;

    public void testIsEmpty() throws Exception {
        village = new Village(VillagePositions.ARNHEIM_DUISBURG_1);
        Pawn p = new Merchant(player);
        assertTrue("village is empty", village.isEmpty());
        village.pushPawn(p);
        assertFalse("village is full", village.isEmpty());
        village.pullPawn();
        assertTrue("village is empty after pull", village.isEmpty());

    }

    public void testGetPosition() throws Exception {
        village = new Village(VillagePositions.ARNHEIM_MUNSTER_1);
        assertTrue(VillagePositions.ARNHEIM_MUNSTER_1 == village.getPosition());

    }


    public void testGetOwner() throws Exception {
        village = new Village(VillagePositions.ARNHEIM_MUNSTER_1);
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
        Pawn p = new Merchant(player);
        village.pushPawn(p);
        assertNotNull(village.pullPawn());
        village.pushPawn(p);
        assertTrue(village.pullPawn() == p);
    }

    public void testPullNull() throws Exception {
        village = new Village(VillagePositions.BREMEN_HAMBURG_3);
        try {
            village.pullPawn();
            fail("Invalid Affectation");
        } catch (IllegalStateException e) {
        }

    }

    public void testGetAdjacent(){
        GameBoard gameBoard = GameBoardFactory.getInstance().createGameBoard(2);
        ICity city = gameBoard.getCities().get(0);

        IVillage village = city.getRoutes().get(0).getVillage(0);
        assertEquals(1, village.getAdjacentsVillages().size());
        assertEquals(village, village.getAdjacentsVillages().get(0));

        //village == village devant GRONINGEN
        village.pushPawn(new Merchant(player));

        System.out.println("city pos : " + city.getPosition().getX() + " - " + city.getPosition().getY());
        System.out.println("village pos : " + village.getPosition().getX() + " - " + village.getPosition().getY());
        for(IVillage v : village.getAdjacentsVillages()) {
            System.out.println(v.getPosition().getX() + " - " + v.getPosition().getY());
        }

        assertEquals(1, village.getAdjacentsVillages().size());
        assertEquals(VillagePositions.GRONINGEN_EMDEN_2, village.getAdjacentsVillages().get(0).getPosition());

        System.out.println("//////////////////////////////////");

        //village == village a gauche de Emden ( direction groningen )
        village = city.getRoutes().get(0).getVillage(2);
        village.pushPawn(new Merchant(player));

        assertEquals(3, village.getAdjacentsVillages().size());
        assertEquals(VillagePositions.GRONINGEN_EMDEN_2, village.getAdjacentsVillages().get(0).getPosition());
        assertEquals(VillagePositions.EMDEN_OSNABRUCK_1, village.getAdjacentsVillages().get(1).getPosition());
        assertEquals(VillagePositions.EMDEN_STADE_1, village.getAdjacentsVillages().get(2).getPosition());




    }

}
