public class Code02_lowestAncestor {
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

    public static class Info
    {
        public boolean findA;
        public boolean findB;
        public Node ans;

        public Info(boolean findA, boolean findB, Node ans)
        {
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
        }
    }

    public static Node lowestAncestor(Node head,Node a,Node b)
    {
        return process(head,a,b).ans;
    }

    public static Info process(Node x,Node a,Node b)
    {
        if(x == null)
        {
            return new Info(false,false,null);
        }

        Info leftInfo = process(x.left,a,b);
        Info rightInfo = process(x.right,a,b);

        boolean findA = leftInfo.findA || rightInfo.findA || x == a;
        boolean findB = leftInfo.findB || rightInfo.findB || x == b;
        Node ans = null;

        if(leftInfo.ans != null)
            ans = leftInfo.ans;

        if(rightInfo.ans != null)
            ans = rightInfo.ans;

        if(findA && findB)
            ans = x;


        return new Info(findA,findB,ans);
    }
}
