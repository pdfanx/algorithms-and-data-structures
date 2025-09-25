public class Code05_IsFull {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value)
        {
            this.value = value;
        }
    }

    public static class Info
    {
        public int maxBSTSubtreeSize;
        public int allSize;
        public int max;
        public int min;

        public Info(int maxBSTSubtreeSize, int allSize, int max, int min)
        {
            this.maxBSTSubtreeSize = maxBSTSubtreeSize;
            this.allSize = allSize;
            this.max = max;
            this.min = min;
        }
    }

    public static int IsFull(Node root)
    {
        if(root == null)
            return 0;

        return process(root).maxBSTSubtreeSize;
    }

    public static Info process(Node x)
    {
        if (x == null)
            return null;

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int allSize = 1;
        int max = x.value;
        int min = x.value;

        if(leftInfo != null)
        {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            allSize += leftInfo.allSize;
        }

        if(rightInfo != null)
        {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            allSize += rightInfo.allSize;
        }

        int p1 = -1;
        if(leftInfo != null)
        {
            p1 = leftInfo.maxBSTSubtreeSize;
        }
        int p2 = -1;
        if(rightInfo != null)
        {
            p2 = rightInfo.maxBSTSubtreeSize;
        }
        int p3 = -1;

        boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
        boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
        if(leftBST && rightBST){
            boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.value);
            boolean rightMinMoreX = rightInfo == null ? true : (rightInfo.min > x.value);
            if(leftMaxLessX && rightMinMoreX){
                int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                p3 = leftSize + rightSize + 1;
            }
        }

        return new Info(Math.max(p1,Math.max(p2,p3)), allSize, max, min);
    }
}
