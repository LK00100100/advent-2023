import org.junit.jupiter.api.Test;
import puzzle8.Puzzle8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle8Test {

    @Test
    void solvePartSample() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle8-sample.txt")).getFile());

        var network = Puzzle8.readTokens(inputFile);

        assertEquals(6, network.countStepsFromAAAToZZZ());
    }

    @Test
    void solvePart1() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle8.txt")).getFile());

        var network = Puzzle8.readTokens(inputFile);

        assertEquals(18673, network.countStepsFromAAAToZZZ());
    }

    @Test
    void solvePart2Sample() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle8-2-sample.txt")).getFile());

        var network = Puzzle8.readTokens(inputFile);

        assertEquals(6, network.getNumStepsFromEndingAtoAllEndingZ());
    }
    @Test
    void solvePart2() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle8.txt")).getFile());

        var network = Puzzle8.readTokens(inputFile);

        assertEquals(6, network.getNumStepsFromEndingAtoAllEndingZ());
    }

}