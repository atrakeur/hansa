package fr.univ_rouen.hansa.gameboard.cities;



import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.view.IPosition;
import fr.univ_rouen.hansa.view.display.HansaCoellenDrawer;
import fr.univ_rouen.hansa.view.display.IDrawer;
import fr.univ_rouen.hansa.view.interactions.HansaCoellenClickableArea;
import fr.univ_rouen.hansa.view.interactions.IClickableArea;

/**
 * handle distribution of Coellen score board
 * handle getter and setter of pawns on Coellen score board too
 */
public class VictoryCoellen {

    private ICity city;
    private List<IPosition> position;
    private IDrawer drawer;
    private IClickableArea clickableArea;
    private List<Privillegium> privillegium;
    private Merchant merchant;
    private boolean isAvailable;
    private List<Integer> value;

    public VictoryCoellen(ICity c, List<IPosition> p, List<Privillegium> priv, List<Integer> val) {
        this.city = c;
        this.position = p;
        this.privillegium = priv;
        //drawer = new HansaCoellenDrawer(this);
        isAvailable = true;
        clickableArea = new HansaCoellenClickableArea(city.getVc()); //voir de this
        value = val;

    }

    public ICity getCity() {
        return city;
    }

    public List<IPosition> getPosition() {
        return Lists.newArrayList(position);
    }

    public IDrawer getDrawer() {
        return drawer;
    }

    public IClickableArea getClickableArea() {
        return clickableArea;
    }
    public List<Privillegium> getPrivillegium() {
        return Lists.newArrayList(privillegium);
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        if (!isAvailable()) {
            throw new IllegalStateException("Pas libre");
        }
        this.merchant = merchant;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public List<Integer> getValue() {return Lists.newArrayList(value);}

    /**
     * La classe de départ
     * package fr.univ_rouen.hansa.gameboard.cities;

     import java.util.HashMap;
     import java.util.Map;

     import fr.univ_rouen.hansa.gameboard.Privillegium;
     import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
     import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;

     /**
     * handle distribution of Coellen score board
     * handle getter and setter of pawns on Coellen score board too

    public class VictoryCoellen {

        private static final Map<Privillegium, Integer> POINTS_FROM_PRIVILLEGIUM;

        static {
            Map<Privillegium, Integer> map = new HashMap<Privillegium, Integer>();
            map.put(Privillegium.White, 7);
            map.put(Privillegium.Orange, 8);
            map.put(Privillegium.Pink, 9);
            map.put(Privillegium.Black, 11);
            POINTS_FROM_PRIVILLEGIUM = map;
        }

        private Map<Privillegium, Pawn> pawnsFromPrivillegium;

        private static VictoryCoellen INSTANCE = new VictoryCoellen();

        private VictoryCoellen() {
            pawnsFromPrivillegium = new HashMap<Privillegium, Pawn>();
        }

        public static VictoryCoellen getInstance() {
            return INSTANCE;
        }

        /**
         * Associate Merchant with the privillegium
         *
         * @param merchant
         * @param privillegium

        public void setMerchant(Merchant merchant, Privillegium privillegium) {
            if (!isAvailable(privillegium)) {
                throw new IllegalStateException("A pawns is already placed here");
            }
            pawnsFromPrivillegium.put(privillegium, merchant);
        }

        /**
         * @param privillegium
         * @return value of having a Merchant on the privillegium
        public static int getValue(Privillegium privillegium) {
            return POINTS_FROM_PRIVILLEGIUM.get(privillegium);
        }

        /**
         * @param privillegium
         * @return if there is no Merchant already associate with that privillegium
        public boolean isAvailable(Privillegium privillegium) {
            return pawnsFromPrivillegium.get(privillegium) == null;
        }

        /**
         * @param privillegium
         * @return the pawn associate with the privillegium or null if there is not
        public Pawn getPawn(Privillegium privillegium) {
            return pawnsFromPrivillegium.get(privillegium);
        }
    }
     */
}
