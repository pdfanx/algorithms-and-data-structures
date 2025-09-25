import java.util.ArrayList;
import java.util.List;

public class Code03_EncodeNaryTreeToBinaryTree {

    public static class Node
    {
        public int data;
        public List<Node> children;

        public Node(int data)
        {
            this.data = data;
        }

        public Node(int data, List<Node> children)
        {
            this.data = data;
            this.children = children;
        }
    }

    public static class TreeNode
    {
        public int data;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data)
        {
            this.data = data;
        }
    }

    class Codec{
        public TreeNode encode(Node root)
        {
            if(root == null)
                return null;
            TreeNode node = new TreeNode(root.data);

            node.left = en(root.children);

            return node;
        }

        public TreeNode en(List<Node> children)
        {
            if(children == null)
                return null;

            TreeNode head = null;
            TreeNode cur = null;

            for(Node node : children)
            {
                TreeNode n = new TreeNode(node.data);
                if(head == null)
                    head = n;
                else
                    cur.right = n;

                cur = n;
                cur.left = en(node.children);
            }

            return head;
        }

        public Node decode(TreeNode root)
        {
            if(root == null)
                return null;

            Node head = de(root);
            return head;
        }

        public Node de(TreeNode root)
        {
            if(root == null)
                return null;

            Node head = new Node(root.data);
            head.children = new ArrayList<>();
            TreeNode cur = root.left;
            while (cur != null)
            {
                Node node = de(cur);
                head.children.add(node);
                cur = cur.right;
            }

            return head;
        }

    }

}
