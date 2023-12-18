package puzzle6;

/**
 * @param timeLimit milliseconds
 * @param record millimeters
 */
public record RaceInfo(long timeLimit, long record) {

    //to hold or not to hold [the button]
    public long numWaysToBeat() {

        int winCount = 0;
        for(long holdSeconds = 0; holdSeconds < timeLimit; holdSeconds++) {
            long remainingTime = timeLimit - holdSeconds;
            long distanceMoved = holdSeconds * remainingTime;

            if(distanceMoved > record)
                winCount++;
        }

        return winCount;
    }
}
