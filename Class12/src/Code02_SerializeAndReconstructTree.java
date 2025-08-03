import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code02_SerializeAndReconstructTree {
    /*
     * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
     * 以下代码全部实现了。
     * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
     * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
     * 比如如下两棵树
     *         __2
     *        /
     *       1
     *       和
     *       1__
     *          \
     *           2
     * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
     *
     * */
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

    // 序列化
    public static Queue<String> preSerial(Node head)
    {
        Queue<String> q = new LinkedList<String>();
        pres(head,q);
        return q;
    }

    public static void pres(Node head, Queue<String> ans)
    {
        if(head == null)
        {
            ans.add(null);
            return;
        }

        ans.add(String.valueOf(head.data));
        pres(head.left, ans);
        pres(head.right, ans);
    }

    public static Queue<String> inSerial(Node head)
    {
        Queue<String> q = new LinkedList<String>();
        in(head,q);
        return q;
    }

    public static void in(Node head, Queue<String> ans)
    {
        if(head == null)
        {
            ans.add(null);
            return;
        }


        pres(head.left, ans);
        ans.add(String.valueOf(head.data));
        pres(head.right, ans);
    }

    public static Queue<String> posSerial(Node head)
    {
        Queue<String> q = new LinkedList<String>();
        pos(head,q);
        return q;
    }

    public static void pos(Node head, Queue<String> ans)
    {
        if(head == null)
        {
            ans.add(null);
            return;
        }


        pos(head.left, ans);
        pos(head.right, ans);
        ans.add(String.valueOf(head.data));
    }

    // 反系列化
    public static Node BuildByPreQueue(Queue<String> prelist)
    {
        if(prelist == null || prelist.isEmpty())
            return null;

        return preb(prelist);
    }

    public static Node preb(Queue<String> prelist)
    {
        String value = prelist.poll();
        if(value == null)
            return null;
        Node node = new Node(Integer.valueOf(value));
        node.left = preb(prelist);
        node.right = preb(prelist);
        return node;
    }

    public static Node BuildByPosQueue(Queue<String> prelist)
    {
        if(prelist == null || prelist.isEmpty())
            return null;
        Stack<String> stack = new Stack<String>();
        while(!prelist.isEmpty())
        {
            stack.push(prelist.poll());
        }

        return poss(stack);
    }
    // 中右左 倒序
    public static Node poss(Stack<String> prelist)
    {
        String value = prelist.pop();
        if(value == null)
            return null;
        Node node = new Node(Integer.valueOf(value));
        node.right = poss(prelist);
        node.left = poss(prelist);
        return node;
    }

    public static Queue<String> levelSerial(Node head)
    {
        Queue<String> q = new LinkedList<>();
        level(head,q);
        return q;
    }

    public static void level(Node head, Queue<String> ans)
    {
        if(head == null) {
            ans.add(null);
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(head);
        ans.add(String.valueOf(head.data));
        while(!q.isEmpty()) {
            Node current = q.poll();

            if(current.left != null) {
                q.add(current.left);
                ans.add(String.valueOf(current.left.data));
            }
            else {
                ans.add(null);
            }


            if(current.right != null)
            {
                q.add(current.right);
                ans.add(String.valueOf(current.right.data));
            }
            else {
                ans.add(null);
            }

        }

    }

    public static Node buildByLevelQueue(Queue<String> levelList)
    {
        if(levelList == null || levelList.isEmpty())
            return null;

        Node head = generateNode(levelList.poll());
        Queue<Node> q = new LinkedList<>();

        if(head != null) {
            q.add(head);
        }

        while(!q.isEmpty()) {
            Node current = q.poll();
            current.left = generateNode(levelList.poll());
            current.right = generateNode(levelList.poll());

            if(current.left != null) {
                q.add(current.left);
            }
            if(current.right != null) {
                q.add(current.right);
            }
        }

        return head;
    }



    public static Node generateNode(String val)
    {
        if(val == null)
            return null;

        Node node = new Node(Integer.valueOf(val));
        return node;
    }

    // for test
    public static Node generateRandomBST(int maxlevel,int maxValue)
    {
        return generate(1,maxlevel,maxValue);
    }

    public static Node generate(int level, int maxLevel, int maxValue)
    {
        if(level > maxLevel || Math.random() < 0.5)
            return null;

        Node head = new Node((int)(Math.random() * maxValue));
        head.left = generate(level + 1,maxLevel,maxValue);
        head.left = generate(level + 1,maxLevel,maxValue);
        return head;
    }

    public static boolean isSameValueStructure(Node a, Node b)
    {
        if(a == null && b == null)
            return true;
        if(a == null || b == null)
            return false;
        if(a.data != b.data)
            return false;
        return isSameValueStructure(a.left, b.left) && isSameValueStructure(a.right, b.right);
    }

    // for test
    public static void main(String[] args)
    {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;

        System.out.println("Test Begin");
        for(int i = 0; i < testTimes; i++)
        {
            Node head = generateRandomBST(maxLevel,maxValue);
            Queue<String> level = levelSerial(head);
            Queue<String> prelist = preSerial(head);
            Queue<String> pos = posSerial(head);
            Node levelBuild = buildByLevelQueue(level);
            Node posBuild = BuildByPosQueue(pos);
            Node preBuild = BuildByPreQueue(prelist);
            if(!isSameValueStructure(preBuild,posBuild) || !isSameValueStructure(preBuild,levelBuild))
            {
                System.out.println("Error");
            }

        }
        System.out.println("Test End");
    }

}
