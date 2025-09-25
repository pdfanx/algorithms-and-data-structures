import java.util.Stack;

public class Code05_ReverseStackUsingRecursive {

    // 不使用数据结构 逆序遍历栈
    public static void ReverseStack(Stack<Character> str)
    {
        if(str == null || str.isEmpty())
            return;


        f(str);
        ReverseStack(str);
    }

    public static void f(Stack<Character> str)
    {
        Character c = str.pop();

        if(str.isEmpty())
        {
            System.out.println(c);
            return;
        }
        else {
            f(str);
            str.push(c);
        }
    }

    public static void main(String[] args) {
        Stack<Character> str = new Stack<>();
        str.push('a');
        str.push('b');
        str.push('c');
        ReverseStack(str);
    }
}
