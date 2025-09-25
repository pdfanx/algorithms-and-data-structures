import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Code06_Prim {
    public static Set<Edge> primMSF(Graph graph)
    {
        // 解锁的边放小跟堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        // 已经解锁的点
        HashSet<Node> nodeSet = new HashSet<>();

        // 结果
        Set<Edge> edges = new HashSet<>();

        for(Node node : graph.nodes.values())
        {
            // 是否包含该顶点
            if(!nodeSet.contains(node))
            {
                nodeSet.add(node);
                for(Edge edge : node.edges)
                {
                    priorityQueue.add(edge);    // 找到权重最低的点
                }

                while(!priorityQueue.isEmpty())
                {
                    Edge edge = priorityQueue.poll();
                    Node toNode = edge.to;
                    if(!nodeSet.contains(toNode))
                    {
                        nodeSet.add(toNode);
                        edges.add(edge);
                        for(Edge nextEdge : toNode.edges)
                            priorityQueue.add(nextEdge);
                    }

                }
            }


        }
        return edges;
    }

    // 请保证graph是连通图
    // graph[i][j]表示点i到点j的距离，如果是系统最大值代表无路
    // 返回值是最小连通图的路径之和

    public static int prim(int[][] graph)
    {
        int size = graph.length;
        boolean[] visited = new boolean[size];
        int[] distances = new int[size];
        visited[0] = true;
        for(int i = 0; i < size; i++)
        {
            distances[i] = graph[0][i];
        }

        int sum = 0;

        for(int i = 1; i < size; i++)
        {
            int minPath = Integer.MAX_VALUE;
            int minIndex = -1;

            for(int j = 0; j < size; j++)
            {
                if(!visited[j] && distances[j] < minPath)
                {
                    minPath = distances[j];
                    minIndex = j;
                }
            }
            if(minIndex == -1)
                return sum;

            visited[minIndex] = true;

            sum += minPath;

            for(int j = 0; j < size; j++)
            {
                if(!visited[j] && distances[j] > graph[minIndex][j])
                {
                    distances[j] = graph[minIndex][j];
                }
            }
        }
        return sum;
    }
}
