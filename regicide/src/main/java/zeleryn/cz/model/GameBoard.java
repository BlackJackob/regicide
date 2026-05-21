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



    public void PlayCards(ArrayList<Card> cardsPlayed) {
        // todo: propably make two functions with diferent arguments
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

        if(card.getCardSuit() == CardSuit.CLUBS) {
            currentRoyal.takeDamage(value);
        } else if (card.getCardSuit() == CardSuit.SPADES) {
            currentRoyal.lowerDamage(value);
        } else if (card.getCardSuit() == CardSuit.HEARTHS) {
            ArrayList<Card> healedCards = discardDeck.drawCards(value);
            tavernDeck.addToBottom(healedCards);
        } else if (card.getCardSuit() == CardSuit.DIAMONDS) {
            PlayerManager.getInstance().playersDrawCards(value);
        }
    }

    // todo: propably add method to draw a single card
    private void enemyDefeated() {
        if(currentRoyal.getHP() == 0) {
            tavernDeck.addToTop(currentRoyal);
        }
        currentRoyal = (CardRoyal) royalDeck.drawCards(1).getFirst();
    }
}
