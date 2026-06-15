package zeleryn.cz.model.cards;

import java.util.Objects;

public enum CardSuit {
    CLUBS ("Clubs"),
    DIAMONDS ("Diamonds"),
    SPADES ("Spades"),
    HEARTHS ("Hearths");

    private final String displayName;
    private String suitSymbol;


    CardSuit(String displayName) {
        this.displayName = displayName;
        determineSuitSymbol();
    }

    public String getDisplayName() {
        return displayName;
    }

    private void determineSuitSymbol() {
        if(Objects.equals(displayName, CLUBS.getDisplayName())) {
            suitSymbol = "♧";
        } else if (Objects.equals(displayName, DIAMONDS.getDisplayName())) {
            suitSymbol = "♢";
        } else if (Objects.equals(displayName, SPADES.getDisplayName())) {
            suitSymbol = "♤";
        } else if (Objects.equals(displayName, HEARTHS.getDisplayName())) {
            suitSymbol = "♡";
        }
    }

    public String getSuitSymbol() {
        return suitSymbol;
    }
}
