import java.util.*;

public class Code02_NumberOfIslands {

    // 1
    public static int numOfIslands1(char[][] grid) {
        int numOfIslands = 0;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    numOfIslands++;
                    infect(grid,i,j);
                }
            }
        }

        return numOfIslands;
    }

    public static void infect(char[][] grid, int i, int j) {
        if( i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1')
        {
            return;
        }

        grid[i][j] = '2';
        infect(grid,i-1,j);
        infect(grid,i+1,j);
        infect(grid,i,j-1);
        infect(grid,i,j+1);
    }

    // 2 HashMap实现并查集
    public static int numIslands2(char[][] grid) {
        Dot[][] dots = new Dot[grid.length][grid[0].length];
        List<Dot> dotList = new ArrayList<>();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    dots[i][j] = new Dot();
                    dotList.add(dots[i][j]);
                }
            }
        }

        // 1.创建并查集合
        UnionFind<Dot> uf = new UnionFind<>(dotList);

        for(int i = 1; i < dots.length; i++) {
            for(int j = 1; j < dots[0].length; j++) {
                if(dots[i][j] != null) {
                    if(dots[i-1][j]!=null) {
                        uf.union(dots[i][j], dots[i-1][j]);
                    }

                    if(dots[i][j-1]!=null) {
                        uf.union(dots[i][j-1], dots[i][j]);
                    }
                }
            }
        }

        if(dots[0][0] != null)
        {
            if(dots.length > 1 && dots[1][0]!= null )
                uf.union(dots[0][0],dots[1][0]);

            if(dots[0].length > 1 && dots[0][1]!=null)
                uf.union(dots[0][0],dots[0][1]);
        }


        for(int i = 1;i < dots.length; i++)
        {
            if(dots[i - 1][0] != null && dots[i][0] != null)
            {
                uf.union(dots[i-1][0],dots[i][0]);
            }
        }

        for(int i = 1;i < dots[0].length; i++)
        {

            if(dots[0][i] != null && dots[0][i-1] != null)
            {
                uf.union(dots[0][i-1],dots[0][i]);
            }
        }


        return uf.sets();
    }

    public static class Dot{

    }

    public static class Node<V>
    {
        private V ch;

        public Node(V ch)
        {
            this.ch = ch;
        }
    }

    public static class UnionFind<V>
    {
        public HashMap<V,Node<V>> nodes;
        public HashMap<Node<V>,Node<V>> parents;
        public HashMap<Node<V>,Integer> sizeMap;

        public UnionFind(List<V> chars)
        {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();

            for(V c : chars)
            {
                Node<V> node = new Node<V>(c);
                nodes.put(c,node);
                parents.put(node,node);
                sizeMap.put(node,1);
            }
        }

        public Node<V> findFather(Node<V> cur)
        {
            Stack<Node<V>> stack = new Stack<>();
            while(cur != parents.get(cur))
            {
                stack.push(cur);
                cur = parents.get(cur);
            }
            while(!stack.isEmpty())
            {
                parents.put(stack.pop(),cur);
            }

            return cur;
        }

        public void union(V a, V b)
        {
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));

            if(aHead != bHead)
            {
                int sizeA = sizeMap.get(aHead);
                int sizeB = sizeMap.get(bHead);
                Node<V> big = sizeA > sizeB ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;

                parents.put(small,big);
                sizeMap.put(big,sizeA+sizeB);
                sizeMap.remove(small);
            }
        }

        public int sets(){return sizeMap.size();}
    }

    // 3 使用数组实现并查集

    public static class UnionFind2
    {
        private int[] parent;
        private int[] size;
        private int[] help;
        int col;
        int sets;

        public UnionFind2(char[][] board)
        {
            col = board[0].length;
            sets = 0;

            int row = board.length;
            int len = row * col;

            parent = new int[len];
            size = new int[len];
            help = new int[len];

            for(int r = 0;r < len;r++)
            {
                for(int c = 0;c < col;c++)
                {
                    if(board[r][c] == '1')
                    {
                        int idx = index(r,c);
                        parent[idx] = idx;
                        size[idx] = 1;
                        sets++;
                    }
                }
            }
        }

        private int index(int i, int j)
        {
            return i * col + j;
        }

        private int find(int i)
        {
            int fi = 0;
            while(i != parent[i])
            {
                help[fi++] = i;
                i = parent[i];
            }

            for(fi--;fi>=0;fi--)
            {
                parent[help[fi]] = i;
            }

            return fi;
        }

        public void union(int r1, int c1, int r2, int c2)
        {
            int i1 = index(r1,c1);
            int i2 = index(r2,c2);

            int h1 = find(i1);
            int h2 = find(i2);

            if(h1 != h2)
            {
                if(size[h1] >= size[h2])
                {
                    parent[h2] = h1;
                    size[h1] += size[h2];
                }
                else
                {
                    parent[h1] = h2;
                    size[h2] += size[h1];
                }

                sets--;
            }
        }

        public int sets(){return sets;}
    }


    // for test
    public static char[][] generateIslandsArray(int maxRow, int maxCol) {
        int il = (int)(Math.random() * maxRow) + 1;
        int jl = (int)(Math.random() * maxCol) + 1;

        char[][] islands = new char[il][jl];
        for(int i = 0; i < il; i++) {
            for(int j = 0; j < jl; j++) {
                double p = Math.random();
                if(Math.random() < p)
                {
                    islands[i][j] = '1';
                }
                else {
                    islands[i][j] = '0';
                }
            }
        }

        return islands;
    }

    public static void main(String[] args) {
        int maxCol = 10;
        int maxRow = 20;
        int testTimes = 1000000;
        for(int i = 0; i < testTimes; i++) {
            char[][] grid = generateIslandsArray(maxCol,maxRow);
            int islands1 = numOfIslands1(grid);
        }
    }
}
