package zeleryn.cz.model;

import zeleryn.cz.model.cards.Card;
import zeleryn.cz.model.cards.CardRoyal;
import zeleryn.cz.model.cards.CardSuit;
import zeleryn.cz.model.deck.DiscardDeck;
import zeleryn.cz.model.deck.RoyalDeck;
import zeleryn.cz.model.deck.TavernDeck;
import zeleryn.cz.model.player.PlayerManager;

import java.util.ArrayList;

public class GameBoard {

    private TavernDeck tavernDeck;
    private DiscardDeck discardDeck;
    private RoyalDeck royalDeck;
    private CardRoyal currentRoyal;

    public GameBoard() {
        tavernDeck = new TavernDeck();
        discardDeck = new DiscardDeck();
        royalDeck = new RoyalDeck();
        // todo: later replace with method that just draws one card
        currentRoyal = (CardRoyal) tavernDeck.drawCards(1).getFirst();
    }

    public void PlayCards(ArrayList<Card> cardsPlayed) {
        if(cardsPlayed.size() > 1) {
            evaluateCombo(cardsPlayed);
            return;
        }

        Card cardPlayed = cardsPlayed.getFirst();
        evaluateCard(cardPlayed);

        if(currentRoyal.getHP() <= 0 ) {
            enemyDefeated();
        }
    }

    private void evaluateCombo(ArrayList<Card> cardsPlayed) {
        int comboValue = calcValueOfCombo(cardsPlayed);

        currentRoyal.takeDamage(comboValue);
        for (Card card: cardsPlayed) {
            applySuitPower(card, comboValue);
        }

    }

    private int calcValueOfCombo(ArrayList<Card> cardsPlayed) {
        int value = 0;
        for(Card card : cardsPlayed) {
            value += card.getValue();
        }
        return value;
    }

    private void evaluateCard(Card card) {

        int value = card.getValue();

        // Apply damage
        currentRoyal.takeDamage(value);

        applySuitPower(card, 0);

    }

    private void applySuitPower(Card card, int comboValue) {
        int value = Math.max(card.getValue(), comboValue);

        if(card.getCardSuit() == currentRoyal.getCardSuit()) {
            return;
        }

        // todo: maybe a better concept but I digres
        if(card.getCardSuit() == CardSuit.CLUBS) {
            currentRoyal.takeDamage(value);
        } else if (card.getCardSuit() == CardSuit.SPADES) {
            currentRoyal.lowerDamage(value);
        } else if (card.getCardSuit() == CardSuit.HEARTHS) {
            healCards(value);
        } else if (card.getCardSuit() == CardSuit.DIAMONDS) {
            drawCards(value);
        }
    }

    private void healCards(int numberOfCardsToHeal) {
        discardDeck.shuffle();
        ArrayList<Card> healedCards = discardDeck.drawCards(numberOfCardsToHeal);
        tavernDeck.addToBottom(healedCards);
    }

    private void drawCards(int numberOfCardsToDraw) {
        ArrayList<Card> cardsToDistribute = tavernDeck.drawCards(numberOfCardsToDraw);
        ArrayList<Card> leftovers = PlayerManager.getInstance().distributeCardsAmongPlayers(cardsToDistribute);
        tavernDeck.addToTop(leftovers);
    }

    private void enemyDefeated() {
        if(currentRoyal.getHP() == 0) {
            tavernDeck.addToTop(currentRoyal);
        } else {
            discardDeck.addToTop(currentRoyal);
        }
        // todo: propably add method to draw a single card
        currentRoyal = (CardRoyal) royalDeck.drawCards(1).getFirst();
    }

    public TavernDeck getTavernDeck() {
        return tavernDeck;
    }

    public CardRoyal getCurrentRoyal() {
        return currentRoyal;
    }
}
