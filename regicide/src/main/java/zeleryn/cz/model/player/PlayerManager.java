package zeleryn.cz.model.player;

import zeleryn.cz.model.cards.Card;
import zeleryn.cz.model.cards.PlayerCard;

import java.util.ArrayList;

public class PlayerManager {

    private ArrayList<Player> players;

    private int currentPlayerIndex;

    private static PlayerManager instance;


    public static PlayerManager getInstance() {
        if(instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    public boolean canPlayerSurvive(int damageToTank) {
        Player currentPlayer = players.get(currentPlayerIndex);
        ArrayList<PlayerCard> cards = currentPlayer.cardsInHand;
        int valueInHand = 0;
        for(Card card : cards) {
            valueInHand += card.getValue();
        }

        return valueInHand >= damageToTank;
    }

    public void currentPlayerTakeDamage(int damageToTank) {
        players.get(currentPlayerIndex);
    }

    public ArrayList<Card> currentPlayerPlayCards() {
        players.get(currentPlayerIndex).playCards();
        resetPassing();

    }

    // todo: propably a better way to do this
    public ArrayList<Card> distributeCardsAmongPlayers(ArrayList<Card> cardsToDistribute) {
        ArrayList<Card> leftoverCards = new ArrayList<>();

        Player currentPlayer = players.get(currentPlayerIndex);

        int playerWhoBegunDrawing = currentPlayerIndex;
        boolean allPlayersFullHand = true;
        while (!cardsToDistribute.isEmpty()) {
            if(!currentPlayer.isHandFull()) {
                currentPlayer.recieveCard(cardsToDistribute.removeFirst());
                allPlayersFullHand = false;
            }
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            currentPlayer =  players.get(currentPlayerIndex);

            if(currentPlayerIndex == playerWhoBegunDrawing) {
                if(allPlayersFullHand) {
                    break;
                }
                allPlayersFullHand = true;
            }
        }

        if(!cardsToDistribute.isEmpty()) {
            leftoverCards.addAll(cardsToDistribute);
        }

        return leftoverCards;
    }

    private void resetPassing() {
        for (Player player : players) {
            player.hasPassed = false;
        }
    }

    public boolean currentPlayerCanPass() {
        Player currentPlayer = players.get(currentPlayerIndex);
        boolean canPass = false;
        for (Player player : players) {
            if(player != currentPlayer) {
                if(!player.hasPassed) {
                    canPass = true;
                    break;
                }
            }
        }
        return canPass;
    }
}
