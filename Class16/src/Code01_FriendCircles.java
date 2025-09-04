import java.util.*;

public class Code01_FriendCircles {
    public int findCircleNum(int[][] M) {
        ArrayList<Integer> nums = new ArrayList<>();

        for (int i = 0; i < M.length; i++) {
            nums.add(i);
        }

        UnionSet<Integer> set = new UnionSet<>(nums);

        for (int i = 0; i < M.length; i++) {
            for (int j = i + 1; j < M.length; j++) {
                if(M[i][j] == 1)
                    set.union(i,j);
            }
        }
        return set.size();
    }

    public static class Node<V>
    {
        public V data;

        public Node(V data)
        {
            this.data = data;
        }
    }

    public static class UnionSet<V>
    {
        private HashMap<V ,Node<V>> nodes;
        private HashMap<Node<V>, Node<V>> parents;
        private HashMap<Node<V> ,Integer> sizeMap;

        public UnionSet(List<V> cur)
        {
            for(V v : cur)
            {
                Node<V> node = new Node<>(v);
                nodes.put(v, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> cur)
        {
            Stack<Node<V>> stack = new Stack<>();
            while(parents.get(cur) != cur)
            {
                stack.push(cur);
                cur = parents.get(cur);
            }
            while(!stack.isEmpty())
            {
                parents.put(stack.pop(), cur);
            }

            return cur;
        }

        public void union(V a, V b)
        {
            Node<V> A = nodes.get(a);
            Node<V> B = nodes.get(b);
            if(parents.get(A) != parents.get(B))
            {
                Node<V> aHead = parents.get(A);
                Node<V> bHead = parents.get(B);
                int sizeA = sizeMap.get(aHead);
                int sizeB = sizeMap.get(bHead);

                Node<V> big = sizeA >= sizeB ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;

                sizeMap.put(big, sizeA + sizeB);
                sizeMap.remove(small);
                parents.put(small, big);
            }
        }

        public int size()
        {
            return sizeMap.size();
        }
    }
}
