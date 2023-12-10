import org.junit.jupiter.api.Test;
import puzzle3.Puzzle3;
import puzzle4.Game;
import puzzle4.Puzzle4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle4Test {

    @Test
    void solvePartSample() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle4-sample.txt")).getFile());

        List<Game> games = Puzzle4.readTokens(inputFile);

        System.out.println(games);

        assertEquals(8, games.get(0).getPoints());
        assertEquals(2, games.get(1).getPoints());
        assertEquals(0, games.get(4).getPoints());
    }

    @Test
    void solvePart1() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle4.txt")).getFile());

        List<Game> games = Puzzle4.readTokens(inputFile);

        int total = games.stream().map(Game::getPoints).reduce(Integer::sum).orElse(0);

        assertEquals(28538, total);
    }

    @Test
    void solvePart2Sample() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle4-sample.txt")).getFile());

        List<Game> games = Puzzle4.readTokens(inputFile);

        Map<Integer, Integer> copyCount = new HashMap<>();
        for(Game game : games) {
            int numCopies = copyCount.getOrDefault(game.id(), 0);

            //make copies on winnings
            for(int copyId = game.id() + 1; copyId <= game.id() + game.getNumMatches(); copyId++) {
                copyCount.put(copyId, copyCount.getOrDefault(copyId,  0) + 1 + numCopies);
            }
        }

        int totalCopies = copyCount.values().stream().reduce(Integer::sum).orElse(0);
        assertEquals(30, games.size() + totalCopies);
    }

    @Test
    void solvePart2() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle4.txt")).getFile());

        List<Game> games = Puzzle4.readTokens(inputFile);

        //calculate copies per game
        Map<Integer, Integer> copyCount = new HashMap<>();
        for(Game game : games) {
            int numCopies = copyCount.getOrDefault(game.id(), 0);

            //make copies on winnings
            for(int copyId = game.id() + 1; copyId <= game.id() + game.getNumMatches(); copyId++) {
                copyCount.put(copyId, copyCount.getOrDefault(copyId,  0) + 1 + numCopies);
            }
        }

        int totalCopies = copyCount.values().stream().reduce(Integer::sum).orElse(0);
        assertEquals(9425061, games.size() + totalCopies);
    }

}