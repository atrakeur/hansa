package fr.univ_rouen.hansa.gameboard.player.bonusmarkers;

import android.app.Application;
import android.test.ApplicationTestCase;

import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusActiones;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusEscritoire;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class BonusMarkersTest extends ApplicationTestCase<Application> {

    public BonusMarkersTest() {
        super(Application.class);
    }

    public void testBonusActiones() throws Exception {
        /**
         * Methode de test du jeton bonus Actiones
         * Creation d'un jeton d'une valeur de 3 puis test avec un joueur particulier
         * Test du résultat de la méthode doAction et de la méthode unDoAction
         */
        BonusActiones bn3 = new BonusActiones(3);
        IHTPlayer player = new HTPlayer(PlayerColor.blue, 1);
        player.getEscritoire().addBonusMarker(bn3);
        bn3.doAction();
        assertEquals(player.getActionNumber(), 4);
        bn3.undoAction();
        assertEquals(player.getActionNumber(), 2);
        /**
         * Meme travail sur un jeton de valeur 4.
         */
        BonusActiones bn4 = new BonusActiones(4);
        player.getEscritoire().addBonusMarker(bn4);
        bn4.doAction();
        assertEquals(player.getActionNumber(), 5);
        bn4.undoAction();
        assertEquals(player.getActionNumber(), 2);
    }

    public void testBonusEscritoire() throws Exception {
        /**
         * Test du jeton bonus Escritoire.
         * Creation du jeton puis changement du pouvoir avec la methode setPower.
         * On test la methode doAction et unDoAction.
         * Puis on change le pouvoir et on recommence
         */
        BonusEscritoire es = new BonusEscritoire();
        es.setPower(Power.Actiones);
        IHTPlayer player = new HTPlayer(PlayerColor.blue, 1);
        es.doAction();
        assertEquals(player.getActionNumber(), 3);
        es.undoAction();
        assertEquals(player.getActionNumber(), 2);
        es.setPower(Power.Bursa);
        es.doAction();
        assertEquals(player.getEscritoire().bursaLevel(), 5);
        es.undoAction();
        assertEquals(player.getEscritoire().bursaLevel(), 3);
        es.setPower(Power.ClavisUrbis);
        es.doAction();
        assertEquals(player.getEscritoire().clavisUrbisLevel(), 2);
        es.undoAction();
        assertEquals(player.getEscritoire().clavisUrbisLevel(), 1);
        es.setPower(Power.LiberSophiae);
        es.doAction();
        assertEquals(player.getEscritoire().liberSophiaLevel(), 3);
        es.undoAction();
        assertEquals(player.getEscritoire().liberSophiaLevel(), 2);
        es.setPower(Power.Privillegium);
        es.doAction();
        assertEquals(player.getEscritoire().privilegiumLevel(), 2);
        es.undoAction();
        assertEquals(player.getEscritoire().privilegiumLevel(), 1);
    }

    public void testBonusRemovePawns() throws Exception {
        /**
         * A voir avec Steeven pour la façon de l'implementer
         IBonusMarker bonus = new BonusRemovePawns();
         List<Pawn> pawn;
         pawn = new Lists.newArrayList();
         IHTPlayer p1 = new HTPlayer(PlayerColor.blue, 1);
         for (int i = 0; i < 10; i++) {
         Pawn p = new Trader();
         pawn.add(p);
         }
         p1.getEscritoire().addToStock(pawn);
         */

    }
}
