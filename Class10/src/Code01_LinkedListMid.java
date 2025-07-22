package class10;

public class Code01_LinkedListMid {
    public static class Node
    {
        public int id;
        public Node next;

        public Node(int id) {
            this.id = id;
        }
    }

    public static Node MidOrUpMidNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        Node slow = head;
        Node fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node MidOrDownMidNode(Node head)
    {
        if(head == null || head.next == null) {
            return null;
        }

        Node slow = head.next;
        Node fast = head.next;

        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static Node MidOrUpMidPreNode(Node head)
    {
        if(head == null || head.next == null || head.next.next == null) {
            return null;
        }

        Node slow = head;
        Node fast = head.next.next;

        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node MidOrDownMidPreNode(Node head)
    {
        if(head == null || head.next == null ) {
            return null;
        }
        if(head.next.next == null) {
            return head;
        }

        Node slow = head;
        Node fast = head.next;

        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
