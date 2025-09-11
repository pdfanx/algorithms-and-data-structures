import java.util.*;

public class Code05_Kruskai {
    // 并查集
    public static class UnionFind
    {
        private HashMap<Node, Node> fatherMap;
        private HashMap<Node, Integer> sizeMap;
        public UnionFind() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();

            for(Node node : nodes)
            {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node findFather(Node node) {
            Stack<Node> stack = new Stack<>();
            if(node != fatherMap.get(node)) {
                stack.push(fatherMap.get(node));
                node = fatherMap.get(node);
            }

            while(!stack.isEmpty()) {
                fatherMap.put(stack.pop(), node);
            }

            return node;
        }

        public boolean isSameSet(Node a, Node b) {return findFather(a) == findFather(b);}

        public void union(Node a, Node b) {
            if(a == null || b == null)
            {
                return;
            }

            Node aHead = findFather(a);
            Node bHead = findFather(b);

            if(aHead != bHead) {
                int sizeA = sizeMap.get(aHead);
                int sizeB = sizeMap.get(bHead);

                Node big = sizeA > sizeB ? aHead : bHead;
                Node small = big == aHead ? bHead : aHead;

                sizeMap.put(big,sizeB+sizeA);
                fatherMap.put(small, big);
                sizeMap.remove(small);
            }
        }
    }

    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        for (Edge edge : graph.edges) {
            priorityQueue.add(edge);
        }

        Set<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                result.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return result;
    }
}
