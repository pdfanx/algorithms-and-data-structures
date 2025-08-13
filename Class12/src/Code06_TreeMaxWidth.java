import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Code06_TreeMaxWidth {
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

    // 1、使用容器
    public static int maxWidthUseMap(Node root)
    {
        HashMap<Node, Integer> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        map.put(root, 0);
        Node cur = null;
        int maxWidth = 0;
        int curLevel = 0;
        int curLevelNodes = 0;
        while(queue != null) {
            cur = queue.poll();
            int curNodeLevel = map.get(cur);

            if(cur.left != null) {
                queue.add(cur.left);
                map.put(cur.left, curNodeLevel + 1);
            }

            if(cur.right != null) {
                queue.add(cur.right);
                map.put(cur.right, curNodeLevel + 1);
            }

            if(curNodeLevel == curLevel) {
                curLevelNodes++;
            }
            else
            {
                maxWidth = Math.max(maxWidth, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        maxWidth = Math.max(maxWidth, curLevelNodes);
        return maxWidth;
    }

    // 2、 不使用容器
    public static int maxWidthNoMap(Node head)
    {
        if(head == null)
            return 0;

        int maxWidth = 0;
        Queue<Node> queue = new LinkedList<>();
        Node curEnd = head;
        Node nextEnd = null;
        int curLevelNodes = 0;
        while(queue != null) {
            Node cur = queue.poll();
            if(cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }

            if(cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }

            curLevelNodes++;

            if(cur == curEnd) {
                maxWidth = Math.max(maxWidth, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }


        return maxWidth;
    }


}
