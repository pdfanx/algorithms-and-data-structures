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
    public static boolean isPalindromeList(Node head)
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
}
