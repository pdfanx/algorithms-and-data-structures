import java.util.*;

public class Code03_TopologySort {

    public static List<Node> sortedTopology(Graph graph)
    {
        HashMap<Node,Integer> inMap = new HashMap<Node,Integer>();

        Queue<Node> queue = new LinkedList<Node>();
        for(Node node : graph.nodes.values())
        {
            inMap.put(node,node.in);
            if(node.in == 0)
            {
                queue.add(node);
            }
        }

        List<Node> topology = new ArrayList<Node>();

        while(!queue.isEmpty())
        {
            Node curr = queue.poll();
            topology.add(curr);
            for(Node n : curr.nexts)
            {
                n.in--;
                if(n.in == 0)
                {
                    queue.add(n);
                }
            }
        }
        return topology;
    }
}
