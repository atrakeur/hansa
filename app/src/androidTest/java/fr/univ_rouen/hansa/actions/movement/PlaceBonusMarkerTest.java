package fr.univ_rouen.hansa.actions.movement;

import com.google.common.collect.Lists;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusActiones;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusState;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.City;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.gameboard.routes.Route;
import fr.univ_rouen.hansa.gameboard.routes.Village;

public class PlaceBonusMarkerTest extends TestCase {

    public void test_not_empty_route_throws_exception() throws Exception{
        //GIVEN
        List<IVillage> vils = Lists.newArrayList();
        Village v = new Village(null);
        vils.add(v);
        City[] c = new City[2];
        c[0] = new City(null, null, new ArrayList<IKontor<? extends Pawn>>());
        c[1] = new City(null, null, new ArrayList<IKontor<? extends Pawn>>());

        IRoute route = new Route(vils, c, null);

        IBonusMarker b = new BonusActiones(3);
        b.setState(BonusState.inPlate);

        //WHEN
        try{
            IMovement mov = new PlaceBonusMarker(b, route);
            v.pushPawn(new Merchant(null));
            mov.doMovement();
            fail();
        } catch (IllegalStateException ex){
            //THEN
            assertEquals(ex.getMessage(), "Route not empty");

        }

    }


}