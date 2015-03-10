package fr.univ_rouen.hansa.gameboard.bonusmarkers;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.PlayerColor;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class BonusRemovePawns extends StatedBonus implements IBonusMarker {

    // La liste des villages concernés
    private List<IVillage> villages;
    // la liste des joueurs concernés
    private List<IHTPlayer> players;
    // La liste des pions concernés
    private List<Pawn> pawn;

    public BonusRemovePawns() {
        super();
    }

    public List<IHTPlayer> getPlayers() {
        return Lists.newArrayList(players);
    }

    public List<IVillage> getVillage() {
        return Lists.newArrayList(villages);
    }

    public List<Pawn> getPawn() {
        return Lists.newArrayList(pawn);
    }

    public void setVillage(List<IVillage> v) {
        if (v == null || v.size() > 3) {
            throw new IllegalArgumentException();
        }
        villages = v;

    }

    public void setPlayers(List<IHTPlayer> p) {
        if (p == null || p.size() > 3) {
            throw new IllegalArgumentException();
        }
        players = p;
    }

    public void setPawn(List<Pawn> p) {
        if (p == null || p.size() > 3) {
            throw new IllegalArgumentException();
        }
        pawn = p;
    }


    @Override
    public void doAction() {
        super.doAction();
        List<IVillage> v = getVillage();
        List<IHTPlayer> player = Lists.newArrayList();
        if (v == null) {
            throw new IllegalStateException("village not set");
        }
        List<Pawn> p = Lists.newArrayList();
        for (int i = 0; i < v.size(); i++) {
            p.add(v.get(i).pullPawn());
        }
        for (Pawn pawn : p) {
            player.add(pawn.getPlayer());
            pawn.getPlayer().getEscritoire().addToSupply(Lists.newArrayList(pawn));
        }
        setPlayers(player);
        setPawn(p);
    }

    @Override
    public void undoAction() {
        super.undoAction();
        List<IVillage> v = getVillage();
        if (v == null) {
            throw new IllegalStateException();
        }
        /**
         * On recupere la liste des pions a retirer du stock des joueurs
         */
        List<Pawn> p = getPawn();
        List<IHTPlayer> player = getPlayers();
        for (int i = 0; i < p.size(); i++) {
            player.get(i).getEscritoire().removeFromSupply(Lists.newArrayList(p.get(i)));
            v.get(i).pushPawn(p.get(i));
        }
    }
}
