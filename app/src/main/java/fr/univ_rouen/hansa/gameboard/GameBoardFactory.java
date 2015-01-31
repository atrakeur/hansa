package fr.univ_rouen.hansa.gameboard;

import com.google.common.collect.Lists;

import java.util.List;

import fr.univ_rouen.hansa.gameboard.cities.City;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.cities.Kontor;
import fr.univ_rouen.hansa.gameboard.cities.Power;
import fr.univ_rouen.hansa.gameboard.pawns.Merchant;
import fr.univ_rouen.hansa.gameboard.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.pawns.Trader;
import fr.univ_rouen.hansa.util.CityPositions;

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
    private GameBoard createGameBoard(int map) {
        //TODO routes supplémentaire pour la seconde map
        
        GameBoard gameBoard = new GameBoard();
        List<IKontor<? extends Pawn>> kontors;

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

        //BREMEN
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(false, Privillegium.Pink));
        ICity bremen = new City(CityPositions.BREMEN, Power.Null, kontors);
        gameBoard.addCity(bremen);

        //STADE
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Merchant>(true, Privillegium.White));
        ICity stade = new City(CityPositions.STADE, Power.Privillegium, kontors);
        gameBoard.addCity(stade);

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

        //KAMPEN
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(false, Privillegium.Orange));
        kontors.add(new Kontor<Trader>(false, Privillegium.Black));
        ICity kampen = new City(CityPositions.KAMPEN, Power.Null, kontors);
        gameBoard.addCity(kampen);

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

        //LUNEBURG
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Merchant>(false, Privillegium.White));
        ICity luneburg = new City(CityPositions.LUNEBURG, Power.Null, kontors);
        gameBoard.addCity(luneburg);

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

        //DORTMUND
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Merchant>(false, Privillegium.White));
        kontors.add(new Kontor<Trader>(false, Privillegium.Orange));
        ICity dortmund = new City(CityPositions.DORTMUND, Power.Null, kontors);
        gameBoard.addCity(dortmund);

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

        //GOSLAR
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(false, Privillegium.White));
        ICity goslar = new City(CityPositions.GOSLAR, Power.Null, kontors);
        gameBoard.addCity(goslar);

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

        //MARBURG
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(true, Privillegium.Orange));
        kontors.add(new Kontor<Trader>(false, Privillegium.Pink));
        ICity marburg = new City(CityPositions.MARBURG, Power.Null, kontors);
        gameBoard.addCity(marburg);

        //GOTTINGEN
        kontors = Lists.newArrayList();
        kontors.add(new Kontor<Trader>(false, Privillegium.White));
        kontors.add(new Kontor<Trader>(false, Privillegium.Orange));
        ICity gottingen = new City(CityPositions.GOTTINGEN, Power.Actiones, kontors);
        gameBoard.addCity(gottingen);

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

        // ------------------------------------ //
        // -------------- ROUTES -------------- //
        // ------------------------------------ //

        //TODO

        return gameBoard;
    }
}
