package fr.univ_rouen.hansa.actions;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.actions.movement.Bursa;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.actions.movement.IncreasePower;
import fr.univ_rouen.hansa.actions.movement.KeepKontor;
import fr.univ_rouen.hansa.actions.movement.KeepRoute;
import fr.univ_rouen.hansa.actions.movement.MovePawnRtoGB;
import fr.univ_rouen.hansa.actions.movement.PlaceBonusMarker;
import fr.univ_rouen.hansa.actions.movement.PlayBonus;
import fr.univ_rouen.hansa.actions.movement.ReplaceMovedPawn;
import fr.univ_rouen.hansa.actions.movement.ValidateMovedPawn;
import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.exceptions.PopupException;
import fr.univ_rouen.hansa.gameboard.TurnManager;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusActiones;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusEscritoire;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusKontor;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusPermutation;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.BonusRemovePawns;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IVisitorBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.gameboard.routes.Village;
import fr.univ_rouen.hansa.view.interactions.IClickableArea;

public class MovementFactory {

    private IBonusMarker bonusMarker;
    private boolean bonusHasToBeReplaced;

    private BonusMove bm;

    public IBonusMarker getBonusMarker() {
        return bonusMarker;
    }

    public void setBonusMarker(IBonusMarker bonusMarker) {
        this.bonusMarker = bonusMarker;
    }

    public void setBonusHasToBeReplaced(boolean b){
        this.bonusHasToBeReplaced = b;
    }

    private class BonusMove implements IVisitorBonusMarker {

        public IMovement movement;

        /*Use for BonusRemovePawns
        * Store the list of pawns to remove from game board*/
        private List<IVillage> villages;


        private ICity city;

        /*Use for BonusKontor*/
        private IVillage village;

        /*Use for BonusPermutation*/
        private IKontor<Pawn> k1;
        private IKontor<Pawn> k2;

        private IClickableArea source;

        public BonusMove(){
            villages = Lists.newArrayList();

            city = null;
            village = null;
            k1 = null;
            k2 = null;
        }

        public IMovement createMovement(IClickableArea source){
            this.source = source;
            bonusMarker.accept(this);
            return movement;
        }

        @Override
        public void visit(IBonusMarker marker) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void visit(BonusActiones marker) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void visit(BonusEscritoire marker) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void visit(BonusKontor marker) {
            if(source.getType() == IClickableArea.Type.village){
                village = (IVillage) source.getSubject();
            } else if(source.getType() == IClickableArea.Type.city){
                city = (ICity) source.getSubject();
            }

            if(village != null && city != null){
                marker.setCity(city);
                marker.setVillage(village);
                movement = new PlayBonus(marker);
                city = null;
                village = null;
            }
        }

        @Override
        public void visit(BonusPermutation marker) {
            //TODO a faire une fois que les kontor seront mieux implémenté
            if(source.getType() == IClickableArea.Type.city){
                city = (ICity) source.getSubject();
            }

            if(city != null && k1 != null && k2 != null){
                marker.setCity(city);
                marker.setKontor1(k1);
                marker.setKontor2(k2);
                movement = new PlayBonus(marker);
                city = null;
                k1 = null;
                k2 = null;
            }
        }

        @Override
        public void visit(BonusRemovePawns marker) {
            if(source.getType() == IClickableArea.Type.village){
                IVillage village = (IVillage) source.getSubject();
                if(!village.isEmpty()){
                    villages.add(village);
                    if(villages.size() == 3){
                        marker.setVillage(villages);
                        movement = new PlayBonus(marker);
                        villages.clear();
                    }

                }
            }
        }
    }

    public IMovement makeBonusMove(IClickableArea source){
        return bm.createMovement(source);
    }

    /*public IMovement makeRemovePawnMovement(IClickableArea source){
        IMovement mov = null;

        if(source.getType() == IClickableArea.Type.village){
            IVillage village = (IVillage) source.getSubject();
            if(village.isEmpty()){
                throw new IllegalArgumentException();
            }

            villages.add(village);
            if(villages.size() == 3){
                BonusRemovePawns bonusMarker = (BonusRemovePawns)this.bonusMarker;
                bonusMarker.setVillage(villages);
                mov = new PlayBonus(bonusMarker);
                villages.clear();
            }
        }
        return mov;
    }
*/

    public IMovement makePlaceBonusMarkerMovement(IClickableArea source){
        IMovement mov;
        if(source.getType() == IClickableArea.Type.village ){
            Village v = (Village) source.getSubject();
            mov = new PlaceBonusMarker(bonusMarker, v.getRoute());
        } else {
            throw new GameException();
        }

        return mov;
    }


    private static MovementFactory ourInstance = new MovementFactory();

    public static MovementFactory getInstance() {
        return ourInstance;
    }

    private Class<? extends Pawn> pawnType;

    private MovementFactory() {
        pawnType = Trader.class;
        bm= new BonusMove();
    }

    public void setPawnType(Class<? extends Pawn> pawnType) {
        if (pawnType == null) {
            throw new IllegalArgumentException("Pawn type can't be null");
        }
        this.pawnType = pawnType;
    }

    public Class<? extends Pawn> getPawnType() {
        return pawnType;
    }

    /**
     * A method for make all the movement possible in the Hansa game with two clickable area
     *
     * @param source      IClickableArea source
     * @param destination IClickableArea destination
     * @return The movement made with Source and Destination
     * @throws PopupException when the action need a pawnType for the movePawnRtoGB movement
     */
    public IMovement makeMovement(IClickableArea source, IClickableArea destination) {
        IHTPlayer player = TurnManager.getInstance().getCurrentPlayingPlayer();

        //Sépare le cas ou on joue normal du cas ou on joue pas normal
        if (TurnManager.getInstance().getCurrentPlayingPlayer() == TurnManager.getInstance().getCurrentPlayer()) {
            if(bonusMarker == null){
                return makeNormalMove(source, destination, player);
            } else if(bonusHasToBeReplaced){
                return makePlaceBonusMarkerMovement(source);
            } else {
                return makeBonusMove(source);
            }
        } else {
            return makeReplaceMove(source, destination, player);
        }
    }

    private IMovement makeReplaceMove(IClickableArea source, IClickableArea destination, IHTPlayer player) {
        if (source == null && destination == null) {
            return new ValidateMovedPawn();
        }
        else if (source.getType() == IClickableArea.Type.supply && destination.getType() == IClickableArea.Type.village ) {
            return new ReplaceMovedPawn(player, (IVillage) destination.getSubject(), pawnType);
        }

        throw new GameException("Invalid movement");
    }

    private IMovement makeNormalMove(IClickableArea source, IClickableArea destination, IHTPlayer player) {
        if (source == null && destination == null) {
            //TODO Fin de partie
            throw new UnsupportedOperationException();
        } else if (source.getType() == IClickableArea.Type.village && destination == null ) {
            //Si le village est vide, on le prend, sinon c'est une prise de comptoir
            IVillage village = (IVillage) source.getSubject();

            if (village.getRoute().isTradeRoute(player)) {
                //Si la route est pleine, c'est une prise de route
                return new KeepRoute(player, village.getRoute());
            } else if (village.isEmpty() || (village.getOwner() != null && village.getOwner() != player)) {
                //Si le village est vide
                //Si le village est occupé par un joueur, mais qui n'est pas le joueur courrant
                //C'est une prise de village
                return new MovePawnRtoGB(player, village, pawnType);
            } else {
                //Tous les autres cas on sort
                throw new GameException("Invalid movement");
            }
        } else if (source.getType() == IClickableArea.Type.village && destination.getType() == IClickableArea.Type.city) {
            return new KeepKontor(player, (ICity) destination.getSubject(), (IVillage) source.getSubject());
        } else if (source.getType() == IClickableArea.Type.village && destination.getType() == IClickableArea.Type.power) {
            //FIXME (07/04) non testé, destination ne réfère peut être pas à une icity
            return new IncreasePower(player, ((ICity) destination.getSubject()), ((IVillage) destination.getSubject()).getRoute());
        } else if (source.getType() == IClickableArea.Type.bonus && destination == null) {
            return new PlayBonus(((IBonusMarker) source.getSubject()));
        } else if (source.getType() == IClickableArea.Type.supply && destination.getType() == IClickableArea.Type.village) {
            return new MovePawnRtoGB(player, (IVillage) destination.getSubject(), pawnType);
        }

        throw new GameException("Invalid movement");
    }

    public IMovement makeBursaMovement(int merchant) {
        IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();

        return new Bursa(player, merchant);
    }

    public IMovement makeBursaMovement() {
        IHTPlayer player = TurnManager.getInstance().getCurrentPlayer();

        return new Bursa(player);
    }
}
