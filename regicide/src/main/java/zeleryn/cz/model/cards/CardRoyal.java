package zeleryn.cz.model.cards;

public class CardRoyal extends Card {

    private int HP;
    private int damage;

    public CardRoyal(int value, CardSuit cardSuit, int HP, int damage) {
        super(value, cardSuit);
        this.HP = HP;
        this.damage = damage;
    }

    // todo: consider wheter it is better to pass in here a card and have here the else if branch of playing spades and clubs or if its better
    // todo: to have all the logic in one place in the GameBoard
    public void takeDamage(int damage) {
        this.HP -= damage;
    }

    public void lowerDamage(int spadeValue) {
        this.damage -= spadeValue;
    }

    public int getDamage() {
        return damage;
    }

    public int getHP() {
        return HP;
    }
}
