import org.junit.jupiter.api.Test;
import puzzle1.Puzzle1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class Puzzle1Test {

    @Test
    void solvePartSample() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle1-sample-input.txt")).getFile());

        List<String> tokens = Puzzle1.readTokens(inputFile);
        int sum = tokens.stream().map(Puzzle1::parseNumber).reduce(Integer::sum).orElse(0);

        assertEquals(142, sum);
    }

    @Test
    void solvePart1() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle1input.txt")).getFile());

        List<String> tokens = Puzzle1.readTokens(inputFile);
        int sum = tokens.stream().map(Puzzle1::parseNumber).reduce(Integer::sum).orElse(0);

        assertEquals(54877, sum);
    }

    @Test
    void solvePart2() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle1-2input.txt")).getFile());

        List<String> tokens = Puzzle1.readTokens(inputFile);
        int sum = tokens.stream().map(Puzzle1::parseWordNumberForReal).reduce(Integer::sum).orElse(0);

        assertEquals(54100, sum);
    }

}