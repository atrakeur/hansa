package fr.univ_rouen.hansa.gameboard.player.bonusmarkers;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusActiones;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusEscritoire;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusKontor;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusState;
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
import fr.univ_rouen.hansa.view.IPosition;
import fr.univ_rouen.hansa.view.Position;

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
        bn3.setState(BonusState.onHand);
        IHTPlayer player = new HTPlayer(PlayerColor.blue, 1);
        player.getEscritoire().addBonusMarker(bn3);
        bn3.setPlayer(player);
        bn3.doAction();
        assertEquals(player.getActionNumber(), 5);
        bn3.undoAction();
        assertEquals(player.getActionNumber(), 2);
        /**
         * Meme travail sur un jeton de valeur 4.
         */
        BonusActiones bn4 = new BonusActiones(4);
        player.getEscritoire().addBonusMarker(bn4);
        bn4.setPlayer(player);
        bn4.setState(BonusState.onHand);
        bn4.doAction();
        assertEquals(player.getActionNumber(), 6);
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
        es.setPlayer(player);
        es.setState(BonusState.onHand);
        player.getEscritoire().addBonusMarker(es);
        es.doAction();
        assertEquals(player.getEscritoire().actionesLevel(), 3);
        es.undoAction();
        assertEquals(player.getEscritoire().actionesLevel(), 2);
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
        assertEquals(player.getEscritoire().privilegiumLevel(), Privillegium.Orange);
        es.undoAction();
        assertEquals(player.getEscritoire().privilegiumLevel(), Privillegium.White);
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

    public void testBonusKontor() throws Exception {
        /**
         * Creation des variables utiles pour les test
         */
        BonusKontor bk = new BonusKontor();
        IHTPlayer player = new HTPlayer(PlayerColor.blue, 1);
        IHTPlayer player2 = new HTPlayer(PlayerColor.green,2);
        Pawn p = new Trader(player);
        //public static IPosition STADE = new Position(0.533f, 0.094f);
        List<IKontor<? extends Pawn>> kontors = Lists.newArrayList();
        Pawn c = new Merchant(player2);
        Kontor k = new Kontor(c.getClass(), true, Privillegium.White);
        kontors.add(k);
        ICity city = new City(new Position(0.533f, 0.094f),Power.Privillegium, kontors);
        /**
         * Test l'effet du jeton bonus sur la liste des comptoirs additionel.
         * Test aussi lorsque l'effet est lancé plusieurs fois
         */
        bk.setState(BonusState.onHand);
        bk.setCity(city);
        player.getEscritoire().addBonusMarker(bk);
        bk.setPawn(p);
        bk.setPlayer(player);
        bk.doAction();
        assertEquals(city.getAdditionalKontors().size(), 1);
        bk.setState(BonusState.onHand);
        bk.doAction();
        assertEquals(city.getAdditionalKontors().size(),2);
        bk.setState(BonusState.onHand);
        bk.doAction();
        assertEquals(city.getAdditionalKontors().size(),3);
        bk.setState(BonusState.onHand);
        bk.doAction();
        assertEquals(city.getAdditionalKontors().size(),4);
        bk.undoAction();
        assertEquals(city.getAdditionalKontors().size(), 3);
    }
    public void testBonusPermutation() throws Exception {

    }
}
