import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class Code04_LowestLexicography {
    public static String lowestString2(String[] strs)
    {
        if(strs == null || strs.length == 0)
            return "";
        TreeSet<String> ans = process(strs);
        return ans.first();
    }

    // strs中，所有字符串全排列，返回所有可能结果
    public static TreeSet<String> process(String[] strs)
    {
        TreeSet<String> ans = new TreeSet<String>();
        if(strs.length == 0)
        {
            ans.add("");
            return ans;
        }

        for(int i = 0; i < strs.length; i++)
        {
            String first = strs[i];
            String[] nexts = removeIndexString(strs,i);
            TreeSet<String> next = process(nexts);
            for(String cur : next)
            {
                ans.add(first + cur);
            }
        }
        return ans;
    }

    public static String[] removeIndexString(String[] arr, int index)
    {
        int N = arr.length;
        String[] ans = new String[N - 1];
        int ansIndex = 0;
        for(int i = 0; i < N; i++)
        {
            if(i != index)
                ans[ansIndex++] = arr[i];
        }
        return ans;
    }

    public static String lowestString1(String[] strs)
    {
        if(strs == null || strs.length == 0)
            return "";

        Arrays.sort(strs, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        String ans = "";
        for(int i = 0; i < strs.length; i++)
        {
            ans += strs[i];
        }

        return ans;
    }

    // ------------------------------------------------
    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen)
    {
        String[] arr = new String[(int)(Math.random() * arrLen)];

        for(int i = 0; i < arr.length; i++)
        {
            StringBuilder s = new StringBuilder();
            for(int j = 0; j < (int)(Math.random() * strLen + 1); j++)
            {
                s.append((char) ('a' + (int) (Math.random() * 26)));
            }
            arr[i] = s.toString();
        }
        return arr;
    }

    public static String[] copyStringArray(String[] arr)
    {
        String[] ans = new String[arr.length];
        System.arraycopy(arr, 0, ans, 0, arr.length);

        return ans;
    }

    public static void main(String[] args)
    {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        System.out.println("test begin");

        for(int i = 0; i < testTimes; i++)
        {
            String[] arr1 = generateRandomStringArray(arrLen,strLen);
            String[] arr2 = copyStringArray(arr1);

            String s1 = lowestString1(arr1);
            String s2 = lowestString2(arr2);

            if(!s1.equals(s2)) {
                System.out.println(s1 + " != " + s2);
                System.out.println("oops");
                break;
            }
        }
        System.out.println("test end");
    }
}
