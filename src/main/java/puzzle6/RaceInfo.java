package puzzle6;

/**
 * @param timeLimit milliseconds
 * @param record millimeters
 */
public record RaceInfo(int timeLimit, int record) {

    //to hold or not to hold [the button]
    public int numWaysToBeat() {

        int winCount = 0;
        for(int holdSeconds = 0; holdSeconds < timeLimit; holdSeconds++) {
            int remainingTime = timeLimit - holdSeconds;
            int distanceMoved = holdSeconds * remainingTime;

            if(distanceMoved > record)
                winCount++;
        }

        return winCount;
    }
}
