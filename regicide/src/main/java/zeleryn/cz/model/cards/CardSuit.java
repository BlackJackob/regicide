package zeleryn.cz.model.cards;

public enum CardSuit {
    CLUBS ("Clubs"),
    DIAMONDS ("Diamonds"),
    SPADES ("Spades"),
    HEARTHS ("Hearths");

    private final String displayName;

    CardSuit(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
