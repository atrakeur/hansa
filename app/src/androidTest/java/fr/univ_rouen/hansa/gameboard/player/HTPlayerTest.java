package fr.univ_rouen.hansa.gameboard.player;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusActiones;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.cities.VictoryCoellen;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;

public class HTPlayerTest extends TestCase {

    public void testGetFinalScore() throws Exception {
        TurnManager manager = TurnManager.getInstance();

        GameBoard gameBoard = GameBoardFactory.getInstance().createGameBoard(1);
        List<ICity> cities = gameBoard.getCities();

        manager.addPlayers(Arrays.asList(PlayerColor.values()));
        List<IHTPlayer> players = manager.getPlayers();
        for (IHTPlayer player : players) {
            assertEquals(0, player.getFinalScore());
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
        assertEquals(4, playerTest.getFinalScore());

        playerTest.getEscritoire().increasePower(Power.Bursa);
        playerTest.getEscritoire().increasePower(Power.Bursa);
        playerTest.getEscritoire().increasePower(Power.Bursa);
        assertEquals(Integer.MAX_VALUE, playerTest.getEscritoire().bursaLevel());

        //actiones max score : 0 -> 4
        //Bursa max score : 4 -> 8
        assertEquals(8, playerTest.getFinalScore());

        playerTest.getEscritoire().increasePower(Power.LiberSophiae);
        playerTest.getEscritoire().increasePower(Power.LiberSophiae);
        playerTest.getEscritoire().increasePower(Power.LiberSophiae);
        assertEquals(5, playerTest.getEscritoire().liberSophiaLevel());

        //actiones max score : 0 -> 4
        //Bursa max score : 4 -> 8
        //Liber Sophia max score : 8 -> 12
        assertEquals(12, playerTest.getFinalScore());
        playerTest.setScore(0);

        playerTest.getEscritoire().increasePower(Power.Privillegium);
        playerTest.getEscritoire().increasePower(Power.Privillegium);
        playerTest.getEscritoire().increasePower(Power.Privillegium);
        assertEquals(Privillegium.Black, playerTest.getEscritoire().privilegiumLevel());

        //actiones max score : 0 -> 4
        //Bursa max score : 4 -> 8
        //Liber Sophia max score : 8 -> 12
        //Privilegium max score : 12 -> 16
        assertEquals(16, playerTest.getFinalScore());

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
        assertEquals(16, playerTest.getFinalScore());

        ////////////////////////////////
        //test points for bonusMarkers//
        ////////////////////////////////

        //0 bonusMarker = 0point + 16 from skills
        assertEquals(0 + 16, playerTest.getFinalScore());

        //1 bonusMarker = +1point + 16 from skills
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        assertEquals(1 + 16, playerTest.getFinalScore());

        //2 bonusMarker = +3point + 16 from skills
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        assertEquals(3 + 16, playerTest.getFinalScore());

        //4 bonusMarker = +6point + 16 from skills
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        assertEquals(6 + 16, playerTest.getFinalScore());

        //6 bonusMarker = +10point + 16 from skills
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        assertEquals(10 + 16, playerTest.getFinalScore());

        //8 bonusMarker = +15point + 16 from skills
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        assertEquals(15 + 16, playerTest.getFinalScore());

        //10 bonusMarker = +21point + 16 from skills
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        playerTest.getEscritoire().addBonusMarker(new BonusActiones(3));
        assertEquals(21 + 16, playerTest.getFinalScore());

        ////////////////////////
        //test network + owner//
        ////////////////////////

        ICity city1 = gameBoard.getCities().get(0);
        IKontor kontor = city1.getKontor(0);
        kontor.pushPawn(playerTest.getEscritoire().popFromSupply(0, 1).get(0));

        // 2point * 1 city controlled (player test is the owner
        // + 1 * clavis urbis 'cause highest network
        // + 21 points from bonusMarker
        // + 16 from skills

        assertEquals(2 * 1 + 1 * playerTest.getEscritoire().clavisUrbisLevel() + 21 + 16, playerTest.getFinalScore());

        ICity city2 = null;
        for (ICity nextCity : city1.getRoutes().get(0).getCities()) {
            if (nextCity != city1) {
                city2 = nextCity;
            }
        }

        city2.getKontor(0).pushPawn(playerTest.getEscritoire().popFromSupply(0, 1).get(0));
        city2.getKontor(1).pushPawn(players.get(1).getEscritoire().popFromSupply(0, 1).get(0));

        //here city2 next to city1 makes up network but playerTest does not own it

        assertEquals(2 * 1 + 2 * playerTest.getEscritoire().clavisUrbisLevel() + 21 + 16, playerTest.getFinalScore());

        ////////////////
        //test COELLEN//
        ////////////////

        VictoryCoellen.getInstance().setMerchant((Merchant) playerTest.getEscritoire().popFromSupply(1, 0).get(0), Privillegium.Pink);

        //add 11 from Black on Coellen
        assertEquals(9 + 2 * 1 + 2 * playerTest.getEscritoire().clavisUrbisLevel() + 21 + 16, playerTest.getFinalScore());

    }
}