package zeleryn.cz.model.player;

import zeleryn.cz.model.cards.Card;
import zeleryn.cz.model.cards.PlayerCard;
import zeleryn.cz.model.cards.PlayerCardState;

import java.util.ArrayList;

public abstract class Player {

    protected ArrayList<PlayerCard> cardsInHand;
    protected int handLimit;
    protected boolean hasPassed;

    public Player(int handLimit) {
        cardsInHand = new ArrayList<>();
        this.handLimit = handLimit;
        hasPassed = false;
    }

    public void playCards() {

        boolean playerHasPassedOrConfirmed = false;
        while (!playerHasPassedOrConfirmed) {
            PlayerCommand playerCommand = selectCardToPlayOrPass();
            if (playerCommand instanceof PlayerCommandPass && PlayerManager.getInstance().currentPlayerCanPass()) {
                playerHasPassedOrConfirmed = true;
                continue;
            }

            if (playerCommand instanceof PlayerCommandSelect) {
                handleCardSelection((PlayerCommandSelect) playerCommand);
            }

            if (playerCommand instanceof PlayerCommandConfirm) {
                playerHasPassedOrConfirmed = true;
                continue;
            }
        }
    }

    public void takeDamage(int damageToTake) {

        boolean hasConfirmedDiscard = false;
        int indexOfCardToChange;
        int indexOfCardToDiscard;
        PlayerCommand confirmOrChange;
        while (!hasConfirmedDiscard) {

            if(getSelectedCardsValue() < damageToTake) {
                indexOfCardToDiscard = selectIndexOfCardToDiscard().getSelectIndex();
                cardsInHand.get(indexOfCardToDiscard).setCardSelected(PlayerCardState.SELECTED);
                continue;
            }

            confirmOrChange = confirmOrChange();
            if(confirmOrChange instanceof PlayerCommandConfirm) {
                hasConfirmedDiscard = true;
            } else if (confirmOrChange instanceof  PlayerCommandSelect) {
                indexOfCardToChange = selectIndexOfCardToDiscard().getSelectIndex();
                cardsInHand.get(indexOfCardToChange).setCardSelected(PlayerCardState.DEFAULT);
            }
        }
    }
    private int getSelectedCardsCount() {
        int count = 0;
        for (PlayerCard card : cardsInHand) {
            if(card.getCardSelected() == PlayerCardState.SELECTED) {
                count += 1;
            }
        }
        return count;
    }

    private int getSelectedCardsValue() {
        int value = 0;
        for (PlayerCard card : cardsInHand) {
            if(card.getCardSelected() == PlayerCardState.SELECTED) {
                value += card.getValue();
            }
        }
        return value;
    }

    protected abstract PlayerCommand confirmOrChange();

    protected abstract PlayerCommandSelect selectIndexOfCardToDiscard();

    protected abstract PlayerCommand selectCardToPlayOrPass();


    public boolean isHandFull() {
        return cardsInHand.size() == handLimit;
    }

    public void recieveCard(Card cardToRecieve) {
        if(isHandFull()) {
            return;
        }

        PlayerCard playerCard = new PlayerCard(cardToRecieve);
        cardsInHand.add(playerCard);
    }

    private void handleCardSelection(PlayerCommandSelect playerCommandSelect) {
        int selectCardIndex = playerCommandSelect.getSelectIndex();
        if(getSelectedCardsCount() == 0) {
            cardsInHand.get(selectCardIndex).setCardSelected(PlayerCardState.SELECTED);
        }
        else if(cardsInHand.get(selectCardIndex).getCardSelected() == PlayerCardState.CAN_BE_PLAYED) {
            cardsInHand.get(selectCardIndex).setCardSelected(PlayerCardState.SELECTED);
        }

        determineWheterOtherCardsCanBePlayed();
    }

    private void determineWheterOtherCardsCanBePlayed() {
        for(PlayerCard playerCard : cardsInHand) {
            if(playerCard.getValue() == 1) {
                handleAce(playerCard);
            }

            if(playerCard.getValue() <= 5 ) {
                handleCombo(playerCard);
            }
        }
    }

    private void handleAce(PlayerCard playerCard) {
        if(getSelectedCardsCount() == 1) {
            playerCard.setCardSelected(PlayerCardState.CAN_BE_PLAYED);
        }
    }

    private void handleCombo(PlayerCard playerCard) {
        if(getSelectedCardsValue() <= 10) {
            playerCard.setCardSelected(PlayerCardState.CAN_BE_PLAYED);
        }
    }

}
