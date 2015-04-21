package fr.univ_rouen.hansa.save;

import com.google.gson.Gson;

public class LoadManager {
    private static LoadManager ourInstance = new LoadManager();

    private final Gson gson;

    public static LoadManager getInstance() {
        return ourInstance;
    }

    private LoadManager() {
        gson = new Gson();
    }

    //TODO loading of a save

    // Important for loading :
    //   1. Load all players in the turnManager
    //   2. Make the gameBoard (need to create new methodes)
}
