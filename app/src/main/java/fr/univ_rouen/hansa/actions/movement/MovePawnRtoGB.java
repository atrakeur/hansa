package fr.univ_rouen.hansa.actions.movement;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.exceptions.NoPlaceException;
import fr.univ_rouen.hansa.exceptions.NotAvailableActionException;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IPawnList;
import fr.univ_rouen.hansa.gameboard.player.escritoire.PawnList;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.gameboard.routes.Village;

public class MovePawnRtoGB implements IMovement {
    private final IHTPlayer player;
    private final IVillage village;
    private final Class<? extends Pawn> type;

    private boolean replaced;
    private int traderToReplace = 0;
    private int merchantToReplace = 0;
    private boolean actionDone;

    private Pawn pawnToReplace;

    public MovePawnRtoGB(IHTPlayer player, IVillage village, Class<? extends Pawn> type) {
        this.player = player;
        this.village = village;
        this.type = type;

        actionDone = false;
        replaced = false;
    }

    @Override
    public boolean isDone() {
        return actionDone;
    }

    @Override
    public Actions getActionDone() {
        return Actions.movePawnRtoGB;
    }

    @Override
    public void doMovement() {
        if (actionDone) {
            throw new IllegalStateException();
        }

        /**
         * Commentaire explicatif. Let's go
         */
        // si le village est pas ville le mouvement est un remplacement de pion
        if (!village.isEmpty()) {
            // On recupere le pion dans le village
            Pawn p = village.pullPawn();
            // Si le pion est un trader
            if (p.getClass() == Trader.class) {
                // On verifie les ressources
                if (player.getEscritoire().getSupply().enoughPawns(0, 2)) {
                    player.getEscritoire().moveSupplyToStock(0, 2);
                    traderToReplace = 2;
                    merchantToReplace = 0;
                } else if (player.getEscritoire().getSupply().enoughPawns(1, 1)) {
                    player.getEscritoire().moveSupplyToStock(1, 1);
                    traderToReplace = 1;
                    merchantToReplace = 1;
                } else if (player.getEscritoire().getSupply().enoughPawns(2, 0)) {
                    player.getEscritoire().moveSupplyToStock(2, 0);
                    traderToReplace = 0;
                    merchantToReplace = 2;
                } else {
                    throw new NotAvailableActionException();
                }
            } else {
                // On verifie que le joueur a 3 ressources au moins
                if (player.getEscritoire().getSupply().enoughPawns(0, 3)) {
                    player.getEscritoire().moveSupplyToStock(0, 3);
                    traderToReplace = 3;
                    merchantToReplace = 0;
                } else if (player.getEscritoire().getSupply().enoughPawns(1, 2)) {
                    player.getEscritoire().moveSupplyToStock(1, 2);
                    traderToReplace = 2;
                    merchantToReplace = 1;
                } else if (player.getEscritoire().getSupply().enoughPawns(2, 1)) {
                    player.getEscritoire().moveSupplyToStock(2, 1);
                    traderToReplace = 1;
                    merchantToReplace = 2;
                } else  if(player.getEscritoire().getSupply().enoughPawns(3,0)) {
                    player.getEscritoire().moveSupplyToStock(3, 0);
                    traderToReplace = 0;
                    merchantToReplace = 3;
                } else {
                    throw new NotAvailableActionException();
                }

            }
            pawnToReplace = p;
            replaced = true;

            /**
             * Une fois les ressources payé on place le pion
             */
            Pawn pawn;

            if (type.equals(Trader.class)) {
                pawn = player.getEscritoire().popFromSupply(0, 1).get(0);
            } else {
                pawn = player.getEscritoire().popFromSupply(1, 0).get(0);
            }

            village.pushPawn(pawn);

            actionDone = true;
            // Ici on as fait la partie remplacement. Il faut donc que le jeu rende la main a l'autre joueur.
            // Celui qui possède pawnToReplace
            // Voir les autres comment on peux faire ça. Si c'est la bonne solution
        } else {

            Pawn pawn;

            if (type.equals(Trader.class)) {
                pawn = player.getEscritoire().popFromSupply(0, 1).get(0);
            } else {
                pawn = player.getEscritoire().popFromSupply(1, 0).get(0);
            }

            village.pushPawn(pawn);

            actionDone = true;

        }


    }

    @Override
    public void doRollback() {
        if (!actionDone) {
            throw new IllegalStateException();
        }

        if (village.isEmpty()) {
            throw new NoPlaceException();
        }

        player.getEscritoire().addToSupply(Lists.newArrayList(village.pullPawn()));

        if (replaced) {
            player.getEscritoire().moveStockToSupply(merchantToReplace, traderToReplace);
            replaced = false;
        }

        actionDone = false;
    }

    @Override
    public Pawn getPawnToReplace() {
        return pawnToReplace;
    }

    // Todo : Ask what is that
    @Override
    public int getMergeableMove() {
        return 0;
    }

    /**
     * replace pawns from pawns into villages in v
     * i.e. v(0) <- pawns(0)
     * @pre v must be "adjacent villages" from villages replaced
     * @pre pawns.size() == v.size()
     * @param pawns
     * @param v
     */
    public void replacePawn(List<Pawn> pawns,List<Village> v ) {
        if (pawns == null || v == null){
            throw new NullPointerException();
        }
        if(pawns.size() != v.size()){
            throw new IllegalArgumentException();
        }
        /**
         *

        if (isAdajacent(village, v)){

        }

        //int trad = t.size();
        for (int i = 0; i < v.size(); i++) {
            if (trad != 0) {
                v.get(i).pushPawn(t.get(i));
                trad--;
            } else {
                v.get(i).pushPawn(m.get(i));
            }
        }
         */
    }
    private boolean isAdajacent(IVillage v1, List<IVillage> villages){
        //TODO
        return true;
    }
}
