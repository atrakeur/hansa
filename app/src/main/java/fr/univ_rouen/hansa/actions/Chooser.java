package fr.univ_rouen.hansa.actions;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;

public class Chooser implements IChooser {
    public int[] replaceMovedPawnData(IVillage village) {
        int[] values = new int[3];

        if (village.getPawnType().equals(Trader.class)) {
            values[0] = 2;
        } else if (village.getPawnType().equals(Merchant.class)) {
            values[0] = 3;
        } else {
            throw new IllegalStateException("Village empty");
        }

        values[1] = village.getOwner().getEscritoire().getSupply().getTraderCount();
        values[2] = village.getOwner().getEscritoire().getSupply().getMerchantCount();

        return values;
    }

    public List<IVillage> replaceMovedPawnLocation(IRoute source) {
        List<IVillage> villages = Lists.newArrayList();

        for (IRoute route : replaceLocation(source, source.getCities()[0])) {
            for (IVillage village : route.getVillages()) {
                villages.add(village);
            }
        }

        for (IRoute route : replaceLocation(source, source.getCities()[1])) {
            for (IVillage village : route.getVillages()) {
                villages.add(village);
            }
        }

        return villages;
    }

    private List<IRoute> replaceLocation(IRoute sourceRoute, ICity sourceCity) {
        List<IRoute> routes = Lists.newArrayList();

        ICity city;

        if (sourceRoute.getCities()[0].equals(sourceCity)) {
            city = sourceRoute.getCities()[1];
        } else {
            city = sourceRoute.getCities()[0];
        }

        for (IRoute route : city.getRoutes()) {
            if (!route.equals(sourceRoute)) {
                if (route.isTradeRoute()) {
                    routes = replaceLocation(route, city);
                } else {
                    routes.add(route);
                }
            }
        }

        return routes;
    }
}
