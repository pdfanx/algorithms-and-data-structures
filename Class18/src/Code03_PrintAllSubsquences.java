import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Code03_PrintAllSubsquences {

    public static void printAllSubsquences(char[] str) {
        if(str == null || str.length == 0)
            return;
        List<String> ans = new ArrayList<String>();
        process1(str,0, ans,"");

        for(String s : ans)
            System.out.println(s);
    }

    // str 固定参数
    // 来到了str[index]字符，index是位置
    // str[0...index-1]已经走过了！之前的决定，都放在path上
    // 之前的决定已经不能改变了，就是path
    // str[index...]还能决定，之前已经确定，而后面还能自由选择的的话
    // 把所有子序列放到ans里面去
    public static void process1(char[] str, int index, List<String> ans, String path)
    {
        if(index == str.length)
        {
            ans.add(path);
            return;
        }
        // 当前节点不选
        process1(str, index + 1, ans, path);
        // 当前节点选
        process1(str, index + 1, ans, path + (str[index]));
    }

    public static void printAllSubsquencesWithoutRepeat(char[] str) {
        if(str == null || str.length == 0)
            return;
        Set<String> set = new HashSet<String>();
        List<String> ans = new ArrayList<String>();
        process1(str,0, ans,"");

        for(String s : ans)
            set.add(s);

        for(String s : set)
            System.out.println(s);
    }

    public static void main(String[] args) {
        printAllSubsquences("abb".toCharArray());
        System.out.println("--------------------");
        printAllSubsquencesWithoutRepeat("abb".toCharArray());
    }
}
