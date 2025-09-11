import java.util.*;

public class Code04_TopologicalOrderDFS {

    public static class DirectedGraphNode
    {
        public int label;
        public List<DirectedGraphNode> neighbors;
        public DirectedGraphNode(int x)
        {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    public static class Record
    {
        public DirectedGraphNode node;
        public int deepth;

        public Record(DirectedGraphNode node, int deepth)
        {
            this.node = node;
            this.deepth = deepth;
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph)
    {
        HashMap<DirectedGraphNode,Record> node = new HashMap<>();

        for(DirectedGraphNode n : graph)
        {
            f(n,node);
        }

        ArrayList<Record> recordArr = new ArrayList<>();
        for(Record n : node.values())
        {
            recordArr.add(n);
        }

        recordArr.sort(new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return o2.deepth - o1.deepth;
            }
        });
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for(Record n : recordArr)
        {
            ans.add(n.node);
        }
        return ans;
    }


    // 构造HashMap<> mode
    public static Record f(DirectedGraphNode n, HashMap<DirectedGraphNode,Record> node)
    {
        if(node.containsKey(n))
        {
            return node.get(n);
        }

        int follow = 0;

        for(DirectedGraphNode nn : n.neighbors)
        {
            follow = Math.max(follow,f(nn,node).deepth);
        }

        Record ans = new Record(n, follow + 1);
        node.put(n,ans);
        return ans;
    }

}
