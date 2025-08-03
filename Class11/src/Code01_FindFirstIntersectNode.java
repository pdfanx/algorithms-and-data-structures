public class Code01_FindFirstIntersectNode {
    public static class Node
    {
        public int val;
        public Node next;

        public Node(int x) { val = x; }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null && head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        // 都不包含环
        if(getLoopNode(head1) == null && getLoopNode(head2) == null)
        {
            return noLoop(head1,head2);
        }

        // 都包含环
        if(getLoopNode(head1) != null && getLoopNode(head2) != null)
        {
            return bothLoop(head1,loop1,head2,loop2);
        }

        return null;
    }

    // 找到链表的入环节点，如果没有返回null
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        Node slow = head.next;
        Node fast = head.next.next;
        while(fast != slow)
        {
            if(fast.next == null || fast.next.next == null)
                return null;
            fast = fast.next.next;
            slow = slow.next;
        }

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    // 都不包含环时交点
    public static Node noLoop(Node head1, Node head2) {
        int n = 0;
        Node cur1 = head1;
        Node cur2 = head2;
        while(cur1 != null)
        {
            n++;
            cur1 = cur1.next;
        }

        while(cur2 != null)
        {
            n--;
            cur2 = cur2.next;
        }

        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;

        n= Math.abs(n);
        while(n!=0)
        {
            n--;
            cur1 = cur1.next;
        }

        while(cur1 != cur2)
        {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    }
    public static Node bothLoop(Node head1,Node loop1, Node head2, Node loop2) {

        if(loop1 != loop2)
        {
            Node cur = loop1.next;
            while(cur != loop2)
            {
                if(cur == loop1)
                    return null;
                cur = cur.next;
            }
            return loop1;
        }

        int n = 0;
        Node cur1 = head1;
        Node cur2 = head2;
        while(cur1 != loop1)
        {
            n++;
            cur1 = cur1.next;
        }

        while(cur2 != loop1)
        {
            n--;
            cur2 = cur2.next;
        }

        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;

        n= Math.abs(n);

        while(n!=0)
        {
            n--;
            cur1 = cur1.next;
        }

        while(cur1 != cur2)
        {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).val);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).val);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).val);

    }
}
