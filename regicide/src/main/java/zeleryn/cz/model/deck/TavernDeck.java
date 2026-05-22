package zeleryn.cz.model.deck;

import zeleryn.cz.model.cards.Card;
import zeleryn.cz.model.cards.CardSuit;

import java.util.ArrayList;

public class TavernDeck extends CardDeck {

    @Override
    protected void setupCardsToDeck() {
        ArrayList<CardSuit> cardSuits = new ArrayList<>();

        cardSuits.add(CardSuit.CLUBS);
        cardSuits.add(CardSuit.DIAMONDS);
        cardSuits.add(CardSuit.HEARTHS);
        cardSuits.add(CardSuit.SPADES);

        Card cardToAdd;
        for (CardSuit cardSuit: cardSuits) {
            for (int i = 1; i <= 10; i++) {
                cardToAdd = new Card(i, cardSuit);
                cardDeck.add(cardToAdd);
            }
        }
    }

}
