package fr.univ_rouen.hansa.gameboard.player.bonusmarkers;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusActiones;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusEscritoire;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusKontor;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusPermutation;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusRemovePawns;
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
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.gameboard.routes.Route;
import fr.univ_rouen.hansa.gameboard.routes.Village;
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
         * Test du jeton bonus RemovePawns
         */
        BonusRemovePawns brp = new BonusRemovePawns();
        IHTPlayer player = new HTPlayer(PlayerColor.green, 1);
        IHTPlayer p2 = new HTPlayer(PlayerColor.blue, 2);
        IHTPlayer p3 = new HTPlayer(PlayerColor.purple, 3);
        IHTPlayer p4 = new HTPlayer(PlayerColor.red, 4);
        IVillage v1 = new Village(new Position(0.115f, 0.221f));
        IVillage v2 = new Village(new Position(0.224f, 0.31f));
        IVillage v3 = new Village(new Position(0.668f, 0.075f));
        Pawn m1 = new Merchant(p2);
        Pawn m2 = new Merchant(p3);
        Pawn t1 = new Trader(p4);
        v1.pushPawn(m1);
        v2.pushPawn(m2);
        v3.pushPawn(t1);
        List<IVillage> v = Lists.newArrayList();
        v.add(v1);
        v.add(v2);
        v.add(v3);
        brp.setVillage(v);
        player.getEscritoire().addBonusMarker(brp);
        brp.setState(BonusState.onHand);
        brp.doAction();
        assertEquals(brp.getVillage().isEmpty(), false);
        assertEquals(brp.getPlayers().isEmpty(), false);
        assertEquals(brp.getPlayers().size(), 3);
        assertEquals(brp.getPawn().size(), 3);
        assertEquals(brp.getPawn().isEmpty(), false);
        brp.undoAction();
    }

    public void testBonusKontor() throws Exception {
        /**
         * Creation des variables utiles pour les test
         */
        BonusKontor bk = new BonusKontor();
        IHTPlayer player1 = new HTPlayer(PlayerColor.blue, 1);
        IHTPlayer player2 = new HTPlayer(PlayerColor.green,2);



        List<IKontor<? extends Pawn>> kontors1 = Lists.newArrayList();
        Kontor k1 = new Kontor<Trader>(Trader.class, false, Privillegium.White);
        k1.pushPawn(player1.getEscritoire().popFromSupply(0,1).get(0));
        kontors1.add(k1);
        ICity city1 = new City(new Position(0.533f, 0.094f),Power.Privillegium, kontors1);

        List<IKontor<? extends Pawn>> kontors2 = Lists.newArrayList();
        Kontor k2 = new Kontor(Trader.class, false, Privillegium.White);
        k2.pushPawn(player2.getEscritoire().popFromSupply(0,1).get(0));
        kontors2.add(k2);
        ICity city2 = new City(new Position(0.533f, 0.094f),Power.Privillegium, kontors2);

        ICity[] cities = new ICity[2];
        cities[0] = city1;
        cities[1] = city2;

        List<IVillage> villages = new ArrayList<IVillage>();
        Village v1 = new Village(new Position(0.533f, 0.094f));
        v1.pushPawn(player1.getEscritoire().popFromSupply(0,1).get(0));
        Village v2 = new Village(new Position(0.533f, 0.095f));
        v2.pushPawn(player1.getEscritoire().popFromSupply(0,1).get(0));
        Village v3 = new Village(new Position(0.533f, 0.096f));
        v3.pushPawn(player1.getEscritoire().popFromSupply(0,1).get(0));
        villages.add(v1);
        villages.add(v2);
        villages.add(v3);
        IRoute r = new Route(villages, cities, new Position(0.533f, 0.097f));

        /*
          ici : city1 a un kontor pris par player1 resp pour city2 player2 et la route reliant
          city1 a city 2 est occupé par player1
         */
        assertTrue(r.isTradeRoute(player1));

        player1.getEscritoire().addBonusMarker(bk);
        bk.setPlayer(player1);
        bk.setCity(city1);
        bk.setVillage(v1);
        bk.setState(BonusState.onHand);

        //test du kontor additionnel
        assertTrue(city1.getAdditionalKontors().isEmpty());

        //test des points
        assertEquals(0,player1.getScore());
        assertEquals(0,player2.getScore());

        //test du nombre d'action
        assertEquals(2,player1.getActionNumber());

        bk.doAction();

        //test du nombre d'action
        assertEquals(1,player1.getActionNumber());

        //test du kontor additionnel
        assertFalse(city1.getAdditionalKontors().isEmpty());
        assertEquals(city1.getAdditionalKontors().get(0).getOwner(),player1);

        //test des points
        assertEquals(1,player1.getScore());
        assertEquals(1,player2.getScore());


        bk.undoAction();


        //test du kontor additionnel
        assertTrue(city1.getAdditionalKontors().isEmpty());

        //test des points
        assertEquals(0,player1.getScore());
        assertEquals(0,player2.getScore());

        //test du nombre d'action
        assertEquals(2,player1.getActionNumber());
    }


    /*public void testBonusPermutation() throws Exception {
        BonusPermutation bp = new BonusPermutation();
        IHTPlayer player = new HTPlayer(PlayerColor.blue, 1);
        IHTPlayer player2 = new HTPlayer(PlayerColor.green,2);
        Pawn p = new Trader(player);
        Pawn c = new Merchant(player2);
        List<IKontor<? extends Pawn>> kontors = Lists.newArrayList();
        Kontor k = new Kontor(c.getClass(), true, Privillegium.White);
        Kontor k2 = new Kontor(p.getClass(), true, Privillegium.White);
        k.pushPawn(c);
        k2.pushPawn(p);
        kontors.add(k);
        kontors.add(k2);
        ICity city = new City(new Position(0.906f, 0.916f), null, kontors);
        bp.setCity(city);
        bp.setKontor1(k);
        bp.setKontor2(k2);
        player.getEscritoire().addBonusMarker(bp);
        bp.setState(BonusState.onHand);
        assertEquals(bp.getKontor1().isEmpty(), false);
        assertEquals(bp.getKontor2().isEmpty(), false);
        assertEquals(bp.getKontor1().getOwner(), player2);
        assertEquals(bp.getKontor2().getOwner(), player);
        bp.doAction();
        assertEquals(bp.getKontor1().isEmpty(), false);
        assertEquals(bp.getKontor2().isEmpty(), false);
        assertEquals(bp.getKontor1().getOwner(), player);
        assertEquals(bp.getKontor2().getOwner(), player2);
        bp.undoAction();
        assertEquals(bp.getKontor1().isEmpty(), false);
        assertEquals(bp.getKontor2().isEmpty(), false);
        assertEquals(bp.getKontor1().getOwner(), player2);
        assertEquals(bp.getKontor2().getOwner(), player);

    }*/
}
