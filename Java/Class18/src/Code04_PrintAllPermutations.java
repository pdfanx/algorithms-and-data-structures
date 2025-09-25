import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Code04_PrintAllPermutations {

    // 黑盒思想
    public static void printAllPermutations(char[] str) {
        if(str == null || str.length == 0)
            return;
        List<String> list = new ArrayList<String>();

        g(str,list,0);

        for (String s : list)
            System.out.println(s);

    }
    // 交换位置
    public static void g(char[] str, List<String> ans, int index) {
        if(index == str.length)
        {
            ans.add(String.valueOf(str));
            return;
        }

        for(int i = index; i < str.length; i++) {
            swap(str,index,i);
            g(str,ans, i + 1);
            swap(str,index,i);
        }
    }

    public static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public static void printAllPermutationsWithoutRepeat(char[] str) {
        if(str == null || str.length == 0)
            return;
        Set<String> set = new HashSet<String>();
        List<String> list = new ArrayList<String>();

        g(str,list,0);

        for (String s : list)
            set.add(s);
        for(String s : set)
            System.out.println(s);
    }

    public static void main(String[] args) {
        printAllPermutations("abb".toCharArray());
        System.out.println("---------------------");
        printAllPermutationsWithoutRepeat("abb".toCharArray());
    }

}
