import org.junit.jupiter.api.Test;
import puzzle2.GameInfo;
import puzzle2.Puzzle2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle2Test {

    @Test
    void solvePartSample() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle2-sample.txt")).getFile());

        List<GameInfo> games = Puzzle2.readTokens(inputFile);

        Map<String, Integer> source = Map.ofEntries(
                entry("red", 12),
                entry("green", 13),
                entry("blue", 14)
        );

        int sum = 0;

        //are all games possible?
        for (GameInfo info : games) {
            boolean isPossible = GameInfo.isPossible(info, source);

            if(isPossible) {
                sum += info.gameId();
            }
        }

        assertEquals(8, sum);
    }

    @Test
    void solvePart1() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle2.txt")).getFile());

        List<GameInfo> games = Puzzle2.readTokens(inputFile);

        Map<String, Integer> source = Map.ofEntries(
                entry("red", 12),
                entry("green", 13),
                entry("blue", 14)
        );

        int sum = 0;

        //are all games possible?
        for (GameInfo info : games) {
            boolean isPossible = GameInfo.isPossible(info, source);

            if(isPossible) {
                sum += info.gameId();
            }
        }

        assertEquals(2683, sum);
    }

    @Test
    void solvePart2() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle2.txt")).getFile());

        List<GameInfo> games = Puzzle2.readTokens(inputFile);

        int sum = games.stream()
                .map(GameInfo::getCubeOfCube)
                .reduce(Integer::sum)
                .orElse(0);

        assertEquals(49710, sum);
    }

}