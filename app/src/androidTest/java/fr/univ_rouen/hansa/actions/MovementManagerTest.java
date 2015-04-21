package fr.univ_rouen.hansa.actions;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusActiones;
import fr.univ_rouen.hansa.gameboard.cities.City;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class MovementManagerTest extends TestCase {

    public void testAddEndGamePoints() throws Exception {
        TurnManager manager = TurnManager.getInstance();
        assertNotNull(manager);
        GameBoard gameBoard = GameBoardFactory.getInstance().createGameBoard(1);
        List<ICity> cities = gameBoard.getCities();

        manager.addPlayers(Arrays.asList(PlayerColor.values()));
        List<IHTPlayer> players = manager.getPlayers();
        for(IHTPlayer player : players){
            assertEquals(0, player.getScore());
        }

        //test addEndGamePoints on players & map just initalize ( no points add )
        MovementManager.getInstance().addEndGamePoints();
        for(IHTPlayer player : players){
            assertEquals(0, player.getScore());
        }

        ///player use to calculate his score
        IHTPlayer playerTest = players.get(0);

        ////////////////////////
        //test +4 on max skill//
        ////////////////////////
        playerTest.getEscritoire().increasePower(Power.Actiones);
        playerTest.getEscritoire().increasePower(Power.Actiones);
        playerTest.getEscritoire().increasePower(Power.Actiones);
        playerTest.getEscritoire().increasePower(Power.Actiones);
        playerTest.getEscritoire().increasePower(Power.Actiones);
        assertEquals(5, playerTest.getEscritoire().actionesLevel());

        //actiones max score : 0 -> 4
        MovementManager.getInstance().addEndGamePoints();
        assertEquals(4, playerTest.getScore());
        playerTest.setScore(0);

        playerTest.getEscritoire().increasePower(Power.Bursa);
        playerTest.getEscritoire().increasePower(Power.Bursa);
        playerTest.getEscritoire().increasePower(Power.Bursa);
        assertEquals(Integer.MAX_VALUE, playerTest.getEscritoire().bursaLevel());

        //actiones max score : 0 -> 4
        //Bursa max score : 4 -> 8
        MovementManager.getInstance().addEndGamePoints();
        assertEquals(8, playerTest.getScore());
        playerTest.setScore(0);

        playerTest.getEscritoire().increasePower(Power.LiberSophiae);
        playerTest.getEscritoire().increasePower(Power.LiberSophiae);
        playerTest.getEscritoire().increasePower(Power.LiberSophiae);
        assertEquals(5, playerTest.getEscritoire().liberSophiaLevel());

        //actiones max score : 0 -> 4
        //Bursa max score : 4 -> 8
        //Liber Sophia max score : 8 -> 12
        MovementManager.getInstance().addEndGamePoints();
        assertEquals(12, playerTest.getScore());
        playerTest.setScore(0);

        playerTest.getEscritoire().increasePower(Power.Privillegium);
        playerTest.getEscritoire().increasePower(Power.Privillegium);
        playerTest.getEscritoire().increasePower(Power.Privillegium);
        assertEquals(Privillegium.Black, playerTest.getEscritoire().privilegiumLevel());

        //actiones max score : 0 -> 4
        //Bursa max score : 4 -> 8
        //Liber Sophia max score : 8 -> 12
        //Privilegium max score : 12 -> 16
        MovementManager.getInstance().addEndGamePoints();
        assertEquals(16, playerTest.getScore());
        playerTest.setScore(0);


        playerTest.getEscritoire().increasePower(Power.ClavisUrbis);
        playerTest.getEscritoire().increasePower(Power.ClavisUrbis);
        playerTest.getEscritoire().increasePower(Power.ClavisUrbis);
        playerTest.getEscritoire().increasePower(Power.ClavisUrbis);
        assertEquals(4, playerTest.getEscritoire().clavisUrbisLevel());

        //actiones max score : 0 -> 4
        //Bursa max score : 4 -> 8
        //Liber Sophia max score : 8 -> 12
        //Privilegium max score : 12 -> 16
        //clavis urbis max score : 16 -> 16 'cause no care of clavis ^^
        MovementManager.getInstance().addEndGamePoints();
        assertEquals(16, playerTest.getScore());
        playerTest.setScore(0);


        ////////////////////////////////
        //test points for bonusMarkers//
        ////////////////////////////////

        //0 bonusMarker = 0point + 16 from skills
        MovementManager.getInstance().addEndGamePoints();
        assertEquals(0 + 16, playerTest.getScore());
        playerTest.setScore(0);

        //1 bonusMarker = +1point + 16 from skills
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        MovementManager.getInstance().addEndGamePoints();
        assertEquals(1 + 16, playerTest.getScore());
        playerTest.setScore(0);

        //2 bonusMarker = +3point + 16 from skills
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        MovementManager.getInstance().addEndGamePoints();
        assertEquals(3 + 16, playerTest.getScore());
        playerTest.setScore(0);

        //4 bonusMarker = +6point + 16 from skills
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        MovementManager.getInstance().addEndGamePoints();
        assertEquals(6 + 16, playerTest.getScore());
        playerTest.setScore(0);

        //6 bonusMarker = +10point + 16 from skills
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        MovementManager.getInstance().addEndGamePoints();
        assertEquals(10 + 16, playerTest.getScore());
        playerTest.setScore(0);

        //8 bonusMarker = +15point + 16 from skills
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        MovementManager.getInstance().addEndGamePoints();
        assertEquals(15 + 16, playerTest.getScore());
        playerTest.setScore(0);

        //10 bonusMarker = +21point + 16 from skills
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        MovementManager.getInstance().addEndGamePoints();
        assertEquals(21 + 16, playerTest.getScore());
        playerTest.setScore(0);

        //TODO Test Coellen

        ////////////////////////
        //test network + owner//
        ////////////////////////

        ICity city1 = gameBoard.getCities().get(0);
        IKontor kontor = city1.getKontor(0);
        kontor.pushPawn(playerTest.getEscritoire().popFromSupply(0,1).get(0));

        // 2point * 1 city controlled (player test is the owner
        // + 1 * clavis urbis 'cause highest network
        // + 21 points from bonusMarker
        // + 16 from skills

        MovementManager.getInstance().addEndGamePoints();
        assertEquals(2 * 1 + 1 * playerTest.getEscritoire().clavisUrbisLevel() + 21 + 16 , playerTest.getScore());
        playerTest.setScore(0);

        ICity city2 = null;
        for(ICity nextCity : city1.getRoutes().get(0).getCities()){
            if (nextCity != city1){
                city2 = nextCity;
            }
        }

        city2.getKontor(0).pushPawn(playerTest.getEscritoire().popFromSupply(0,1).get(0));
        city2.getKontor(1).pushPawn(players.get(1).getEscritoire().popFromSupply(0,1).get(0));

        //here city2 next to city1 makes up network but playerTest does not own it

        MovementManager.getInstance().addEndGamePoints();
        assertEquals(2 * 1 + 2 * playerTest.getEscritoire().clavisUrbisLevel() + 21 + 16 , playerTest.getScore());
        playerTest.setScore(0);

    }
}