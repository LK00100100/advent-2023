package puzzle7;

public enum CamelHand {
    FIVE_KIND(6),
    FOUR_KIND(5),
    FULL_HOUSE(4),
    THREE_KIND(3),
    TWO_PAIR(2),
    ONE_PAIR(1),
    HIGH_CARD(0);

    public final int value;

     CamelHand(int value) {
        this.value = value;
    }
}
