import org.junit.jupiter.api.Test;
import puzzle5.GardenMap;
import puzzle6.Puzzle6;
import puzzle6.RaceInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle6Test {

    @Test
    void solvePartSample() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle6-sample.txt")).getFile());

        var races = Puzzle6.readTokens(inputFile);

        long answer = races.stream().map(RaceInfo::numWaysToBeat)
                .reduce((a, b) -> a * b)
                .orElse(0L);

        assertEquals(288, answer);
    }

    @Test
    void solvePart1() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle6.txt")).getFile());

        var races = Puzzle6.readTokens(inputFile);

        long answer = races.stream().map(RaceInfo::numWaysToBeat)
                .reduce((a, b) -> a * b)
                .orElse(0L);

        assertEquals(440000, answer);
    }

    @Test
    void solvePart2() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle6-2.txt")).getFile());

        var races = Puzzle6.readTokens(inputFile);

        long answer = races.stream().map(RaceInfo::numWaysToBeat)
                .reduce((a, b) -> a * b)
                .orElse(0L);

        assertEquals(26187338, answer);
    }

}