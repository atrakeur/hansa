package fr.univ_rouen.hansa.actions.actions;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.actions.movement.IMovement;

public class ActionFactory {

    private List<IMovement> movements;
    private List<IAction> actions;
    private int replaceMoves = 0;

    private IAction createAction(Actions type, List<IMovement> movements) {
        return new Action(type, movements);
    }

    public List<IAction> getActions(List<IMovement> movements) {
        if (!movements.equals(this.movements)) {
            this.compileMovements(movements);
        }

        return actions;
    }

    public int getReplaceMoves(List<IMovement> movements) {
        if (!movements.equals(this.movements)) {
            this.compileMovements(movements);
        }

        return replaceMoves;
    }

    private void compileMovements(List<IMovement> movements) {
        List<IAction> actions = Lists.newArrayList();

        int replaceMoves = 0;
        Actions lastMergeAction = null;
        List<IMovement> mergeableMoves = Lists.newArrayList();

        for (int i = 0; i < movements.size(); i++) {
            IMovement movement = movements.get(i);

            replaceMoves += movement.getPawnReplaceMove();

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
        }

        if (!mergeableMoves.isEmpty()) {
            actions.add(createAction(lastMergeAction, mergeableMoves));
            mergeableMoves.clear();
        }

        this.actions = actions;
        this.movements = movements;
        this.replaceMoves = replaceMoves;
    }

}
