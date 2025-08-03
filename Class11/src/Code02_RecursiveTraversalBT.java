public class Code02_RecursiveTraversalBT {
    public static class Node
    {
        public int value;
        public Node left;
        public Node right;
        public Node(int value){ this.value = value; }
    }

    public static void f(Node head)
    {
        if(head == null)
            return;

        f(head.left);

        f(head.right);
    }

    // 先序打印所有节点 头左右
    public static void pre(Node head)
    {
        if(head == null)
            return;
        System.out.print(head.value + " ");
        pre(head.left);
        pre(head.right);
    }

    // 中序打印所有节点  左头右
    public static void in(Node head)
    {
        if(head == null)
            return;

        in(head.left);
        System.out.print(head.value + " ");
        in(head.right);
    }

    // 后序遍历所有节点 左右中
    public static void pos(Node head)
    {
        if(head == null)
            return;

        pos(head.left);
        pos(head.right);
        System.out.print(head.value + " ");
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
        pos(head);
        System.out.println("========");

    }

}
