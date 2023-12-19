package puzzle8;

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
}
