package zeleryn.cz.model;

import zeleryn.cz.model.player.PlayerManager;

public class GameEngine {

    GameBoard gameBoard;
    PlayerManager playerManager;

    public GameEngine() {
        this.gameBoard = new GameBoard();
        this.playerManager = PlayerManager.getInstance();
    }

    public void playGame() {
        boolean gameHasFinished = false;
        while (gameHasFinished = false) {

        }
    }

}
