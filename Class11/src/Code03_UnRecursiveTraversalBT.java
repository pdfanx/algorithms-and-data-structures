import java.util.Stack;

public class Code03_UnRecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 先序 中左右
    public static void pre(Node head) {
        if (head == null) return;

        Stack<Node> stack = new Stack<>();
        Node cur = head;
        stack.push(cur);
        while (!stack.isEmpty()) {
            cur = stack.pop();
            System.out.print(cur.value + " ");

            if(cur.right != null) stack.push(cur.right);
            if(cur.left != null) stack.push(cur.left);
        }
    }

    // 中序 左中右
    public static void in(Node head) {
        if (head == null) return;

        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while(cur != null || !stack.isEmpty()) {
            if(cur == null) {
                cur = stack.pop();
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
            else
            {
                stack.push(cur);
                cur = cur.left;
            }
        }
    }

    // 后序 左右中
    public static void pos1(Node head) {
        if(head != null) {
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.push(head);
            while (!s1.isEmpty()) {
                head = s1.pop();    // 中 右 左
                s2.push(head);
                if(head.left != null) s1.push(head.left);
                if(head.right != null) s1.push(head.right);

            }

            while(!s2.isEmpty()) {
                head = s2.pop();
                System.out.print(head.value + " ");
            }
        }
    }

    public static void pos2(Node head) {
        if(head != null) {
            Stack<Node> s1 = new Stack<>();
            Node cur = null;
            s1.push(head);
            while (!s1.isEmpty()) {
                cur = s1.peek();
                if (cur.left != null && head != cur.left && head != cur.right) {
                    s1.push(cur.left);
                } else if (cur.right != null && head != cur.right) {
                    s1.push(cur.right);
                } else {
                    System.out.print(s1.pop().value + " ");
                    head = cur;
                }
            }
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

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos1(head);
        System.out.println("========");
        pos2(head);
        System.out.println("========");
    }

}
