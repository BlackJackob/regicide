package zeleryn.cz.model.player;

import zeleryn.cz.model.player.commands.*;

import java.util.Objects;
import java.util.Scanner;

public class HumanPlayer extends Player {

    private final String playerWantsToPass = "pass";
    private final String playerWantsToConfirm = "confirm";
    private Scanner scaner;

    public HumanPlayer(int handLimit) {
        super(handLimit);
        scaner = new Scanner(System.in);
    }

    @Override
    protected PlayerCommand confirmOrChange() {
        return null;
    }

    @Override
    protected PlayerCommandSelect selectIndexOfCardToDiscard() {
        System.out.println("Which card would you like to discard?");

        this.printCardsInHand();

        String playerInput = scaner.nextLine();
        int selectedIndex = Integer.parseInt(playerInput);
        if(selectedIndex >= 0 && selectedIndex < cardsInHand.size()) {
            return new PlayerCommandSelect(selectedIndex);
        }

        return null;
    }

    @Override
    protected PlayerCommand selectCardToPlayOrPass() {
        System.out.println("Which card do you want to play.");
        System.out.printf("For passing write: '%s' %n", playerWantsToPass);
        System.out.printf("For confirming write: '%s' %n", playerWantsToConfirm);

        this.printCardsInHand();

        String playerInput = scaner.nextLine();

        if(Objects.equals(playerInput, playerWantsToPass)) {
            return  new PlayerCommandPass();
        }

        if(Objects.equals(playerInput, playerWantsToConfirm)){
            return new PlayerCommandConfirm();
        }

        int selectedIndex = Integer.parseInt(playerInput);
        if(selectedIndex >= 0 && selectedIndex < cardsInHand.size()) {
            return new PlayerCommandSelect(selectedIndex);
        }

        return new PlayerCommandInvalid();
    }




}
