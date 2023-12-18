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

        if (answer != 0)
            return answer;

        //same type of hand. compare high cards from left to right
        for (int i = 0; i < this.hand().length(); i++) {
            char cardA = this.hand.charAt(i);
            char cardB = other.hand.charAt(i);

            int val = compareTo(cardA, cardB);

            if (val != 0)
                return val;
        }

        return 0;
    }

    public int compareToWithJoker(HandBet other) {
        CamelHand myHand = this.getCamelHandWithJoker();
        CamelHand otherHand = other.getCamelHandWithJoker();

        int answer = Integer.compare(myHand.value, otherHand.value);

        if (answer != 0)
            return answer;

        //same type of hand. compare high cards from left to right
        for (int i = 0; i < this.hand().length(); i++) {
            char cardA = this.hand.charAt(i);
            char cardB = other.hand.charAt(i);

            int val = compareToWithJoker(cardA, cardB);

            if (val != 0)
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


    public CamelHand getCamelHandWithJoker() {
        Map<Character, Integer> counts = getCardCounts();

        int numJokers = counts.getOrDefault('J', 0);
        counts.remove('J');

        if (isFiveOfAKindWithJoker(counts, numJokers))
            return CamelHand.FIVE_KIND;

        if (isFourOfAKindWithJoker(counts, numJokers))
            return CamelHand.FOUR_KIND;

        if (isFullHouseWithJoker(counts, numJokers))
            return CamelHand.FULL_HOUSE;

        if (isThreeOfAKindWithJoker(counts, numJokers))
            return CamelHand.THREE_KIND;

        if (isTwoPairWithJoker(counts, numJokers))
            return CamelHand.TWO_PAIR;

        if (isOnePairWithJoker(counts, numJokers))
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

    /*
     * methods below here.
     * counts do not include joker counts.
     */
    private boolean isFiveOfAKindWithJoker(Map<Character, Integer> counts, int numJokers) {
        if (numJokers >= 5)
            return true;

        for (int count : counts.values()) {
            if (count + numJokers >= 5)
                return true;
        }

        return false;
    }

    private boolean isFourOfAKindWithJoker(Map<Character, Integer> counts, int numJokers) {
        if (numJokers >= 4)
            return true;

        for (int count : counts.values()) {
            if (count + numJokers >= 4)
                return true;
        }

        return false;
    }

    private boolean isFullHouseWithJoker(Map<Character, Integer> counts, int numJokers) {
        if (numJokers >= 5)
            return true;

        boolean hasPair = false;
        boolean hasTriple = false;
        int pairCount = 0;

        for (int count : counts.values()) {
            if (count == 2) {
                hasPair = true;
                pairCount++;
            }

            if (count == 3)
                hasTriple = true;
        }

        //no jokers needed
        if (hasPair && hasTriple)
            return true;

        //we need jokers...
        if (hasTriple) {
            return numJokers >= 1;
        }

        if (hasPair) {
            if (pairCount == 2 && numJokers >= 1)
                return true;

            if (pairCount == 1 && numJokers >= 2)
                return true;
        }

        return false;
    }

    private boolean isThreeOfAKindWithJoker(Map<Character, Integer> counts, int numJokers) {
        if (numJokers >= 3)
            return true;

        for (int count : counts.values()) {
            if (count + numJokers >= 3)  //4 of a kind is ok.
                return true;
        }

        return false;
    }

    private boolean isTwoPairWithJoker(Map<Character, Integer> counts, int numJokers) {
        if (numJokers >= 4)
            return true;

        int pairCount = 0;
        for (int count : counts.values()) {
            if (count == 2)
                pairCount++;
        }

        if (pairCount == 2)
            return true;

        if (pairCount == 1 && numJokers >= 1)
            return true;

        return false;
    }

    //at least one pair (JJJ is ok)
    private boolean isOnePairWithJoker(Map<Character, Integer> counts, int numJokers) {
        if (numJokers >= 1)
            return true;

        for (int count : counts.values()) {
            if (count == 2)    //two pairs are ok
                return true;
        }

        return false;
    }

    public static int compareTo(char cardA, char cardB) {
        //assumed length 5
        int cardAVal = getCardVal(cardA);
        int cardBVal = getCardVal(cardB);

        return Integer.compare(cardAVal, cardBVal);
    }

    public static int compareToWithJoker(char cardA, char cardB) {
        //assumed length 5
        int cardAVal = getCardValWithJoker(cardA);
        int cardBVal = getCardValWithJoker(cardB);

        return Integer.compare(cardAVal, cardBVal);
    }

    //assumed valid input
    public static int getCardVal(char card) {
        if (Character.isDigit(card))
            return card - '2';  //2 is 2, 9 is 9

        switch (card) {
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

    //assumed valid input
    public static int getCardValWithJoker(char card) {
        if (Character.isDigit(card))
            return card - '2';  //2 is 2, 9 is 9

        switch (card) {
            case 'T':
                return 10;
            case 'J':
                return -1;
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
