import java.util.HashMap;

public class Code04_CopyListWithRandom {
    public static class Node
    {
        int value;
        Node next;
        Node rand;
        Node(int value)
        {
            this.value = value;
        }
    }

    public static Node copyRandomList1(Node head)
    {
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        Node cur = head;
        while (cur != null) {
            map.put(cur,new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).rand = map.get(cur.rand);
            map.get(cur).next = map.get(cur.next);
            cur = cur.next;
        }

        return map.get(head);
    }

    public static Node copyRandomList(Node head)
    {
        if (head == null)
            return null;

        Node cur = head;
        Node copy = null;
        Node next = null;
        while(cur != null) {
            next = cur.next;
            copy = new Node(cur.value);
            cur.next = copy;
            copy.next = next;
            cur = next;
        }
        cur = head;
        while(cur != null) {
            cur.next.rand = cur.rand != null ? cur.rand.next : null;
            cur = cur.next.next;
        }

        Node res = head.next;
        cur = head;
        while(cur != null) {
            next = cur.next.next;
            copy = cur.next;
            copy.next = next != null ? next.next : null;
            cur.next = next;
            cur = next;
        }

        return res;
    }
}
