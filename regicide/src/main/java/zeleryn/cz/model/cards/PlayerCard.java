package zeleryn.cz.model.cards;

public class PlayerCard extends Card {

    private PlayerCardState cardSelected;

    public PlayerCard(Card card) {
        super(card.getValue(), card.getCardSuit());
        cardSelected = PlayerCardState.DEFAULT;
    }

    public PlayerCardState getCardSelected() {
        return cardSelected;
    }

    public void setCardSelected(PlayerCardState cardSelected) {
        this.cardSelected = cardSelected;
    }
}
