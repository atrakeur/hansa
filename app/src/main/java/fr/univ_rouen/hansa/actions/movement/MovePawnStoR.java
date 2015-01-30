package fr.univ_rouen.hansa.actions.movement;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.exceptions.NotAvailableActionException;
import fr.univ_rouen.hansa.exceptions.NotEnoughSupplyException;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;

public class MovePawnStoR implements IMovement {

    private IHTPlayer player;
    private int nbMerchant;
    private int nbTrader;

    private boolean actionDone;

    public MovePawnStoR (IHTPlayer player, int nbMerchant, int nbTrader){

        if(player == null || nbMerchant < 0 || nbTrader < 0){
            throw new IllegalArgumentException();
        }

        if(player.getEscritoire().bursaLevel() < (nbTrader + nbMerchant)){
            throw new NotAvailableActionException("Bursa level insufficient.");
        }

        try{
            player.getEscritoire().getFromSupply(nbMerchant, nbTrader);
        } catch (NotEnoughSupplyException e){
            throw e;
        }

        this.player = player;
        this.nbMerchant = nbMerchant;
        this.nbTrader = nbTrader;

        this.actionDone = false;
    }
    @Override
    public boolean isDone() {
        return actionDone;
    }

    @Override
    public Actions getActionDone() {
        return Actions.movePawnStoR;
    }

    @Override
    public void doMovement() {

        if(actionDone){
            throw new IllegalStateException("Movement already done");
        }

        try{
            player.getEscritoire().moveStockToSupply(nbMerchant, nbTrader);
            this.actionDone = true;
        }catch(NotEnoughSupplyException ex) {
            throw ex;
        }
    }

    @Override
    public void doRollback() {
        if(!actionDone){
            throw new IllegalStateException("Movement not done yet");
        }
        //TODO escritoire.moveSupplyToStock ?
    }
}
