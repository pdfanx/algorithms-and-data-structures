import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class Code01_Dijkstra {
    // 加强堆优化
    // 1）Dijkstra算法必须指定一个源点
    // 2）生成一个源点到各个点的最小距离表，一开始只有一条记录，即原点到自己的最小距离为0，源点到其他所有点的最小距离都为正无穷大
    // 3）从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，更新源点到各个点的最小距离表，不断重复这一步
    // 4）源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表得到了

    // 加强堆 -- 反向索引表
    // 可选节点及距离 -- 修改距离

    public static class Record{
        public int distance;
        public Node node;

        public Record(Node node, int distance){
            this.node = node;
            this.distance = distance;
        }
    }

    public static class HeapGeater
    {
        // 反向索引表
        private HashMap<Node,Integer> map = new HashMap<>();
        private HashMap<Node,Integer> distanceMap = new HashMap<>();

        private Node[] nodes;

        private int heapSize = 0;

        public HeapGeater(int size)
        {
            nodes = new Node[size];

            heapSize = 0;
        }

        public void addOrUpdateorIgnore(Node node, int distance)
        {
            if(inHeap(node))
            {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                heapInsert(map.get(node));
            }
            else {
                nodes[heapSize] = node;
                map.put(node, heapSize);
                distanceMap.put(node, distance);
                heapInsert(map.get(node));
            }
        }

        private boolean inHeap(Node node)
        {
            for(int i = 0;i < heapSize;i++)
            {
                if(nodes[i] == node)
                    return true;
            }
            return false;
        }

        private void heapify(int index)
        {
            int left = (index * 2) + 1;
            while(left < heapSize)
            {

                int smallest = left + 1 < heapSize && distanceMap.get(nodes[left]) > distanceMap.get(nodes[left + 1])
                        ? (left + 1) : left;
                smallest = distanceMap.get(nodes[index]) < distanceMap.get(nodes[smallest]) ? index : smallest;

                if(smallest == index)
                {
                    break;
                }

                swap(index,smallest);
                index = smallest;
                left = (index * 2) + 1;
            }
        }

        private void heapInsert(int index)
        {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2]))
            {
                swap(index,(index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void push(Node node , int distance)
        {
            nodes[heapSize] = node;

            map.put(node, heapSize);
            distanceMap.put(node, node.Value + distance);

            heapInsert(heapSize++);
        }

        public boolean isEmpty()
        {
            return nodes.length == 0;
        }

        public Record pop()
        {
            Node node = nodes[0];
            int distance = distanceMap.get(node);

            swap(0, heapSize - 1);

            map.remove(node);
            distanceMap.remove(node);

            nodes[0] = nodes[heapSize - 1];
            nodes[heapSize - 1] = null;

            heapSize--;
            heapify(0);

            return new Record(node, distance);
        }

        public void swap(int index1, int index2)
        {
            Node node1 = nodes[index1];
            Node node2 = nodes[index2];
            nodes[index1] = node2;
            nodes[index2] = node1;
            map.put(node1,index2);
            map.put(node2,index1);
        }
    }

    public static HashMap<Node, Integer> dijkstra(Node root,int size) {
        HashMap<Node, Integer> ans = new HashMap<>();

        HeapGeater heap = new HeapGeater(size);
        heap.addOrUpdateorIgnore(root,0);

        while(!heap.isEmpty())
        {
            Record cur = heap.pop();
            Node curNode = cur.node;
            int distance = cur.distance;
            for(Edge edge : curNode.edges)
            {
                heap.addOrUpdateorIgnore(edge.to,distance + edge.weight);
            }
            ans.put(curNode,distance);
        }

        return ans;
    }
}
