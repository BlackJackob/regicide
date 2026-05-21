package zeleryn.cz.model.cards;

public class Card {
    protected int value;
    protected CardSuit cardSuit;

    public Card(int value, CardSuit cardSuit) {
        this.value = value;
        this.cardSuit = cardSuit;
    }

    public int getValue() {
        return value;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }
}
