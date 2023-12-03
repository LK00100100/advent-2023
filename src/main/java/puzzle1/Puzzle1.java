package puzzle1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Map.entry;

public class Puzzle1 {

    /**
     * Returns the first and second digit of the token.
     * One digit is repeated twice.
     * 1asd3a = 13
     * ab7ba = 77
     *
     * @param token assume has 1 - 2 digits.
     * @return list of numbers.
     */
    public static Integer parseNumber(String token) {
        int leftDigit = -1;
        int rightDigit = -1;
        for (int i = 0; i < token.length(); i++) {
            char c = token.charAt(i);
            if (Character.isDigit(c)) {
                if (leftDigit == -1) {
                    leftDigit = c - '0';
                    rightDigit = c - '0';
                } else {
                    rightDigit = c - '0';
                }

            }
        }

        return (leftDigit * 10) + rightDigit;
    }

    //token -> digit value
    final static Map<String, Integer> tokenToDigit = Map.ofEntries(
            entry("zero", 0),
            entry("one", 1),
            entry("two", 2),
            entry("three", 3),
            entry("four", 4),
            entry("five", 5),
            entry("six", 6),
            entry("seven", 7),
            entry("eight", 8),
            entry("nine", 9),
            entry("0", 0),
            entry("1", 1),
            entry("2", 2),
            entry("3", 3),
            entry("4", 4),
            entry("5", 5),
            entry("6", 6),
            entry("7", 7),
            entry("8", 8),
            entry("9", 9)
    );

    record WordTuple(String word, int index) { }

    /**
     * assumed case-insensitive
     * one4two = 12
     * sixteen5one = 61
     *
     * @param token token with digits-as-words
     * @return number
     */
    public static Integer parseWordNumberForReal(String token) {
        //dev note: can probably do this in one pass but too much work.

        String leftWord = tokenToDigit.keySet().stream()
                .map(word -> new WordTuple(word, token.indexOf(word)))
                .filter(tuple -> tuple.index >= 0)
                .reduce((tupleA, tupleB) -> tupleA.index < tupleB.index ? tupleA : tupleB)
                .map(tuple -> tuple.word)
                .orElseThrow(() -> new IllegalArgumentException("bad token: " + token));

        String rightWord = tokenToDigit.keySet().stream()
                .map(word -> new WordTuple(word, token.lastIndexOf(word)))
                .filter(tuple -> tuple.index >= 0)
                .reduce((tupleA, tupleB) -> tupleA.index > tupleB.index ? tupleA : tupleB)
                .map(tuple -> tuple.word)
                .orElseThrow(() -> new IllegalArgumentException("bad token: " + token));

        int leftDigit = tokenToDigit.get(leftWord);
        int rightDigit = tokenToDigit.get(rightWord);

        return (leftDigit * 10) + rightDigit;
    }

    /**
     * @param file assume valid
     * @return list of tokens
     */
    public static List<String> readTokens(File file) throws FileNotFoundException {
        List<String> tokens = new ArrayList<>();

        Scanner in = new Scanner(file);

        while (in.hasNextLine()) {
            String token = in.nextLine();

            if (token.isEmpty()) {
                continue;
            }

            tokens.add(token);
        }

        return tokens;
    }
}
