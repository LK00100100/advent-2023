import org.junit.jupiter.api.Test;
import puzzle4.Game;
import puzzle4.Puzzle4;
import puzzle5.GardenMap;
import puzzle5.Puzzle5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle5Test {

    @Test
    void solvePartSample() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle5-sample.txt")).getFile());

        GardenMap gardenMap = Puzzle5.readTokens(inputFile);

        assertEquals("82", gardenMap.getSeedToLocation("79"));
        assertEquals("43", gardenMap.getSeedToLocation("14"));
        assertEquals("86", gardenMap.getSeedToLocation("55"));
        assertEquals("35", gardenMap.getSeedToLocation("13"));
    }

    @Test
    void solvePart1() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle5.txt")).getFile());

        GardenMap gardenMap = Puzzle5.readTokens(inputFile);

        long min = gardenMap.seeds().stream()
                .map(gardenMap::getSeedToLocation)
                .map(Long::parseLong)
                .min(Comparator.naturalOrder())
                .orElse(-1L);

        System.out.println(min);
        //assertEquals(35, gardenMap.getSeedToLocation(13));
    }

}