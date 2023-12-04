package puzzle2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.util.Map.entry;

public class Puzzle2 {

    //dev note: colors could be an enum

    /**
    /**
     * @param file assume valid
     * @return list of tokens
     */
    public static List<GameInfo> readTokens(File file) throws FileNotFoundException {
        List<GameInfo> games = new ArrayList<>();

        Scanner in = new Scanner(file);

        //Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        while (in.hasNextLine()) {
            String token = in.nextLine();

            if (token.isEmpty()) {
                continue;
            }

            //parse game id
            int gameIdx = token.indexOf(" ");
            int colonIdx = token.indexOf(":");
            int gameId = Integer.parseInt(token.substring(gameIdx + 1, colonIdx));

            //parse game sets
            List<Map<String, Integer>> gameSets = new ArrayList<>();

            String[] gameSetStr = token.substring(colonIdx + 1).split(";");
            for(String gameSet : gameSetStr) {
                gameSet = gameSet.strip();
                Map<String, Integer> ballCount = new HashMap<>();

                //such as: 3 blue, 4 red
                String[] balls = gameSet.strip().split(",");
                for(String ball : balls) {
                    ball = ball.strip();
                    int spaceIdx = ball.indexOf(" ");

                    String color = ball.substring(spaceIdx).strip();
                    int amount = Integer.parseInt(ball.substring(0, spaceIdx));
                    ballCount.put(color, amount);
                }

                gameSets.add(ballCount);
            }

            var gameInfo = new GameInfo(gameId, gameSets);

            games.add(gameInfo);
        }

        return games;
    }

}
