import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Code01_BFS {

    // 宽度优先遍历
    public static void BFS(Node start)
    {
        if(start == null || start.nexts.size() == 0)
        {
            return;
        }

        Queue<Node> queue = new LinkedList<Node>();
        HashSet<Node> visited = new HashSet<Node>();
        queue.add(start);
        visited.add(start);
        while(!queue.isEmpty())
        {
            Node current = queue.poll();

            for(Node node : current.nexts)
            {
                if(!visited.contains(node)) {
                    queue.add(node);
                    visited.add(node);
                }
            }
        }
    }
}
