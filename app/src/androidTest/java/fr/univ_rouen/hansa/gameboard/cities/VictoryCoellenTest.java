package fr.univ_rouen.hansa.gameboard.cities;

import junit.framework.TestCase;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.player.HTComputer;
import fr.univ_rouen.hansa.gameboard.player.HTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;

public class VictoryCoellenTest extends TestCase {

    public void testSetMerchantAndGetPawn() throws Exception {
        //getPawn return null on empty
        assertEquals(null,VictoryCoellen.getInstance().getPawn(Privillegium.White));

        //getPawn return the previously setted pawn if not empty
        Merchant m = new Merchant(new HTPlayer(PlayerColor.purple,1));
        VictoryCoellen.getInstance().setMerchant(m,Privillegium.White);
        assertEquals(m,VictoryCoellen.getInstance().getPawn(Privillegium.White));
    }
}
