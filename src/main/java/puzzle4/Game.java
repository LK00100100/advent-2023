package puzzle4;

import java.util.Set;

public record Game(int id, Set<Integer> numbers, Set<Integer> winningNumbers) {

    public int getPoints() {
        int numMatches = getNumMatches();

        if(numMatches == 0)
            return 0;

        return (int) Math.pow(2, numMatches - 1);
    }

    public int getNumMatches() {
        return (int) numbers.stream().filter(winningNumbers::contains).count();
    }

}
