import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import puzzle8.Network;
import puzzle8.Puzzle8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle8Test {

    @Test
    void solvePartSample() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle8-sample.txt")).getFile());

        var network = Puzzle8.readTokens(inputFile);

        assertEquals(6, network.countStepsFromSourceToZZZ("AAA"));
    }

    @Test
    void solvePart1() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle8.txt")).getFile());

        var network = Puzzle8.readTokens(inputFile);

        assertEquals(18673, network.countStepsFromSourceToZZZ("AAA"));
    }

    @Test
    void solvePart2Sample() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle8-2-sample.txt")).getFile());

        var network = Puzzle8.readTokens(inputFile);

        assertEquals(6, network.getNumStepsFromEndingAtoAllEndingZ());
    }

    @Disabled("slow")
    @Test
    void solvePart2Slow() throws FileNotFoundException {
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle8.txt")).getFile());

        var network = Puzzle8.readTokens(inputFile);

        var answer = network.nodes().keySet().stream()
                .filter(nodeId -> nodeId.endsWith("A"))
                .map(network::countStepsFromSourceToZZZ)
                .reduce((a, b) -> a * b)
                .orElse(0);

        assertEquals(6, answer);
    }

    @Test
    void shouldReturnValueNumSteps() throws FileNotFoundException {
        //AAA, BBB, CCC, ZZZ, 111, 222
        //should be: just 3, 5 -

        //AAA, BBB, CCC, ZZZ, 111, 222, ZZZ, AAA
        //should be: just 3, 3, 5 - use first node once

        //AAA, BBB, CCC, ZZZ
        //should be: just 3, 3

        //AAA, BBB, CCC, ZZZ, CCC
        //should be: just 3, 2 (to infinity)

        List<String> startingNodes = List.of("AAA", "BBB", "CCC", "ZZZ", "111", "222");
        List<String> startingNodes2 = List.of("AAA", "BBB", "CCC", "ZZZ", "111", "222", "ZZZ", "AAA");
        List<String> startingNodes3 = List.of("AAA", "BBB", "CCC", "ZZZ");

        var stepList = Network.listToStepToNodeEndingWithZCount(startingNodes);
        var stepList2 = Network.listToStepToNodeEndingWithZCount(startingNodes2);
        var stepList3 = Network.listToStepToNodeEndingWithZCount(startingNodes3);

        assertEquals(List.of(3, 5), stepList);
        assertEquals(List.of(3, 3, 4), stepList2);
        assertEquals(List.of(3, 3), stepList3);
    }

    @Test
    void solvePart2() throws FileNotFoundException {
        //File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle8-2-sample.txt")).getFile());
        File inputFile = new File(Objects.requireNonNull(this.getClass().getResource("puzzle8.txt")).getFile());

        var network = Puzzle8.readTokens(inputFile);

        var nodesStartingWithA = network.listNodesEndingWithA();

        var nodeCycles = nodesStartingWithA.stream().map(network::getNodesFromSourceCycle).toList();

        //observation, first hit to Z is the same distance as the second hit to Z.
        var nodeNextZPath = nodeCycles.stream().map(Network::listToStepToNodeEndingWithZCount).toList();

        //all of the nodes, for the first and second node (steps to Z), for my input, look the same.
        //im going off of goofy observations with my particular input
        //not generic code.

        //these are actually 6 paths that are 2 nodes deep. we skip the first node (it won't be needed)

        //yolo code below

        //simulate cycles
        int nextZ = nodeNextZPath.stream().map(x -> x.get(0)).reduce(Integer::min).orElseThrow();

        int[] stepsLeftToNextZ = nodeNextZPath.stream().map(vals -> vals.get(0))
                .mapToInt(i -> i)
                .toArray();

        long numSteps = 0;
        while (true) {
            numSteps += nextZ;

            int onZCount = 0;
            //take a step per node-next-z path
            //dont reuse first node
            for (int i = 0; i < nodeNextZPath.size(); i++) {
                if (nextZ <= stepsLeftToNextZ[i]) {
                    stepsLeftToNextZ[i] = stepsLeftToNextZ[i] - nextZ;
                } else {
                    //min is used to subtract so you only subtract once.
                    int diff = nextZ - stepsLeftToNextZ[i];
                    stepsLeftToNextZ[i] = nodeNextZPath.get(i).get(1);
                    stepsLeftToNextZ[i] = stepsLeftToNextZ[i] - diff;
                }

                if (stepsLeftToNextZ[i] == 0) {
                    onZCount++;
                }
            }

            if (onZCount == nodeNextZPath.size()) {
                System.out.println("numSteps: " + numSteps);
                assertEquals(17972669116327L, numSteps;
                break;
            }

            nextZ = Arrays.stream(stepsLeftToNextZ).filter(x -> x != 0).reduce(Integer::min).orElseThrow();  //then use 12362    //min from nodeNextZPath
        }

    }

}