import java.util.LinkedList;
import java.util.Queue;

public class Code01_IsCBT {

    public static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static boolean isCBT(Node root) {
        if (root == null) return true;

        Queue<Node> q = new LinkedList<>();
        boolean leaf = false;
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.poll();

            if(
                    (leaf && (cur.left != null || cur.right != null))
                    ||
                    (cur.left == null && cur.right != null)
            ){
                return false;
            }

            if(cur.left != null) {
                q.add(cur.left);
            }

            if(cur.right != null) {
                q.add(cur.right);
            }

            if(cur.left == null || cur.right == null) {
                leaf = true;
            }
        }
        return true;
    }
}
