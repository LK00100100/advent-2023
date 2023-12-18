package puzzle6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Puzzle6 {

    /**
     * @param file assume valid
     * @return -
     */
    public static List<RaceInfo> readTokens(File file) throws FileNotFoundException {
        List<RaceInfo> races = new ArrayList<>();

        Scanner in = new Scanner(file);

        //Time:      7  15   30
        //Distance:  9  40  200
        String timeLine = in.nextLine();
        timeLine = timeLine.replaceAll(" +", " ");
        timeLine = timeLine.substring(timeLine.indexOf(":") + 2);

        String distanceLine = in.nextLine();
        distanceLine = distanceLine.replaceAll(" +", " ");
        distanceLine = distanceLine.substring(distanceLine.indexOf(":") + 2);

        String[] timesArr = timeLine.split(" ");
        String[] distanceArr = distanceLine.split(" ");

        //assumed same length
        for(int i = 0; i < timesArr.length; i++) {
            long time = Long.parseLong(timesArr[i]);
            long distance = Long.parseLong(distanceArr[i]);

            races.add(new RaceInfo(time, distance));
        }

        return races;
    }

}
