package zeleryn.cz.model;

import zeleryn.cz.model.cards.Card;
import zeleryn.cz.model.player.HumanPlayer;
import zeleryn.cz.model.player.Player;
import zeleryn.cz.model.player.PlayerManager;

import java.util.ArrayList;

public class GameEngine {

    private GameBoard gameBoard;
    private PlayerManager playerManager;
    private boolean gameHasFinished;
    private boolean playerWon;


    public GameEngine() {
        this.gameBoard = new GameBoard();
        this.playerManager = PlayerManager.getInstance();
        gameHasFinished = false;
        playerWon = false;
    }

    public void playGame() {
        setupPlayers();
        while (gameHasFinished == false) {

            // Check if won
            if(gameBoard.getTavernDeck().getCardsAmmount() == 0 && gameBoard.getCurrentRoyal().getHP() >= 0) {
                gameHasFinished = true;
                playerWon = true;
            }

            // Check if current player can at least survive
            if(!playerManager.canPlayerSurvive(gameBoard.getCurrentRoyal().getDamage())) {
                gameHasFinished = true;
                playerWon = false;
            }

            playerManager.currentPlayerSelectsCardsToPlay();
            if(!playerCardSelectionValid()) {
                // Handle invalid player selection
                continue;
            }

            playTurn();

        }
    }

    private boolean playerCardSelectionValid() {
        return true;
    }

    private void playTurn() {
        ArrayList<Card> selectedCardsToPlay = playerManager.getCurrentPlayersSelectedCards();
        gameBoard.PlayCards(selectedCardsToPlay);
        playerManager.discardCurrentPlayersSelectedCards();
        if(!playerManager.canPlayerSurvive(gameBoard.getCurrentRoyal().getDamage())) {
            gameHasFinished = true;
            playerWon = false;
        }

        playerManager.currentPlayerTakeDamage(gameBoard.getCurrentRoyal().getDamage());
        playerManager.discardCurrentPlayersSelectedCards();

    }

    private void setupPlayers() {
        HumanPlayer humanPlayer = new HumanPlayer(8);
        ArrayList<Player> currentPlayers = new ArrayList<>();
        currentPlayers.add(humanPlayer);
        playerManager.setPlayers(currentPlayers);
    }

}
