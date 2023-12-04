import org.junit.jupiter.api.Test;
import puzzle2.GameInfo;
import puzzle2.Puzzle2;
import puzzle3.Puzzle3;

import java.io.File;
import java.io.FileNotFoundException;
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

}