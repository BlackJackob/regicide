package zeleryn.cz.model.deck;

import zeleryn.cz.model.cards.Card;
import zeleryn.cz.model.cards.CardRoyal;
import zeleryn.cz.model.cards.CardSuit;

import java.util.ArrayList;

public class RoyalDeck extends CardDeck {
    @Override
    protected void setupCardsToDeck() {
        ArrayList<CardSuit> cardSuits = new ArrayList<>();

        cardSuits.add(CardSuit.CLUBS);
        cardSuits.add(CardSuit.DIAMONDS);
        cardSuits.add(CardSuit.HEARTHS);
        cardSuits.add(CardSuit.SPADES);

        ArrayList<Integer> damageOfRoyals = new ArrayList<>();

        damageOfRoyals.add(10);
        damageOfRoyals.add(15);
        damageOfRoyals.add(20);

        CardRoyal cardToAdd;
        for (int i = 0; i < 3; i++) {
            for (CardSuit cardSuit: cardSuits) {
                int value = damageOfRoyals.get(i);
                cardToAdd = new CardRoyal(value, cardSuit, (value * 2), value);
                cardDeck.add(cardToAdd);
            }
        }

    }
}
