import java.util.*;

public class Code02_DFS {

    public static void DFS(Node start) {
        if (start == null || start.nexts == null) {
            return;
        }

        Stack<Node> stack = new Stack<Node>();
        Set<Node> visited = new HashSet<Node>();
        stack.add(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            Node curr = stack.pop();

            for (Node n : curr.nexts) {
                if (!visited.contains(n)) {
                    visited.add(n);
                    stack.add(curr);
                    stack.add(n);
                    System.out.println(n.Value);
                    break;
                }
            }
        }
    }
}
