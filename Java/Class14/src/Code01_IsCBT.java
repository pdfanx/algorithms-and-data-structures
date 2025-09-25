public class Code01_IsCBT {
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

    public static boolean isCBT(Node root)
    {
        return process(root).isCBT;
    }

    public static class Info
    {
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean isFull, boolean isCBT, int height)
        {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    public static Info process(Node x)
    {
        if(x == null)
            return new Info(true,true,0);

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        boolean isCBT = isFull;

        // 可能性02
        if(leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1)
            isCBT = true;

        // 可能性03
        if(leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1)
            isFull = true;

        // 可能性04
        if(leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height)
            isFull = true;

        return new Info(isFull,isCBT,height);

    }
}
