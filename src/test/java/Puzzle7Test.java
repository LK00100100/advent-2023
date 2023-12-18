import org.junit.jupiter.api.Test;
import puzzle7.HandBet;
import puzzle7.Puzzle7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle7Test {

    @Test
    void solvePartSample() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle7-sample.txt")).getFile());

        var handBets = Puzzle7.readTokens(inputFile);
        handBets.sort(HandBet::compareTo);

        //sum it up
        int total = 0;
        for (int rank = 1; rank <= handBets.size(); rank++) {
            total += rank * handBets.get(rank - 1).bet();
        }

        assertEquals(6440, total);
    }

    @Test
    void solvePart1() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle7.txt")).getFile());

        var handBets = Puzzle7.readTokens(inputFile);
        handBets.sort(HandBet::compareTo);

        //sum it up
        int total = 0;
        for (int rank = 1; rank <= handBets.size(); rank++) {
            total += rank * handBets.get(rank - 1).bet();
        }

        assertEquals(246795406, total);
    }

    @Test
    void solvePart2Sample() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle7-sample.txt")).getFile());

        var handBets = Puzzle7.readTokens(inputFile);
        handBets.sort(HandBet::compareToWithJoker);

        //sum it up
        int total = 0;
        for (int rank = 1; rank <= handBets.size(); rank++) {
            total += rank * handBets.get(rank - 1).bet();
        }

        assertEquals(5905, total);
    }

    @Test
    void solvePart2() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle7.txt")).getFile());

        var handBets = Puzzle7.readTokens(inputFile);
        handBets.sort(HandBet::compareToWithJoker);

        //sum it up
        int total = 0;
        for (int rank = 1; rank <= handBets.size(); rank++) {
            total += rank * handBets.get(rank - 1).bet();
        }

        assertEquals(249356515, total);
    }

}