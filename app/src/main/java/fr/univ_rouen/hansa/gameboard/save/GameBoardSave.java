package fr.univ_rouen.hansa.gameboard.save;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class GameBoardSave {
    @Expose
    private int background;
    @Expose
    private List<ICity> cities;

    @Expose
    private List<IRoute> routes;

    @Expose
    private TurnManager manager;

    @Expose
    private Map<IHTPlayer, List<Pawn>> pawns;

    public GameBoardSave(GameBoard board) {
        if (board == null) {
            throw new IllegalArgumentException();
        }
        background = board.getBackground();
        routes = board.getRoutes();
        cities = board.getCities();
        manager = TurnManager.getInstance();
        pawns = new HashMap<>();

        for (ICity c : board.getCities()) {
            for (IKontor k : c.getKontors()) {
                if (!k.isEmpty()) {
                    Pawn p = k.popPawn();
                    k.pushPawn(p);
                    IHTPlayer player = p.getPlayer();
                    if (!pawns.keySet().contains(player)) {
                        pawns.put(player, new ArrayList<Pawn>());
                        pawns.get(player).add(p);
                    } else {
                        pawns.get(player).add(p);
                    }
                }
            }

        }

        for (IRoute r : board.getRoutes()) {
            for (IVillage v : r.getVillages()) {
                if (!v.isEmpty()) {
                    Pawn p = v.pullPawn();
                    v.pushPawn(p);
                    IHTPlayer player = p.getPlayer();
                    if (!pawns.keySet().contains(player)) {
                        pawns.put(player, new ArrayList<Pawn>());
                        pawns.get(player).add(p);
                    } else {
                        pawns.get(player).add(p);
                    }
                }
            }

        }


    }

    public int getBackground() {
        return background;
    }

    public Map<IHTPlayer, List<Pawn>> getPawns() {
        return pawns;
    }

    public List<ICity> getCities() {
        return cities;
    }

    public void setCities(List<ICity> cts) {
        cities = cts;
    }

    public List<IRoute> getRoutes() {
        return routes;
    }

    public void setRoutes(List<IRoute> rs) {
        routes = rs;
    }

    public TurnManager getManager() {
        return manager;
    }

    public void setManager(TurnManager m) {
        manager = m;
    }

    public void SetBackground(int b) {
        background = b;
    }

}
