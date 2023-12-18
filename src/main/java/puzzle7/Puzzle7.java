package puzzle7;

import puzzle6.RaceInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Puzzle7 {

    /**
     * @param file assume valid
     * @return -
     */
    public static List<HandBet> readTokens(File file) throws FileNotFoundException {
        List<HandBet> handBets = new ArrayList<>();

        Scanner in = new Scanner(file);

        //32T3K 765
        //T55J5 684
        while (in.hasNextLine()) {
            String line = in.nextLine();

            if (line.isEmpty()) {
                continue;
            }

            String[] handBetArr = line.split(" ");
            String hand = handBetArr[0];
            int bet = Integer.parseInt(handBetArr[1]);

            handBets.add(new HandBet(hand, bet));
        }

        return handBets;
    }

}
