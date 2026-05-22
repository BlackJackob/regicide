package zeleryn.cz.model.deck;

import zeleryn.cz.model.cards.Card;

import java.util.ArrayList;
import java.util.Collections;

public abstract class CardDeck {

    ArrayList<Card> cardDeck;

    protected CardDeck() {
        cardDeck = new ArrayList<>();
        setupCardsToDeck();
    }

    protected abstract void setupCardsToDeck();

    public void shuffle() {
        Collections.shuffle(cardDeck);
    }

    public void addToBottom(ArrayList<Card> cardsToAdd) {
        for(Card card : cardsToAdd) {
            cardDeck.add(card);
        }
    }

    public ArrayList<Card> drawCards(int numOfCardsToDraw) {
        ArrayList<Card> drawnCards = new ArrayList<>();
        Card drawnCard;
        for (int i = 0; i < numOfCardsToDraw; i++) {
            drawnCard = cardDeck.removeFirst();;
            drawnCards.add(drawnCard);
        }
        return  drawnCards;
    }

    public void addToTop(Card card) {
        cardDeck.add(0, card);
    }

    public void addToTop(ArrayList<Card> cardsToAdd) {
        for(Card card : cardsToAdd) {
            cardDeck.add(0, card);
        }
    }



}
