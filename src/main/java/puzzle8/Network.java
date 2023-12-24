package puzzle8;

import java.util.*;

//nodes = nodeid, Node
public record Network(String instructions, Map<String, Node> nodes) {

    //from start, how many steps? (use instructions, repeated)
    public int countStepsFromSourceToZZZ(String nodeId) {
        Node current = nodes.get(nodeId);

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

    /**
     * from start, how many steps? (use instructions, repeated)
     * cycle will end on something ending in Z
     * A -> B -> C -> Z -> B (etc) will yield [a, b, c, z, b, c, z]
     * @param nodeId -
     * @return -
     */
    public List<String> getNodesFromSourceCycle(String nodeId) {
        Set<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();

        Node current = nodes.get(nodeId);

        //could be infinite :-/
        while (true) {
            for (int i = 0; i < instructions.length(); i++) {
                char c = instructions.charAt(i);

                String key = i + ";" + current.id();

                if(visited.contains(key) && current.id().endsWith("Z")) {
                    path.add(current.id());
                    return path;
                }

                visited.add(key);
                path.add(current.id());

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

    public List<String> listNodesEndingWithA() {
        return this.nodes.keySet().stream()
                .filter(x -> x.endsWith("A"))
                .toList();
    }

    /**
     * nodes doesn't start on Z.
     *
     * @param nodes -
     * @return only use the first step once. probably should be safety rails...
     */
    public static List<Integer> listToStepToNodeEndingWithZCount(List<String> nodes) {
        List<Integer> stepCounts = new ArrayList<>();

        //AAA, BBB, CCC, ZZZ, 111, 222
        //should be: just 3, 5 - use first node once

        //AAA, BBB, CCC, ZZZ, 111, 222, ZZZ, AAA
        //should be: just 3, 3, 4 - use first node once

        //for every current node, when is the next --Z node?
        for(int current = 0; current < nodes.size(); current++) {
            for(int numSteps = 1; current + numSteps < nodes.size(); numSteps++) {
                if(nodes.get(current + numSteps).endsWith("Z")) {
                    stepCounts.add(numSteps);
                    current = current + numSteps - 1;   //start on --Z
                    break;
                }

                //at the end but we have these extra steps...
                if(current + numSteps == nodes.size() - 1) {
                    stepCounts.add(stepCounts.getLast() + numSteps);
                    current = current + numSteps;
                }
            }
        }

        if(stepCounts.size() == 1)
            stepCounts.add(stepCounts.get(0));

        return stepCounts;
    }
}
