import org.junit.jupiter.api.Test;
import puzzle2.GameInfo;
import puzzle2.Puzzle2;
import puzzle3.Puzzle3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle3Test {

    @Test
    void solvePartSample() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle3-sample.txt")).getFile());

        List<String> rows = Puzzle3.readTokens(inputFile);

        int sum = 0;
        for (int i = 0; i < rows.size(); i++) {
            sum += Puzzle3.getRowNumber(rows, i);
        }

        assertEquals(4361, sum);
    }

    @Test
    void solvePart1() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle3.txt")).getFile());

        List<String> rows = Puzzle3.readTokens(inputFile);

        int sum = 0;
        for (int i = 0; i < rows.size(); i++) {
            sum += Puzzle3.getRowNumber(rows, i);
        }

        assertEquals(540212, sum);
    }

    @Test
    void solvePart2Sample() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle3-sample.txt")).getFile());

        List<String> rows = Puzzle3.readTokens(inputFile);

        Map<String, List<Integer>> gearMap = new HashMap<>();

        //get gear ratios
        for (int i = 0; i < rows.size(); i++) {
            Puzzle3.parseGearNumber(rows, i, gearMap);
        }

        //sum only gears with 2 numbers
        int sum = gearMap.values().stream()
                .filter(list -> list.size() == 2)
                .map(list -> list.get(0) * list.get(1))
                .reduce(Integer::sum).orElse(0);
        System.out.println(sum);

        assertEquals(467835, sum);
    }

    @Test
    void solvePart2() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle3.txt")).getFile());

        List<String> rows = Puzzle3.readTokens(inputFile);

        Map<String, List<Integer>> gearMap = new HashMap<>();

        //get gear ratios
        for (int i = 0; i < rows.size(); i++) {
            Puzzle3.parseGearNumber(rows, i, gearMap);
        }

        //sum only gears with 2 numbers
        int sum = gearMap.values().stream()
                .filter(list -> list.size() == 2)
                .map(list -> list.get(0) * list.get(1))
                .reduce(Integer::sum).orElse(0);
        System.out.println(sum);

        assertEquals(87605697, sum);
    }

}