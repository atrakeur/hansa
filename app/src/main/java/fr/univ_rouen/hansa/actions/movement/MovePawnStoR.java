package fr.univ_rouen.hansa.actions.movement;

import java.util.List;

import fr.univ_rouen.hansa.actions.Actions;
import fr.univ_rouen.hansa.exceptions.NotAvailableActionException;
import fr.univ_rouen.hansa.exceptions.NotEnoughSupplyException;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;

public class MovePawnStoR implements IMovement {

    private IHTPlayer player;
    private int nbMerchant;
    private int nbTrader;

    private boolean actionDone;

    /* Constructeur de la classe MovePawnStoR, pour la compétence Bursa au Maximum
    * @throw NotAvailableActionException si la compétence bursa de player n'est pas au maximum
    * */
    public MovePawnStoR(IHTPlayer player){
        if(player == null){
            throw new IllegalArgumentException();
        }

        if(player.getEscritoire().bursaLevel() < Integer.MAX_VALUE){
            throw new NotAvailableActionException("Bursa level insufficient.");
        }

        this.player = player;
        this.nbTrader = this.player.getEscritoire().getStock().getTraderCount();
        this.nbMerchant = this.player.getEscritoire().getStock().getMerchantCount();
    }

    /**Constructeur de la classe MovePawnStoR
     * L'instanciation des traders se fait de la façon suivante :
     * Trader =
     *  - Bursa - Merchant (soit le nombre de pions déplacable restant)
     *  - Au nombre de trader présent dans le stock (si la compétence bursa est très élevée, et qu'il y a
     *      moins de trader présent dans le stock que ceux données par bursa - merchant).
     * Aussi : Si le player a augmenté son pouvoir Bursa au maximum, les traders et les merchants prennent le nombre
     * respectif de trader et de merchant qu'il y a dans le stock
     *
     * @throws NotEnoughSupplyException S'il n'y a pas assez de merchant dans le stock
     * @throws NotAvailableActionException Si le nombre de merchant est supérieur à la valeur actuel du pouvoir bursa du player.
     * */
    public MovePawnStoR(IHTPlayer player, int merchant){
        if(player == null || merchant < 0){
            throw new IllegalArgumentException();
        }

        if(player.getEscritoire().bursaLevel() == Integer.MAX_VALUE){
            this.player = player;
            this.nbTrader = this.player.getEscritoire().getStock().getTraderCount();
            this.nbMerchant = this.player.getEscritoire().getStock().getMerchantCount();
        } else {

            if (!player.getEscritoire().enoughStock(merchant, 0)) {
                throw new NotEnoughSupplyException();
            }

            if (player.getEscritoire().bursaLevel() < merchant) {
                throw new NotAvailableActionException("Bursa level insufficient.");
            }

            this.player = player;
            this.nbMerchant = merchant;
            this.nbTrader = Math.min(player.getEscritoire().getStock().getTraderCount(),
                    player.getEscritoire().bursaLevel() - merchant);
        }

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
        }catch(NotEnoughSupplyException e) {
            throw e;
        }
    }

    @Override
    public void doRollback() {
        if(!actionDone){
            throw new IllegalStateException("Movement not done yet");
        }

        List<Pawn> pawns;
        try{
            pawns = player.getEscritoire().popFromSupply(nbMerchant, nbTrader);
        } catch (NotEnoughSupplyException e){
            throw e;
        }

        try{
            player.getEscritoire().addToStock(pawns);
            actionDone = false;
        }catch(NotEnoughSupplyException e){
            player.getEscritoire().addToSupply(pawns);
            throw e;
        }
    }
}
