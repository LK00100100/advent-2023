package puzzle2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record GameInfo(int gameId, List<Map<String, Integer>> gameSets) {

    /**
     * is target game possible using the source?
     * @param target -
     * @param source -
     * @return true if possible. Otherwise, false
     */
    public static boolean isPossible(GameInfo target, Map<String, Integer> source) {
        //is this set possible?
        for(Map<String, Integer> gameSet : target.gameSets()) {
            for(Map.Entry<String, Integer> pair : gameSet.entrySet()) {
                //we're using too many balls
                if(source.getOrDefault(pair.getKey(), 0) < pair.getValue())
                    return false;
            }
        }

        return true;
    }

    /**
     * get the minimum number of cubes needed to play all the sets for the Game.
     * multiply all the numbers and add that.
     * See problem listing.
     * @return
     */
    public int getCubeOfCube() {
        Map<String, Integer> maxes = new HashMap<>();

        //get the max for each color
        for(Map<String, Integer> gameSet : gameSets) {
            for(Map.Entry<String, Integer> pair: gameSet.entrySet()) {
                //new max
                if(maxes.getOrDefault(pair.getKey(), 0) < pair.getValue()) {
                    maxes.put(pair.getKey(), pair.getValue());
                }
            }

        }

        //cube the cubes, then sum
        return maxes.values().stream()
                .reduce((a, b) -> a * b)
                .orElse(0);
    }

}
