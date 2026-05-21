package zeleryn.cz.model.player;

import java.util.ArrayList;

public class PlayerManager {

    private ArrayList<Player> players;

    private static PlayerManager instance;


    public static PlayerManager getInstance() {
        if(instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    public void playersDrawCards(int numOfCardsToDraw) {

    }
}
