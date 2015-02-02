package fr.univ_rouen.hansa.gameboard.board;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.cities.City;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.Kontor;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.player.pawns.Trader;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;
import fr.univ_rouen.hansa.gameboard.routes.Route;
import fr.univ_rouen.hansa.gameboard.routes.Village;
import fr.univ_rouen.hansa.util.CityPositions;
import fr.univ_rouen.hansa.util.TavernPositions;
import fr.univ_rouen.hansa.util.VillagePositions;
import fr.univ_rouen.hansa.view.IPosition;

public class GameBoardFactory {
    private static GameBoardFactory ourInstance = new GameBoardFactory();

    public static GameBoardFactory getInstance() {
        return ourInstance;
    }

    private GameBoardFactory() {
    }

    /**
     * Create and init a new GameBoard
     *
     * @param map 1 for the map of 2/3 players, 2 for the map of 4/5 players
     * @return the gameboard initialized
     */
    public GameBoard createGameBoard(int map) {
        GameBoard gameBoard = new GameBoard();
        List<IKontor<? extends Pawn>> kontors;
        List<IVillage> villages;
        ICity[] cities;
        IPosition tavernPosition;

        gameBoard.setBackground(R.drawable.plateau23);


        // ------------------------------------ //
        // -------------- CITIES -------------- //
        // ------------------------------------ //


        //GRONINGEN
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(true, Privillegium.White));
        kontors.add(new Kontor<Merchant>(false, Privillegium.Orange));
        ICity groningen = new City(CityPositions.GRONINGEN, Power.LiberSophiae, kontors);
        gameBoard.addCity(groningen);

        //EMDEN
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Merchant>(false, Privillegium.White));
        kontors.add(new Kontor<Trader>(false, Privillegium.Pink));
        ICity emden = new City(CityPositions.EMDEN, Power.Null, kontors);
        gameBoard.addCity(emden);

        //HAMBURG
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(false, Privillegium.White));
        kontors.add(new Kontor<Trader>(false, Privillegium.Orange));
        kontors.add(new Kontor<Trader>(false, Privillegium.Black));
        ICity hamburg = new City(CityPositions.HAMBURG, Power.Null, kontors);
        gameBoard.addCity(hamburg);

        //LUBECK
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(true, Privillegium.White));
        kontors.add(new Kontor<Trader>(false, Privillegium.Pink));
        ICity lubeck = new City(CityPositions.LUBECK, Power.Bursa, kontors);
        gameBoard.addCity(lubeck);

        //OSNABRUCK
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(false, Privillegium.White));
        kontors.add(new Kontor<Trader>(false, Privillegium.Orange));
        kontors.add(new Kontor<Trader>(false, Privillegium.Black));
        ICity osnabruck = new City(CityPositions.OSNABRUCK, Power.Null, kontors);
        gameBoard.addCity(osnabruck);

        //HANNOVER
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(false, Privillegium.White));
        kontors.add(new Kontor<Trader>(false, Privillegium.Pink));
        ICity hannover = new City(CityPositions.HANNOVER, Power.Null, kontors);
        gameBoard.addCity(hannover);

        //PERLBERG
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(false, Privillegium.White));
        kontors.add(new Kontor<Trader>(false, Privillegium.Pink));
        kontors.add(new Kontor<Trader>(false, Privillegium.Black));
        ICity perlberg = new City(CityPositions.PERLBERG, Power.Null, kontors);
        gameBoard.addCity(perlberg);

        //ARNHEIM
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(false, Privillegium.White));
        kontors.add(new Kontor<Merchant>(false, Privillegium.White));
        kontors.add(new Kontor<Trader>(false, Privillegium.Orange));
        kontors.add(new Kontor<Trader>(false, Privillegium.Pink));
        ICity arnheim = new City(CityPositions.ARNHEIM, Power.Null, kontors);
        gameBoard.addCity(arnheim);

        //MUNSTER
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Merchant>(false, Privillegium.White));
        kontors.add(new Kontor<Trader>(false, Privillegium.Orange));
        ICity munster = new City(CityPositions.MUNSTER, Power.Null, kontors);
        gameBoard.addCity(munster);

        //MINDEN
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(false, Privillegium.White));
        kontors.add(new Kontor<Trader>(false, Privillegium.Orange));
        kontors.add(new Kontor<Trader>(false, Privillegium.Pink));
        kontors.add(new Kontor<Trader>(false, Privillegium.Black));
        ICity minden = new City(CityPositions.MINDEN, Power.Null, kontors);
        gameBoard.addCity(minden);

        //BRUNSWIEK
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(false, Privillegium.Orange));
        ICity brunswiek = new City(CityPositions.BRUNSWIEK, Power.Null, kontors);
        gameBoard.addCity(brunswiek);

        //STENDAL
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(false, Privillegium.White));
        kontors.add(new Kontor<Merchant>(false, Privillegium.White));
        kontors.add(new Kontor<Trader>(false, Privillegium.Orange));
        kontors.add(new Kontor<Trader>(false, Privillegium.Pink));
        ICity stendal = new City(CityPositions.STENDAL, Power.Null, kontors);
        gameBoard.addCity(stendal);

        //DUISBURG
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(false, Privillegium.White));
        ICity duisburg = new City(CityPositions.DUISBURG, Power.Null, kontors);
        gameBoard.addCity(duisburg);

        //PADERBORN
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(false, Privillegium.White));
        kontors.add(new Kontor<Merchant>(false, Privillegium.Black));
        ICity paderborn = new City(CityPositions.PADERBORN, Power.Null, kontors);
        gameBoard.addCity(paderborn);

        //HILDESHEIM
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(false, Privillegium.White));
        kontors.add(new Kontor<Trader>(false, Privillegium.Black));
        ICity hildesheim = new City(CityPositions.HILDESHEIM, Power.Null, kontors);
        gameBoard.addCity(hildesheim);

        //MAGDEBURG
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Merchant>(false, Privillegium.White));
        kontors.add(new Kontor<Trader>(false, Privillegium.Orange));
        ICity magdeburg = new City(CityPositions.MAGDEBURG, Power.Null, kontors);
        gameBoard.addCity(magdeburg);

        //COELLEN
        //TODO Gestion spécial de Coellen avec les points bonus
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(true, Privillegium.White));
        kontors.add(new Kontor<Trader>(false, Privillegium.Pink));
        ICity coellen = new City(CityPositions.COELLEN, Power.Null, kontors);
        gameBoard.addCity(coellen);

        //QUEDLINBURG
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Merchant>(false, Privillegium.Orange));
        kontors.add(new Kontor<Merchant>(false, Privillegium.Pink));
        ICity quedlinburg = new City(CityPositions.QUEDLINBURG, Power.Null, kontors);
        gameBoard.addCity(quedlinburg);

        //HALLE
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(true, Privillegium.White));
        kontors.add(new Kontor<Trader>(false, Privillegium.Orange));
        ICity halle = new City(CityPositions.HALLE, Power.ClavisUrbis, kontors);
        gameBoard.addCity(halle);

        //Villes qui changent entre les maps 2-3 et 4-5
        ICity dortmund;
        ICity kampen;
        ICity bremen;
        ICity stade;
        ICity luneburg;
        ICity goslar;
        ICity gottingen;
        ICity marburg;

        if (map == 1) {
            //DORTMUND
            kontors = Lists.newArrayList();
            kontors.add(new Kontor<Merchant>(false, Privillegium.White));
            kontors.add(new Kontor<Trader>(false, Privillegium.Orange));
            dortmund = new City(CityPositions.DORTMUND, Power.Null, kontors);
            gameBoard.addCity(dortmund);

            //KAMPEN
            kontors = Lists.newArrayList();
            kontors.add(new Kontor<Trader>(false, Privillegium.Orange));
            kontors.add(new Kontor<Trader>(false, Privillegium.Black));
            kampen = new City(CityPositions.KAMPEN, Power.Null, kontors);
            gameBoard.addCity(kampen);

            //BREMEN
            kontors = Lists.newArrayList();
            kontors.add(new Kontor<Trader>(false, Privillegium.Pink));
            bremen = new City(CityPositions.BREMEN, Power.Null, kontors);
            gameBoard.addCity(bremen);

            //STADE
            kontors = Lists.newArrayList();
            kontors.add(new Kontor<Merchant>(true, Privillegium.White));
            stade = new City(CityPositions.STADE, Power.Privillegium, kontors);
            gameBoard.addCity(stade);

            //LUNEBURG
            kontors = Lists.newArrayList();
            kontors.add(new Kontor<Merchant>(false, Privillegium.White));
            luneburg = new City(CityPositions.LUNEBURG, Power.Null, kontors);
            gameBoard.addCity(luneburg);

            //GOSLAR
            kontors = Lists.newArrayList();
            kontors.add(new Kontor<Trader>(false, Privillegium.White));
            goslar = new City(CityPositions.GOSLAR, Power.Null, kontors);
            gameBoard.addCity(goslar);

            //GOTTINGEN
            kontors = Lists.newArrayList();
            kontors.add(new Kontor<Trader>(false, Privillegium.White));
            kontors.add(new Kontor<Trader>(false, Privillegium.Orange));
            gottingen = new City(CityPositions.GOTTINGEN, Power.Actiones, kontors);
            gameBoard.addCity(gottingen);

            //MARBURG
            kontors = Lists.newArrayList();
            kontors.add(new Kontor<Trader>(true, Privillegium.Orange));
            kontors.add(new Kontor<Trader>(false, Privillegium.Pink));
            marburg = new City(CityPositions.MARBURG, Power.Null, kontors);
            gameBoard.addCity(marburg);
        } else {
            //DORTMUND
            kontors = Lists.newArrayList();
            kontors.add(new Kontor<Merchant>(false, Privillegium.White));
            kontors.add(new Kontor<Trader>(false, Privillegium.Orange));
            kontors.add(new Kontor<Trader>(false, Privillegium.Pink));
            dortmund = new City(CityPositions.DORTMUND, Power.Null, kontors);
            gameBoard.addCity(dortmund);

            //KAMPEN
            kontors = Lists.newArrayList();
            kontors.add(new Kontor<Merchant>(false, Privillegium.Orange));
            kontors.add(new Kontor<Trader>(false, Privillegium.Black));
            kampen = new City(CityPositions.KAMPEN, Power.Null, kontors);
            gameBoard.addCity(kampen);

            //BREMEN
            kontors = Lists.newArrayList();
            kontors.add(new Kontor<Merchant>(false, Privillegium.White));
            kontors.add(new Kontor<Trader>(false, Privillegium.Pink));
            bremen = new City(CityPositions.BREMEN, Power.Null, kontors);
            gameBoard.addCity(bremen);

            //STADE
            kontors = Lists.newArrayList();
            kontors.add(new Kontor<Merchant>(false, Privillegium.White));
            stade = new City(CityPositions.STADE, Power.Privillegium, kontors);
            gameBoard.addCity(stade);

            //LUNEBURG
            kontors = Lists.newArrayList();
            kontors.add(new Kontor<Merchant>(false, Privillegium.Orange));
            kontors.add(new Kontor<Trader>(false, Privillegium.Black));
            luneburg = new City(CityPositions.LUNEBURG, Power.Null, kontors);
            gameBoard.addCity(luneburg);

            //GOSLAR
            kontors = Lists.newArrayList();
            kontors.add(new Kontor<Trader>(false, Privillegium.White));
            kontors.add(new Kontor<Trader>(false, Privillegium.Black));
            goslar = new City(CityPositions.GOSLAR, Power.Null, kontors);
            gameBoard.addCity(goslar);

            //GOTTINGEN
            kontors = Lists.newArrayList();
            kontors.add(new Kontor<Merchant>(false, Privillegium.Orange));
            kontors.add(new Kontor<Merchant>(false, Privillegium.Pink));
            gottingen = new City(CityPositions.GOTTINGEN, Power.Actiones, kontors);
            gameBoard.addCity(gottingen);

            //MARBURG
            kontors = Lists.newArrayList();
            kontors.add(new Kontor<Trader>(false, Privillegium.Orange));
            kontors.add(new Kontor<Trader>(false, Privillegium.Pink));
            marburg = new City(CityPositions.MARBURG, Power.Null, kontors);
            gameBoard.addCity(marburg);
        }


        // ------------------------------------ //
        // -------------- ROUTES -------------- //
        // ------------------------------------ //


        //GRONINGEN_EMDEN
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.GRONINGEN_EMDEN_1));
        villages.add(new Village(VillagePositions.GRONINGEN_EMDEN_2));
        villages.add(new Village(VillagePositions.GRONINGEN_EMDEN_3));
        cities = new ICity[]{groningen, emden};
        tavernPosition = TavernPositions.GRONINGEN_EMDEN;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //EMDEN_OSNABRUCK
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.EMDEN_OSNABRUCK_1));
        villages.add(new Village(VillagePositions.EMDEN_OSNABRUCK_2));
        villages.add(new Village(VillagePositions.EMDEN_OSNABRUCK_3));
        cities = new ICity[]{emden, osnabruck};
        tavernPosition = TavernPositions.EMDEN_OSNABRUCK;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //OSNABRUCK_BREMEN
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.OSNABRUCK_BREMEN_1));
        villages.add(new Village(VillagePositions.OSNABRUCK_BREMEN_2));
        villages.add(new Village(VillagePositions.OSNABRUCK_BREMEN_3));
        cities = new ICity[]{osnabruck, bremen};
        tavernPosition = TavernPositions.OSNABRUCK_BREMEN;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //BREMEN_HAMBURG
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.BREMEN_HAMBURG_1));
        villages.add(new Village(VillagePositions.BREMEN_HAMBURG_2));
        villages.add(new Village(VillagePositions.BREMEN_HAMBURG_3));
        villages.add(new Village(VillagePositions.BREMEN_HAMBURG_4));
        cities = new ICity[]{bremen, hamburg};
        tavernPosition = TavernPositions.BREMEN_HAMBURG;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //STADE_HAMBURG
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.STADE_HAMBURG_1));
        villages.add(new Village(VillagePositions.STADE_HAMBURG_2));
        villages.add(new Village(VillagePositions.STADE_HAMBURG_3));
        cities = new ICity[]{stade, hamburg};
        tavernPosition = TavernPositions.STADE_HAMBURG;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //HAMBURG_LUBECK
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.HAMBURG_LUBECK_1));
        villages.add(new Village(VillagePositions.HAMBURG_LUBECK_2));
        villages.add(new Village(VillagePositions.HAMBURG_LUBECK_3));
        cities = new ICity[]{hamburg, lubeck};
        tavernPosition = TavernPositions.HAMBURG_LUBECK;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //KEMPEN_OSNABRUCK
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.KEMPEN_OSNABRUCK_1));
        villages.add(new Village(VillagePositions.KEMPEN_OSNABRUCK_2));
        cities = new ICity[]{kampen, osnabruck};
        tavernPosition = TavernPositions.KEMPEN_OSNABRUCK;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //KEMPEN_ARNHEIM
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.KEMPEN_ARNHEIM_1));
        villages.add(new Village(VillagePositions.KEMPEN_ARNHEIM_2));
        villages.add(new Village(VillagePositions.KEMPEN_ARNHEIM_3));
        cities = new ICity[]{kampen, arnheim};
        tavernPosition = TavernPositions.KEMPEN_ARNHEIM;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //ARNHEIM_MUNSTER
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.ARNHEIM_MUNSTER_1));
        villages.add(new Village(VillagePositions.ARNHEIM_MUNSTER_2));
        villages.add(new Village(VillagePositions.ARNHEIM_MUNSTER_3));
        cities = new ICity[]{arnheim, munster};
        tavernPosition = TavernPositions.ARNHEIM_MUNSTER;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //MUNSTER_MINDEN
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.MUNSTER_MINDEN_1));
        villages.add(new Village(VillagePositions.MUNSTER_MINDEN_2));
        villages.add(new Village(VillagePositions.MUNSTER_MINDEN_3));
        cities = new ICity[]{munster, minden};
        tavernPosition = TavernPositions.MUNSTER_MINDEN;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //BREMEN_MINDEN
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.BREMEN_MINDEN_1));
        villages.add(new Village(VillagePositions.BREMEN_MINDEN_2));
        villages.add(new Village(VillagePositions.BREMEN_MINDEN_3));
        cities = new ICity[]{bremen, minden};
        tavernPosition = TavernPositions.BREMEN_MINDEN;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //BREMEN_HANNOVER
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.BREMEN_HANNOVER_1));
        villages.add(new Village(VillagePositions.BREMEN_HANNOVER_2));
        villages.add(new Village(VillagePositions.BREMEN_HANNOVER_3));
        cities = new ICity[]{bremen, hannover};
        tavernPosition = TavernPositions.BREMEN_HANNOVER;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //MINDEN_HANNOVER
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.MINDEN_HANNOVER_1));
        villages.add(new Village(VillagePositions.MINDEN_HANNOVER_2));
        villages.add(new Village(VillagePositions.MINDEN_HANNOVER_3));
        cities = new ICity[]{minden, hannover};
        tavernPosition = TavernPositions.MINDEN_HANNOVER;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //HANNOVER_LUNEBURG
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.HANNOVER_LUNEBURG_1));
        villages.add(new Village(VillagePositions.HANNOVER_LUNEBURG_2));
        villages.add(new Village(VillagePositions.HANNOVER_LUNEBURG_3));
        cities = new ICity[]{hannover, luneburg};
        tavernPosition = TavernPositions.HANNOVER_LUNEBURG;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //HAMBURG_LUNEBURG
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.HAMBURG_LUNEBURG_1));
        villages.add(new Village(VillagePositions.HAMBURG_LUNEBURG_2));
        villages.add(new Village(VillagePositions.HAMBURG_LUNEBURG_3));
        villages.add(new Village(VillagePositions.HAMBURG_LUNEBURG_4));
        cities = new ICity[]{hamburg, luneburg};
        tavernPosition = TavernPositions.HAMBURG_LUNEBURG;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //LUNEBURG_PERLEBERG
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.LUNEBURG_PERLEBERG_1));
        villages.add(new Village(VillagePositions.LUNEBURG_PERLEBERG_2));
        villages.add(new Village(VillagePositions.LUNEBURG_PERLEBERG_3));
        cities = new ICity[]{luneburg, perlberg};
        tavernPosition = TavernPositions.LUNEBURG_PERLEBERG;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //PERLEBERG_STENDAL
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.PERLEBERG_STENDAL_1));
        villages.add(new Village(VillagePositions.PERLEBERG_STENDAL_2));
        villages.add(new Village(VillagePositions.PERLEBERG_STENDAL_3));
        cities = new ICity[]{perlberg, stendal};
        tavernPosition = TavernPositions.PERLEBERG_STENDAL;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //ARNHEIM_DUISBURG
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.ARNHEIM_DUISBURG_1));
        villages.add(new Village(VillagePositions.ARNHEIM_DUISBURG_2));
        villages.add(new Village(VillagePositions.ARNHEIM_DUISBURG_3));
        cities = new ICity[]{arnheim, duisburg};
        tavernPosition = TavernPositions.ARNHEIM_DUISBURG;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //DUISBURG_DORTMUND
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.DUISBURG_DORTMUND_1));
        villages.add(new Village(VillagePositions.DUISBURG_DORTMUND_2));
        cities = new ICity[]{duisburg, dortmund};
        tavernPosition = TavernPositions.DUISBURG_DORTMUND;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //DORTMUND_PADERBORN
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.DORTMUND_PADERBORN_1));
        villages.add(new Village(VillagePositions.DORTMUND_PADERBORN_2));
        villages.add(new Village(VillagePositions.DORTMUND_PADERBORN_3));
        cities = new ICity[]{dortmund, paderborn};
        tavernPosition = TavernPositions.DORTMUND_PADERBORN;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //COELLEN_MARBURG
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.COELLEN_MARBURG_1));
        villages.add(new Village(VillagePositions.COELLEN_MARBURG_2));
        villages.add(new Village(VillagePositions.COELLEN_MARBURG_3));
        villages.add(new Village(VillagePositions.COELLEN_MARBURG_4));
        cities = new ICity[]{coellen, marburg};
        tavernPosition = TavernPositions.COELLEN_MARBURG;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //MARBURG_PADERBIRN
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.MARBURG_PADERBIRN_1));
        villages.add(new Village(VillagePositions.MARBURG_PADERBIRN_2));
        villages.add(new Village(VillagePositions.MARBURG_PADERBIRN_3));
        cities = new ICity[]{marburg, paderborn};
        tavernPosition = TavernPositions.MARBURG_PADERBIRN;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //MINDEN_PADERBORN
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.MINDEN_PADERBORN_1));
        villages.add(new Village(VillagePositions.MINDEN_PADERBORN_2));
        villages.add(new Village(VillagePositions.MINDEN_PADERBORN_3));
        cities = new ICity[]{minden, paderborn};
        tavernPosition = TavernPositions.MINDEN_PADERBORN;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //MINDEN_BRUNSWIEK
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.MINDEN_BRUNSWIEK_1));
        villages.add(new Village(VillagePositions.MINDEN_BRUNSWIEK_2));
        villages.add(new Village(VillagePositions.MINDEN_BRUNSWIEK_3));
        villages.add(new Village(VillagePositions.MINDEN_BRUNSWIEK_4));
        cities = new ICity[]{minden, brunswiek};
        tavernPosition = TavernPositions.MINDEN_BRUNSWIEK;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //BRUNSWIEK_STENDAL
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.BRUNSWIEK_STENDAL_1));
        villages.add(new Village(VillagePositions.BRUNSWIEK_STENDAL_2));
        villages.add(new Village(VillagePositions.BRUNSWIEK_STENDAL_3));
        villages.add(new Village(VillagePositions.BRUNSWIEK_STENDAL_4));
        cities = new ICity[]{brunswiek, stendal};
        tavernPosition = TavernPositions.BRUNSWIEK_STENDAL;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //PADERBORN_HILDSHEIM
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.PADERBORN_HILDSHEIM_1));
        villages.add(new Village(VillagePositions.PADERBORN_HILDSHEIM_2));
        villages.add(new Village(VillagePositions.PADERBORN_HILDSHEIM_3));
        cities = new ICity[]{paderborn, hildesheim};
        tavernPosition = TavernPositions.PADERBORN_HILDSHEIM;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //HILDESHEIM_GOSLAR
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.HILDESHEIM_GOSLAR_1));
        villages.add(new Village(VillagePositions.HILDESHEIM_GOSLAR_2));
        villages.add(new Village(VillagePositions.HILDESHEIM_GOSLAR_3));
        cities = new ICity[]{hildesheim, goslar};
        tavernPosition = TavernPositions.HILDESHEIM_GOSLAR;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //GOSLAR_MAGDEBURG
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.GOSLAR_MAGDEBURG_1));
        villages.add(new Village(VillagePositions.GOSLAR_MAGDEBURG_2));
        cities = new ICity[]{goslar, magdeburg};
        tavernPosition = TavernPositions.GOSLAR_MAGDEBURG;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //STENDAL_MAGDEBURG
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.STENDAL_MAGDEBURG_1));
        villages.add(new Village(VillagePositions.STENDAL_MAGDEBURG_2));
        villages.add(new Village(VillagePositions.STENDAL_MAGDEBURG_3));
        cities = new ICity[]{stendal, magdeburg};
        tavernPosition = TavernPositions.STENDAL_MAGDEBURG;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //GOSLAR_QUEDLINBURG
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.GOSLAR_QUEDLINBURG_1));
        villages.add(new Village(VillagePositions.GOSLAR_QUEDLINBURG_2));
        villages.add(new Village(VillagePositions.GOSLAR_QUEDLINBURG_3));
        villages.add(new Village(VillagePositions.GOSLAR_QUEDLINBURG_4));
        cities = new ICity[]{goslar, quedlinburg};
        tavernPosition = TavernPositions.GOSLAR_QUEDLINBURG;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //QUEDLINBURG_HALLE
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.QUEDLINBURG_HALLE_1));
        villages.add(new Village(VillagePositions.QUEDLINBURG_HALLE_2));
        villages.add(new Village(VillagePositions.QUEDLINBURG_HALLE_3));
        villages.add(new Village(VillagePositions.QUEDLINBURG_HALLE_4));
        cities = new ICity[]{quedlinburg, halle};
        tavernPosition = TavernPositions.QUEDLINBURG_HALLE;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));

        //GOTTINGEN_QUEDLINBURG
        villages = Lists.newArrayList();
        villages.add(new Village(VillagePositions.GOTTINGEN_QUEDLINBURG_1));
        villages.add(new Village(VillagePositions.GOTTINGEN_QUEDLINBURG_2));
        villages.add(new Village(VillagePositions.GOTTINGEN_QUEDLINBURG_3));
        cities = new ICity[]{gottingen, quedlinburg};
        tavernPosition = TavernPositions.GOTTINGEN_QUEDLINBURG;
        gameBoard.addRoute(new Route(villages, cities, tavernPosition));


        //Routes supplémentaires de la seconde map
        if (map == 2) {
            //EMDEN_STADE
            villages = Lists.newArrayList();
            villages.add(new Village(VillagePositions.EMDEN_STADE_1));
            villages.add(new Village(VillagePositions.EMDEN_STADE_2));
            villages.add(new Village(VillagePositions.EMDEN_STADE_3));
            cities = new ICity[]{emden, stade};
            tavernPosition = TavernPositions.EMDEN_STADE;
            gameBoard.addRoute(new Route(villages, cities, tavernPosition));

            //MARBURG_GOTTINGEN
            villages = Lists.newArrayList();
            villages.add(new Village(VillagePositions.MARBURG_GOTTINGEN_1));
            villages.add(new Village(VillagePositions.MARBURG_GOTTINGEN_2));
            villages.add(new Village(VillagePositions.MARBURG_GOTTINGEN_3));
            cities = new ICity[]{marburg, gottingen};
            tavernPosition = TavernPositions.MARBURG_GOTTINGEN;
            gameBoard.addRoute(new Route(villages, cities, tavernPosition));
        }


        return gameBoard;
    }
}
