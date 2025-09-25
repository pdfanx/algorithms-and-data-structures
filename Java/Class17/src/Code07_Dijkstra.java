import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Code07_Dijkstra {


    // 1）Dijkstra算法必须指定一个源点
    // 2）生成一个源点到各个点的最小距离表，一开始只有一条记录，即原点到自己的最小距离为0，源点到其他所有点的最小距离都为正无穷大
    // 3）从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，更新源点到各个点的最小距离表，不断重复这一步
    // 4）源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表得到了

    public static HashMap<Node, Integer> dijkstra1(Node root) {
        Set<Node> visited = new HashSet<Node>();
        HashMap<Node,Integer> distanceMap = new HashMap<>();
        distanceMap.put(root, 0);

        Node minNode = getMinDistanceAndUnselectedNode(distanceMap,visited);

        while (minNode != null) {
            visited.add(minNode);
            int distance = distanceMap.get(minNode);
            for(Edge edge : minNode.edges) {
                Node to = edge.to;
                if(!distanceMap.containsKey(to)) {
                    distanceMap.put(to, distance + edge.weight);
                }else {
                    distanceMap.put(to,Math.min(distanceMap.get(to), distance + edge.weight));
                }

            }
            visited.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap,visited);
        }
        return distanceMap;
    }

    public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, Set<Node> visited) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;

        for(Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if(!visited.contains(node) && distance < minDistance) {
                minDistance = distance;
                minNode = node;
            }
        }

        return minNode;
    }

}
