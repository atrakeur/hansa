package fr.univ_rouen.hansa.gameboard.routes;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.view.IPosition;
import fr.univ_rouen.hansa.view.interactions.HansaVillageClickableArea;
import fr.univ_rouen.hansa.view.interactions.IClickableArea;

public class Village implements IVillage {

    private final IPosition position;
    private Pawn pawn;
    private IRoute route;

    private IClickableArea clickableArea;

    public Village(IPosition position) {
        this.position = position;

        this.clickableArea = new HansaVillageClickableArea(this);
    }

    @Override
    public IRoute getRoute() {
        if (route == null) {
            throw new IllegalStateException();
        }

        return route;
    }

    @Override
    public void setRoute(IRoute route) {
        if (this.route != null) {
            throw new IllegalStateException();
        }

        this.route = route;
    }

    @Override
    public boolean isEmpty() {
        return pawn == null;
    }

    @Override
    public IPosition getPosition() {
        return position;
    }

    @Override
    public IHTPlayer getOwner() {
        if (pawn != null) {
            return pawn.getPlayer();
        }

        return null;
    }

    @Override
    public Class<? extends Pawn> getPawnType() {
        if (pawn == null) {
            return null;
        }

        return pawn.getClass();
    }

    @Override
    public void pushPawn(Pawn pawn) {
        if (!isEmpty()) {
            throw new IllegalStateException("Village already taken");
        }

        this.pawn = pawn;
    }

    @Override
    public Pawn pullPawn() {
        if (isEmpty()) {
            throw new IllegalStateException("Village has no pawn");
        }

        Pawn pawn = this.pawn;
        this.pawn = null;

        return pawn;
    }

    @Override
    public IClickableArea getClickableArea() {
        return this.clickableArea;
    }

    @Override
    public List<IVillage> getAdjacentsVillages(){
        LinkedList<IVillage> toTravel = new LinkedList<IVillage>();
        List<IVillage> adj = new ArrayList<IVillage>();
        Map<IVillage, Boolean> visited = new HashMap<IVillage, Boolean>();

        toTravel.add(this);
        visited.put(this,true);


        while(!toTravel.isEmpty()){
            System.out.println("toTravel");
            IVillage village = toTravel.getFirst();
            if (village.isEmpty()){
                System.out.println("  empty");
                adj.add(village);
                visited.put(village,true);
            } else {
                System.out.println("  !empty");
                IRoute route = village.getRoute();

                int index = route.getVillages().indexOf(village);

                System.out.println("    index : " + index);
                //add the one before if village is not at the beginning of the route
                if (index != 0){
                    System.out.println("add -1");
                    toTravel.add(route.getVillage(index-1));
                }

                //add the one after if village is not at the end of the route
                if (index != route.getVillages().size() -1){
                    System.out.println("add +1");
                    toTravel.add(route.getVillage(index + 1));
                }

                //add the "firsts" villages (except itself) on the route next to the nearest city if
                //  village is at an extremity of the route
                if (index == 0 || index == route.getVillages().size() - 1) {
                    ICity nearestCity = village.getNearestCity();
                    System.out.println("    extremity");
                    System.out.println("    nearest city : " + nearestCity.getPosition().getX() + " - " + nearestCity.getPosition().getY());
                    for (IVillage villageNextToCity : nearestCity.getNearestVillages()) {
                        System.out.println("    village next to city ");
                        if (villageNextToCity != village ){
                            System.out.println("    pos : " + villageNextToCity.getPosition().getX() + " - " + villageNextToCity.getPosition().getY());
                            toTravel.add(villageNextToCity);
                        }
                    }
                }

                visited.put(village,true);
            }
            toTravel.remove(village);
        }


        return adj;
    }

    @Override
    public ICity getNearestCity() {
        float posX = getPosition().getX();
        float posY = getPosition().getY();

        float pos0X = getRoute().getCities()[0].getPosition().getX();
        float pos0Y = getRoute().getCities()[0].getPosition().getY();

        float pos1X = getRoute().getCities()[1].getPosition().getX();
        float pos1Y = getRoute().getCities()[1].getPosition().getY();

        double d0 = Math.sqrt((posX-pos0X)*(posX-pos0X) + (posY-pos0Y)*(posY-pos0Y));
        double d1 = Math.sqrt((posX-pos1X)*(posX-pos1X) + (posY-pos1Y)*(posY-pos1Y));

        if (d0 < d1){
            return getRoute().getCities()[0];
        }
        return getRoute().getCities()[1];
    }
}
