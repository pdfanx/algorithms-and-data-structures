import java.util.HashMap;

public class Code01_TrieTree {

    public static class Node1
    {
        public int pass;
        public int end;

        public Node1[] children;

        public Node1()
        {
            children = new Node1[26];
            pass = 0;
            end = 0;
        }

    }

    public static class TrieTree1
    {
        public Node1 root;

        public TrieTree1()
        {
            root = new Node1();
        }

        public void insert(String str)
        {
            if(str == null)
                return;

            char[] chars = str.toCharArray();
            Node1 cur = root;
            cur.pass++;
            int index;
            for(int i = 0; i < chars.length; i++)
            {
                index = chars[i] - 'a';
                if(cur.children[index] == null)
                    cur.children[index] = new Node1();
                cur = cur.children[index];
                cur.pass++;
            }
            cur.end++;
        }

        public void delete(String str)
        {
            if(search(str)!=0)
            {
                char[]  chars = str.toCharArray();
                Node1 cur = root;
                cur.pass--;
                int index;
                for(int i = 0; i < chars.length; i++)
                {
                    index = chars[i] - 'a';
                    // 此后的pass都为1
                    if(--cur.children[index].pass == 0)
                    {
                        cur.children[index] = null;
                        return;
                    }
                    cur = cur.children[index];
                }
                cur.end--;
            }
        }

        // 查找str之前出现了多少次
        public int search(String str)
        {
            if(str == null)
                return 0;
            Node1 cur = root;
            int index;
            char[] chars = str.toCharArray();
            for(int i = 0; i < chars.length; i++)
            {
                index = chars[i] - 'a';
                if(cur.children[index] == null)
                    return 0;
                cur = cur.children[index];
            }
            return cur.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre)
        {
            if(pre == null)
                return 0;

            char[] chars = pre.toCharArray();
            Node1 cur = root;
            int index;
            for(int i = 0; i < chars.length; i++)
            {
                index = chars[i] - 'a';
                if(cur.children[index] == null)
                    return 0;
                cur = cur.children[index];
            }
            return cur.pass;
        }

    }

    public static class Node2
    {
        public int pass;
        public int end;
        public HashMap<Integer, Node2> children;

        public Node2()
        {
            children = new HashMap<>();
            pass = 0;
            end = 0;
        }
    }

    public static class TrieTree2
    {
        private Node2 root;

        public TrieTree2 ()
        {
            root = new Node2();
        }

        public void insert(String str)
        {
            if(str == null)
                return;
            char[] chars = str.toCharArray();
            Node2 cur = root;
            cur.pass++;
            int index;
            for(int i = 0; i < chars.length; i++)
            {
                index = (int)chars[i];
                if(!cur.children.containsKey(index))
                    cur.children.put(index, new Node2());
                cur = cur.children.get(index);
                cur.pass++;
            }
            cur.end++;
        }

        public int search(String str)
        {
            if(str == null)
                return 0;

            Node2 cur = root;
            int index;
            char[] chars = str.toCharArray();
            for(int i = 0; i < chars.length; i++)
            {
                index = (int)chars[i];
                if(!cur.children.containsKey(index))
                    return 0;
                cur = cur.children.get(index);
            }
            return cur.end;
        }

        public void delete(String str)
        {
            if(search(str)!=0)
            {
                char[]  chars = str.toCharArray();
                Node2 cur = root;
                cur.pass--;
                int index;
                for(int i = 0; i < chars.length; i++)
                {
                    index = (int)chars[i];
                    if(--cur.children.get(index).pass == 0) {
                        cur.children.remove(index);
                        return;
                    }
                    cur = cur.children.get(index);

                }
                cur.end--;
            }
        }

        public int prefixNumber(String pre)
        {
            if(pre == null)
                return 0;
            Node2 cur = root;
            int index;
            char[] chars = pre.toCharArray();
            for(int i = 0; i < chars.length; i++)
            {
                index = (int)chars[i];
                if(!cur.children.containsKey(index))
                    return 0;
                cur = cur.children.get(index);
            }
            return cur.pass;
        }
    }

    public static class Right
    {
        private HashMap<String, Integer> box;

        public Right()
        {
            box = new HashMap<>();
        }

        public void insert(String word)
        {
            if(!box.containsKey(word))
            {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }

        }

        public void delete(String word)
        {
            if(box.containsKey(word))
            {
                if(box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }


        public int search(String word)
        {
            if(!box.containsKey(word) || word == null)
                return 0;
            return box.get(word);
        }

        public int prefixNumber(String pre)
        {
            if(pre == null)
                return 0;
            int count = 0;
            for(String str: box.keySet())
            {
                if(str.startsWith(pre))
                    count++;
            }
            return count;
        }
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen,int strlen)
    {
        int length = (int)(Math.random() * arrLen) + 1;
        String[] arr = new String[length];
        for(int i = 0; i < length; i++)
        {
            arr[i] = generateRandomString(strlen);
        }
        return arr;
    }

    public static String generateRandomString(int strLen)
    {
        char[] chars = new char[(int)(Math.random() * strLen) + 1];
        for (int i = 0; i < chars.length; i++)
        {
            int value = (int)(Math.random() * 26);
            chars[i] = (char)(97 + value);
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args)
    {
        int arrLen = 100;
        int strlen = 20;
        int testTimes = 100000;
        for(int i = 0; i < testTimes; i++)
        {
            String[] arr = generateRandomStringArray(arrLen, strlen);
            TrieTree1 trie1 = new TrieTree1();
            TrieTree2 trie2 = new TrieTree2();
            Right r = new Right();
            for(int j = 0; j < arr.length; j++)
            {
                double decide = Math.random();
                if(decide < 0.25)
                {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    r.insert(arr[j]);
                } else if(decide < 0.5)
                {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    r.delete(arr[j]);
                } else if (decide < 0.75){
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = r.search(arr[j]);
                    if(ans1 != ans2 || ans2 != ans3)
                    {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = r.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3)
                    {
                        System.out.println("Oops!");
                    }
                }
            }

        }
        System.out.println("finish!");
    }
}
