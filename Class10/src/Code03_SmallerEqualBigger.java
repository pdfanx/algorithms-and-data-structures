public class Code03_SmallerEqualBigger {
    public static class Node
    {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node listPartition1(Node head,int pivot)
    {
        if(head == null)
        {
            return head;
        }

        int i = 0;
        Node cur = head;
        while(cur!=null)
        {
            i++;
            cur = cur.next;
        }

        Node[] nodeArr = new Node[i];
        cur = head;
        for(i = 0;i < nodeArr.length;i++)
        {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        int p1 = 0;
        int p2 = nodeArr.length-1;
        i = 0;
        while (i <= p2)
        {
            if(nodeArr[i].value<pivot)
            {
                Node temp = nodeArr[i];
                nodeArr[i] = nodeArr[p1];
                nodeArr[p1] = temp;
                p1++;
                i++;
            } else if (nodeArr[i].value > pivot) {
                Node temp = nodeArr[i];
                nodeArr[i] = nodeArr[p2];
                nodeArr[p2] = temp;
                p2--;
            }else {
                i++;
            }
        }

        for(i = 1;i < nodeArr.length;i++)
        {
            nodeArr[i-1].next = nodeArr[i];
        }
        nodeArr[nodeArr.length-1].next = null;
        return nodeArr[0];
    }

    public static Node arrPartition(Node head,int pivot)
    {
        if(head == null)
        { return head;}
        Node smallerHead = null;
        Node smallerTail = null;
        Node largerHead = null;
        Node largerTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node cur = head;
        while (cur!=null)
        {
            Node next = cur.next;
            cur.next = null;
            if(cur.value < pivot)
            {
                if(smallerHead==null) {
                    smallerHead = cur;
                    smallerTail = cur;
                }
                else
                {
                    smallerTail.next = cur;
                    smallerTail = cur;
                }
            }else if(cur.value > pivot)
            {
                if(largerHead==null) {
                    largerHead = cur;
                    largerTail = cur;
                }
                else
                {
                    largerTail.next = cur;
                    largerTail = cur;
                }
            }else {
                if(equalHead==null) {
                    equalHead = cur;
                    equalTail = cur;
                }
                else
                {
                    equalTail.next = cur;
                    equalTail = cur;
                }
            }

            cur = next;
        }

        if(smallerTail!=null)
        {
            smallerTail.next = equalHead;
            equalTail = equalTail == null ? smallerTail : equalTail;
        }

        if(equalTail!=null)
        {
            equalTail.next = largerHead;

        }

        return smallerHead != null ? smallerHead : (equalHead != null ? equalHead : largerHead);
    }

    public static void printLinkedList(Node head)
    {
        Node cur = head;
        while(cur != null)
        {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);

        printLinkedList(head1);
        // head1 = listPartition1(head1,5);
        head1 = arrPartition(head1,5);
        printLinkedList(head1);
    }
}
