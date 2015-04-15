package fr.univ_rouen.hansa.gameboard.save;


import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.board.GameBoard;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;

/*
* la classe POJO
 */
public class GameBoardSave {
    @Expose
    private List<IHTPlayer> players;
    @Expose
    private int currentPlayer;
    @Expose
    private int background;
    @Expose
    private Map<Pawn, int[]> pawnsCities;
    @Expose
    private Map<Pawn, int[]> pawnsRoutes;
    @Expose
    private Map<Pawn, int[]> pawnsKontorsSup;
    @Expose
    private Map<IBonusMarker, Integer> bonusMarkerRoutes;

    public GameBoardSave(GameBoard board) {

        this.setBackground(board.getBackground());
        this.setPlayers(TurnManager.getInstance().getPlayers());
        IHTPlayer currentPlayer = TurnManager.getInstance().getCurrentPlayer();
        this.setCurrentPlayer(TurnManager.getInstance().getPlayers().indexOf(currentPlayer));
        this.setBackground(board.getBackground());
        this.setPawnsCities(board.getCities());
        this.setPawnsRoutes(board.getRoutes());
        this.setbonusMarkerRoutes(board.getRoutes());

    }

    /*
    * la sauvegarde de player: coleur et escritoire, score
     */
    public void setPlayers(List<IHTPlayer> ps) {
        players = ps;
    }

    public List<IHTPlayer> getPlayers() {
        return players;
    }

    public void setCurrentPlayer(int player) {
        currentPlayer = player;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * la sauvegarde de  background pour savoir : le plateau 1 ou 2
     */
    public void setBackground(int bk) {
        background = bk;
    }

    public int getBackground() {
        return background;
    }

    /**
     * la sauvegarde de pawn ansi sa position dans quel kontor de quelle ville
     */
    public void setPawnsCities(List<ICity> cities) {
        pawnsCities = new HashMap<>();
        for (int i = 0; i < cities.size(); i++) {

            for (int j = 0; j < cities.get(i).getKontors().size(); j++) {
                if (!cities.get(i).getKontors().get(j).isEmpty()) {
                    Pawn p = cities.get(i).getKontors().get(j).popPawn();
                    int player = TurnManager.getInstance().getPlayers().indexOf(p.getPlayer());
                    /*
                    *i: index of city, j:index of kontor, player:index of player.
                     */
                    int[] index = new int[]{i, j, player};
                    pawnsCities.put(p, index);
                }
            }
        }

    }

    public Map<Pawn, int[]> getPawnsCities() {
        return pawnsCities;
    }

    /**
     * la sauvegarde de pawn ansi sa position dans quel village de quelle route
     */
    public void setPawnsRoutes(List<IRoute> routes) {
        pawnsRoutes = new HashMap<>();
        for (int i = 0; i < routes.size(); i++) {
            for (int j = 0; j < routes.get(i).getVillages().size(); j++) {
                if (!routes.get(i).getVillages().get(j).isEmpty()) {
                    Pawn p = routes.get(i).getVillages().get(j).pullPawn();
                    int player = TurnManager.getInstance().getPlayers().indexOf(p.getPlayer());
                    int[] index = new int[]{i, j, player};
                    pawnsCities.put(p, index);
                }
            }
        }

    }

    public Map<Pawn, int[]> getPawnsRoutes() {
        return pawnsRoutes;
    }

    /**
     * la sauvegarde de bonusMarker de la route ansi sa position dans quelle route
     */
    public void setbonusMarkerRoutes(List<IRoute> routes) {
        bonusMarkerRoutes = new HashMap<>();
        for (int i = 0; i < routes.size(); i++) {

            if (routes.get(i).getBonusMarker() != null) {
                bonusMarkerRoutes.put(routes.get(i).getBonusMarker(), i);
            }
        }

    }

    public Map<IBonusMarker, Integer> getbonusMarkerRoutes() {
        return bonusMarkerRoutes;
    }
}

