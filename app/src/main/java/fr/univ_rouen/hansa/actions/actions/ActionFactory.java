package fr.univ_rouen.hansa.actions.actions;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;

public class ActionFactory {

    private List<IMovement> movements;
    private List<IAction> actions;
    private Pawn pawnToReplace;

    private IAction createAction(Actions type, List<IMovement> movements) {
        return new Action(type, movements);
    }

    public List<IAction> getActions(List<IMovement> movements) {
        if (!movements.equals(this.movements)) {
            this.compileMovements(movements);
        }

        return actions;
    }

    public Pawn getPawnToReplace(List<IMovement> movements) {
        if (!movements.equals(this.movements)) {
            this.compileMovements(movements);
        }

        return pawnToReplace;
    }

    public boolean hasPawnToReplace(List<IMovement> movements) {
        if (!movements.equals(this.movements)) {
            this.compileMovements(movements);
        }

        return pawnToReplace != null;
    }

    public int getMaxReplaceMoves(List<IMovement> movements) {
        if (!movements.equals(this.movements)) {
            this.compileMovements(movements);
        }

        if(pawnToReplace == null) {
            return 0;
        } else if(pawnToReplace.getClass() == Trader.class) {
            return 1;       //TODO check that!
        } else if(pawnToReplace.getClass() == Merchant.class) {
            return 2;       //TODO check that!
        } else {
            throw new RuntimeException("Pawn not reconized to get max replace moves allowed");
        }
    }

    private void compileMovements(List<IMovement> movements) {
        List<IAction> actions = Lists.newArrayList();

        Pawn pawnToReplace = null;
        Actions lastMergeAction = null;
        List<IMovement> mergeableMoves = Lists.newArrayList();

        for (int i = 0; i < movements.size(); i++) {
            IMovement movement = movements.get(i);

            //On calcule si on peux merger avec le mouvement précédent
            if (lastMergeAction != movement.getActionDone()) {
                //Actions différente, on merge l'action dans tous les cas
                if (mergeableMoves.size() > 0) {
                    actions.add(createAction(lastMergeAction, mergeableMoves));
                    mergeableMoves.clear();
                }
                mergeableMoves.add(movement);
                lastMergeAction = movement.getActionDone();
            } else {
                //A actions égales, on regarde si on peux merger
                if (mergeableMoves.size() < movement.getMergeableMove()) {
                    //On prépare a merger
                    mergeableMoves.add(movement);
                } else {
                    //On peux plus merger, on merge ce que l'on peux
                    if (mergeableMoves.size() > 0) {
                        actions.add(createAction(lastMergeAction, mergeableMoves));
                        mergeableMoves.clear();
                    }
                    //Puis on prépare a remerge le courant
                    mergeableMoves.add(movement);
                }
            }

            //Ensuite, on regarde si on a un pion a replacer
            if (movement.getPawnToReplace() != null) {
                pawnToReplace = movement.getPawnToReplace();
                for (int j = i + 1; j < movements.size(); j++) {
                    //On merge tous les replace qui suivent
                    if (movements.get(j).getActionDone() == Actions.replaceMovedPawn) {
                        mergeableMoves.add(movements.get(j));  //On merge
                    }
                    if (movements.get(j).getActionDone() == Actions.validateMovedPawn) {
                        mergeableMoves.add(movements.get(j));  //On merge
                        pawnToReplace = null;                  //On considére le pawn comme replacé
                        i = j;                                 //On skip dans la boucle principale
                        break;
                    }
                }
            }
        }

        if (!mergeableMoves.isEmpty()) {
            actions.add(createAction(lastMergeAction, mergeableMoves));
            mergeableMoves.clear();
        }

        this.actions = actions;
        this.movements = movements;
        this.pawnToReplace = pawnToReplace;
    }

}
