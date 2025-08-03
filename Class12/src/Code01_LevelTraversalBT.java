import java.util.LinkedList;
import java.util.Queue;

public class Code01_LevelTraversalBT {
    public static class Node
    {
        public int data;
        public Node left;
        public Node right;

        public Node(int data)
        {
            this.data = data;
        }
    }

    public static void level(Node head)
    {
        if (head == null) return;
        Queue<Node> q = new LinkedList<Node>();
        q.add(head);
        while(!q.isEmpty())
        {
            Node current = q.poll();
            System.out.print(current.data + " ");
            if(current.left != null) q.add(current.left);
            if(current.right != null) q.add(current.right);
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        level(head);
        System.out.println("========");
    }
}
