package puzzle8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Puzzle8 {

    /**
     * @param file assume valid
     * @return instructions and root node
     */
    public static Network readTokens(File file) throws FileNotFoundException {

        Scanner in = new Scanner(file);

        String instructions = in.nextLine();
        //LLR
        //
        //AAA = (BBB, BBB)
        //BBB = (AAA, ZZZ)
        //ZZZ = (ZZZ, ZZZ)
        List<List<String>> nodeStrs = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();

            if (line.isEmpty()) {
                continue;
            }

            String nodeId = line.substring(0, line.indexOf(' '));
            String leftStr = line.substring(line.indexOf('(') + 1, line.indexOf(','));
            String rightStr = line.substring(line.indexOf(',') + 2, line.indexOf(')'));

            nodeStrs.add(List.of(nodeId, leftStr, rightStr));
        }

        Map<String, Node> nodes = new HashMap<>();

        //make nodes
        for(List<String> nodeStr : nodeStrs) {
            String nodeId = nodeStr.get(0);
            String nodeLeft = nodeStr.get(1);
            String nodeRight = nodeStr.get(2);

            nodes.put(nodeId, new Node(nodeId, nodeLeft, nodeRight));
        }

        return new Network(instructions, nodes);
    }

}
