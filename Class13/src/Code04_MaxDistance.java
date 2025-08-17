public class Code04_MaxDistance {
    public static class Node
    {
        public int value;
        public Node left;
        public Node right;

        public Node(int value)
        {
            this.value = value;
        }
    }


    public static int maxDistance(Node root)
    {
        return process(root).maxDistance;
    }

    public static class Info
    {
        public int maxDistance;
        public int height;

        public Info(int m, int h)
        {
            maxDistance = m;
            height = h;
        }
    }

    public static Info process(Node x)
    {
        if(x == null)
        {
            // 空值好处理
            return new Info(0, 0);
        }

        Info leftNode = process(x.left);
        Info rightNode = process(x.right);


        int p1 = leftNode.maxDistance;
        int p2 = rightNode.maxDistance;
        int p3 = leftNode.height + rightNode.height + 1;
        int maxDistance = Math.max(p1, Math.max(p2, p3));


        int height = Math.max(leftNode.height, rightNode.height) + 1;

        return new Info(maxDistance, height);
    }

}
