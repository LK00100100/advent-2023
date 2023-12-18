package puzzle7;

import java.util.HashMap;
import java.util.Map;

public record HandBet(String hand, int bet) {

    /**
     * -1, this is less than the other.
     * 0 is equal.
     * 1, this is greater than the other.
     *
     * @return -
     */
    public int compareTo(HandBet other) {
        CamelHand myHand = this.getCamelHand();
        CamelHand otherHand = other.getCamelHand();

        int answer = Integer.compare(myHand.value, otherHand.value);

        if(answer != 0)
            return answer;

        //same type of hand. compare high cards from left to right
        for(int i = 0; i < this.hand().length(); i++) {
            char cardA = this.hand.charAt(i);
            char cardB = other.hand.charAt(i);

            int val = compareTo(cardA, cardB);

            if(val != 0)
                return val;
        }

        return 0;
    }

    public CamelHand getCamelHand() {
        Map<Character, Integer> counts = getCardCounts();

        if (isFiveOfAKind(counts))
            return CamelHand.FIVE_KIND;

        if (isFourOfAKind(counts))
            return CamelHand.FOUR_KIND;

        if (isFullHouse(counts))
            return CamelHand.FULL_HOUSE;

        if (isThreeOfAKind(counts))
            return CamelHand.THREE_KIND;

        if (isTwoPair(counts))
            return CamelHand.TWO_PAIR;

        if (isOnePair(counts))
            return CamelHand.ONE_PAIR;

        //assumed default is high card
        return CamelHand.HIGH_CARD;
    }

    private Map<Character, Integer> getCardCounts() {
        Map<Character, Integer> counts = new HashMap<>();

        for (int i = 0; i < this.hand().length(); i++) {
            char c = this.hand().charAt(i);

            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        return counts;
    }

    private boolean isFiveOfAKind(Map<Character, Integer> counts) {
        for (int count : counts.values()) {
            if (count == 5)
                return true;
        }

        return false;
    }

    private boolean isFourOfAKind(Map<Character, Integer> counts) {
        for (int count : counts.values()) {
            if (count == 4)
                return true;
        }

        return false;
    }

    private boolean isFullHouse(Map<Character, Integer> counts) {
        boolean hasPair = false;
        boolean hasTriple = false;

        for (int count : counts.values()) {
            if (count == 2)
                hasPair = true;

            if (count == 3)
                hasTriple = true;
        }

        return hasPair && hasTriple;
    }

    private boolean isThreeOfAKind(Map<Character, Integer> counts) {
        for (int count : counts.values()) {
            if (count == 3)
                return true;
        }

        return false;
    }

    private boolean isTwoPair(Map<Character, Integer> counts) {
        int pairCount = 0;
        for (int count : counts.values()) {
            if (count == 2)
                pairCount++;
        }

        return pairCount == 2;
    }

    private boolean isOnePair(Map<Character, Integer> counts) {
        boolean hasOnePair = false;
        for (int count : counts.values()) {
            if (count == 2 && hasOnePair)    //two pairs are bad
                return false;

            if (count == 2)
                hasOnePair = true;
        }

        return hasOnePair;

    }

    public static int compareTo(char cardA, char cardB) {
        //assumed length 5
        int cardAVal = getCardVal(cardA);
        int cardBVal = getCardVal(cardB);

        return Integer.compare(cardAVal, cardBVal);
    }

    //assumed valid input
    public static int getCardVal(char card) {
        if(Character.isDigit(card))
            return card - '2';  //2 is 2, 9 is 9

        switch(card) {
            case 'T':
                return 10;
            case 'J':
                return 11;
            case 'Q':
                return 12;
            case 'K':
                return 13;
            case 'A':
                return 14;
        }

        throw new IllegalArgumentException();
    }

}
