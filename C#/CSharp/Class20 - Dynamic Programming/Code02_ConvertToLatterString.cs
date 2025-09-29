namespace Class20___Dynamic_Programming;

public class Code02_ConvertToLatterString
{
    // public static void Main(string[] args)
    // {
    //     System.Console.WriteLine(number("7210234152114521"));
    //     System.Console.WriteLine(dp("7210234152114521"));
    // }

    public static int number(String str)
    {
        if(str == null || str.Length == 0)
            return 0;

        return process(str.ToCharArray(), 0);
    }

    public static int process(char[] str, int i)
    {
        if(i == str.Length)
            return 1;
        
        // i 没有到最后
        if (str[i] == '0')
        {   // 之前的决定有问题
            return 0;
        }
        
        // i位置 单转化
        int ways = process(str, i + 1);
        if (i + 1 < str.Length && (str[i] - '0') * 10 + (str[i + 1] - '0') < 27)
        {
            ways += process(str, i + 2);
        }
        return ways;
    }
    
    public static int dp(String str)
    {
        if(str == null || str.Length == 0)
            return 0;
        
        int N = str.Length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--)
        {
            if (str[i] != '0')
            {
                int ways = dp[i + 1];
                if(i + 1 < str.Length && (str[i] - '0') * 10 + (str[i + 1] - '0') < 27)
                {
                    ways += dp[i + 2];
                }
                dp[i] = ways;
            }
        }

        return dp[0];
    }
}