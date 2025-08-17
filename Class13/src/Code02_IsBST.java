public class Code02_IsBST {

    public static class Node{
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static boolean isBST(Node root) {
        if (root == null) return true;
        return process(root).isBST;
    }

    // 信息体
    public static class Info
    {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static Info process(Node x)
    {
        // 上游处理空值
        if(x == null) return null;

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        // 最大值
        int max = x.val;
        if(leftInfo != null)
        {
            max = Math.max(max, leftInfo.max);
        }
        if(rightInfo != null)
        {
            max = Math.max(max, rightInfo.max);
        }

        // 最小值
        int min = x.val;
        if(leftInfo != null)
        {
            min = Math.min(max, leftInfo.min);
        }
        if(rightInfo != null)
        {
            min = Math.min(min, rightInfo.min);
        }

        boolean isBST = true;

        if(leftInfo != null && !leftInfo.isBST)
        {
            isBST = false;
        }

        if(rightInfo != null && !rightInfo.isBST)
        {
            isBST = false;
        }
        if(leftInfo != null && leftInfo.max >= x.val)
        {
            isBST = false;
        }
        if(rightInfo != null && rightInfo.max <=x.val)
        {
            isBST = false;
        }

        return new Info(isBST, max, min);
    }
}
