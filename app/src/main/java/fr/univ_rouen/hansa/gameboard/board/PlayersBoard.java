package fr.univ_rouen.hansa.gameboard.board;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;

public class PlayersBoard extends RouteBoard {

    private List<IHTPlayer> players;
    private List<Pawn> pawns;

    public PlayersBoard()
    {
        players = Lists.newArrayList();
        pawns   = Lists.newArrayList();
    }

}
