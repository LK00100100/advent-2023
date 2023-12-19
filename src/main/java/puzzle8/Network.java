package puzzle8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//nodes = nodeid, Node
public record Network(String instructions, Map<String, Node> nodes) {

    //from start, how many steps? (use instructions, repeated)
    public int countStepsFromAAAToZZZ() {
        Node current = nodes.get("AAA");

        int numSteps = 0;

        //could be infinite :-/
        while (true) {
            for (int i = 0; i < instructions.length(); i++) {
                char c = instructions.charAt(i);

                //go left
                if (c == 'L') {
                    current = nodes.get(current.leftId());
                }
                //go right
                else if (c == 'R') {
                    current = nodes.get(current.rightId());
                }
                else {
                    throw new IllegalArgumentException("hmmm...");
                }

                numSteps++;
                if (current.id().equals("ZZZ"))
                    return numSteps;
            }
        }
    }


    //from start, how many steps? (use instructions, repeated)
    public int getNumStepsFromEndingAtoAllEndingZ() {
        List<Node> currentNodes = nodes.keySet().stream()
                .filter(nodeId -> nodeId.endsWith("A"))
                .map(nodes::get)
                .toList();

        currentNodes = new ArrayList<>(currentNodes);

        int numSteps = 0;

        List<Node> nextNodes = new ArrayList<>();
        //could be infinite :-/
        while (true) {
            for (int i = 0; i < instructions.length(); i++) {
                char c = instructions.charAt(i);

                //one step at every node
                for(Node current : currentNodes) {
                    //go left
                    if (c == 'L') {
                        nextNodes.add(nodes.get(current.leftId()));
                    }
                    //go right
                    else if (c == 'R') {
                        nextNodes.add(nodes.get(current.rightId()));
                    } else {
                        throw new IllegalArgumentException("hmmm...");
                    }

                }

                numSteps++;

                if (nextNodes.stream().allMatch(node -> node.id().endsWith("Z"))) {
                    return numSteps;
                }

                //swap lists
                var temp = currentNodes;
                currentNodes = nextNodes;
                nextNodes = temp;
                temp.clear();
            }
        }
    }
}
