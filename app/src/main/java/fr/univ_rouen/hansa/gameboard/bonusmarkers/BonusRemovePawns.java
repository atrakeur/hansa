package fr.univ_rouen.hansa.gameboard.bonusmarkers;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;

public class BonusRemovePawns extends StatedBonus implements IBonusMarker {

    private List<Pawn> pawn;

    public BonusRemovePawns() {
        super();
        pawn = Lists.newArrayList();
    }

    public List<Pawn> getPawn() {
        return pawn;
    }

    public void setPawn(List<Pawn> p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        pawn = p;
    }

    @Override
    public void doAction() {
        super.doAction();
        /**
         * On initialise la liste des pions par couleurs
         * et les joueurs par couleurs
         * En effet le jeton définis les pions a supprimer du plateau
         * De la on récupère le joueur affecté pour remetter les pions dans sa réserve
         */
        List<Pawn> blue = Lists.newArrayList();
        IHTPlayer pb = null;
        List<Pawn> red = Lists.newArrayList();
        IHTPlayer pr = null;
        List<Pawn> green = Lists.newArrayList();
        IHTPlayer pg = null;
        List<Pawn> yellow = Lists.newArrayList();
        IHTPlayer py = null;
        List<Pawn> purple = Lists.newArrayList();
        IHTPlayer pp = null;
        for(int i = 0; i < getPawn().size(); i++) {
            PlayerColor playerColor = getPawn().get(i).getPlayer().getPlayerColor();
            if (playerColor == PlayerColor.blue) {
                blue.add(getPawn().get(i));
                pb = getPawn().get(i).getPlayer();
            } else if (playerColor == PlayerColor.green) {
                green.add(getPawn().get(i));
                pg = getPawn().get(i).getPlayer();
            } else if (playerColor == PlayerColor.red) {
                red.add(getPawn().get(i));
                pr = getPawn().get(i).getPlayer();
            } else if (playerColor == PlayerColor.yellow) {
                yellow.add(getPawn().get(i));
                py = getPawn().get(i).getPlayer();
            } else {
                purple.add(getPawn().get(i));
                pp = getPawn().get(i).getPlayer();
            }
        }
        /**
         * On test les valeurs de player pour ne pas addToSupply une quantité null.
         * Cela évite les possibles IAE définis dans escritoire.
         */
        if (pb != null) {
            pb.getEscritoire().addToSupply(blue);
        }
        if (pr != null) {
            pr.getEscritoire().addToSupply(red);
        }
        if (py != null) {
            py.getEscritoire().addToSupply(yellow);
        }
        if (pp != null) {
            pp.getEscritoire().addToSupply(purple);
        }

        if (pg != null) {
            pg.getEscritoire().addToSupply(green);
        }
    }

    @Override
    public void undoAction() {
        super.undoAction();

        /**
         * On initialise la liste des pions par couleurs
         * et les joueurs par couleurs
         * En effet le jeton définis les pions a supprimer du plateau
         * De la on récupère le joueur affecté pour remetter les pions dans sa réserve
         */
        List<Pawn> blue = Lists.newArrayList();
        IHTPlayer pb = null;
        List<Pawn> red = Lists.newArrayList();
        IHTPlayer pr = null;
        List<Pawn> green = Lists.newArrayList();
        IHTPlayer pg = null;
        List<Pawn> yellow = Lists.newArrayList();
        IHTPlayer py = null;
        List<Pawn> purple = Lists.newArrayList();
        IHTPlayer pp = null;
        for(int i = 0; i < getPawn().size(); i++) {
            PlayerColor playerColor = getPawn().get(i).getPlayer().getPlayerColor();
            if (playerColor == PlayerColor.blue) {
                blue.add(getPawn().get(i));
                pb = getPawn().get(i).getPlayer();
            } else if (playerColor == PlayerColor.green) {
                green.add(getPawn().get(i));
                pg = getPawn().get(i).getPlayer();
            } else if (playerColor == PlayerColor.red) {
                red.add(getPawn().get(i));
                pr = getPawn().get(i).getPlayer();
            } else if (playerColor == PlayerColor.yellow) {
                yellow.add(getPawn().get(i));
                py = getPawn().get(i).getPlayer();
            } else {
                purple.add(getPawn().get(i));
                pp = getPawn().get(i).getPlayer();
            }
        }
        /**
         * On test les valeurs de player pour ne pas addToSupply une quantité null.
         * Cela évite les possibles IAE définis dans escritoire.
         */
        if (pb != null) {
            pb.getEscritoire().removeFromSupply(blue);
        }
        if (pr != null) {
            pr.getEscritoire().removeFromSupply(red);
        }
        if (py != null) {
            py.getEscritoire().removeFromSupply(yellow);
        }
        if (pp != null) {
            pp.getEscritoire().removeFromSupply(purple);
        }

        if (pg != null) {
            pg.getEscritoire().removeFromSupply(green);
        }

    }
}
