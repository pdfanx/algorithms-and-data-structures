public class Code03_IsBalanced {
    public class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int value) {
            val = value;
        }
    }

    public static boolean isBalanced(Node root) {
        return process(root).isBalanced;
    }

    public static class Info{
        public boolean isBalanced;
        public int height;

        public Info(boolean i, int h) {
            isBalanced = i;
            height = h;
        }
    }

    public static Info process(Node x)
    {
        if(x == null)
        {
            return new Info(true, 0);
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        boolean isBalanced = true;

        if(!leftInfo.isBalanced)
        {
            isBalanced = false;
        }

        if(!rightInfo.isBalanced)
        {
            isBalanced = false;
        }

        if(Math.abs(leftInfo.height - rightInfo.height) > 1)
        {
            isBalanced = false;
        }

        int height = Math.max(leftInfo.height, rightInfo.height) ;

        return new Info(isBalanced, height);
    }
}
