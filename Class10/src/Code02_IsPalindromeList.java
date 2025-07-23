import java.util.Stack;

public class Code02_IsPalindromeList {

    public static class Node
    {
        public int value;
        public Node next;

        public Node(int value)
        {
            this.value = value;
            next = null;
        }
    }

    // need n extra space
    public static boolean isPalindrome1(Node head)
    {
        Stack<Node> stack = new Stack<Node>();
        Node current = head;

        while(current != null)
        {
            stack.push(current);
            current = current.next;
        }

        while (head != null)
        {
            current = stack.pop();
            if (current.value != head.value) return false;
            head = head.next;
        }

        return true;
    }

    // need n/2 extra space
    public static boolean isPalindrome2(Node head)
    {
        if(head == null || head.next == null) return true;

        Node fast = head;
        Node slow =  head;
        Node curr = head;
        while(fast.next != null && fast.next.next != null)
        {
            fast = fast.next.next;
            slow = slow.next;
        }

        Stack<Node> stack = new Stack<>();
        while(slow.next!=null)
        {
            stack.push(slow.next);
            slow = slow.next;
        }
        while(!stack.isEmpty())
        {
            if(stack.pop().value != curr.value) return false;
            curr = curr.next;
        }
        return true;
    }

    // need O(1) extra space
    public static boolean isPalindrome3(Node head)
    {
        if(head == null || head.next == null) return true;
        Node n1 = head;
        Node n2 = head;
        while(n2.next != null && n2.next.next != null)
        {
            n1 = n1.next;
            n2 = n2.next.next;
        }

        n2 = n1.next;
        n1.next = null;
        Node n3 = null;

        while (n2!=null)
        {
            n3= n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1;
        n2 = head;
        boolean result = true;
        while(n1!=null && n2!=null)
        {
            if(n1.value != n2.value) result = false;
            n1 = n1.next;
            n2 = n2.next;
        }
        n1 = n3.next;
        n3.next = null;
        while(n1!=null)
        {
            n2 =  n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return result;
    }
}
