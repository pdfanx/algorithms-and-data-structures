namespace Class23;

public class Code03_SplitNumber
{
    public static int ways1(int n)
    {
        if (n < 0)
        {
            return 0;
        }

        if (n == 1)
        {
            return 1;
        }

        return process(1, n);
    }
    
    // 上一个拆出来的是pre
    // 还剩多少个可以拆
    public static int process(int pre, int rest)
    {
        if(rest == 0)
            return 1;
        if (pre > rest)
            return 0;
        if (pre == rest)
            return 1;
        // pre < rest
        int ways = 0;
        for (int first = pre; first <= rest; first++)
        {
            ways += process(first, rest - first);
        }
        return ways;
    }

    public static int dp1(int n)
    {
        if (n < 0)
        {
            return 0;
        }

        if (n == 1)
        {
            return 1;
        }
        
        int[,] dp = new int[n + 1, n +1];
        //.. 
        for (int i = 0; i <= n; i++)
        {
            dp[i, 0] = 1;
        }

        for (int i = 1; i <= n; i++)
        {

            dp[i, i] = 1;

        }

        for (int pre = n - 1; pre >= 1; pre--)
        {
            for (int rest = pre + 1; rest <= n; rest++)
            {
                int ways = 0;
                for (int first = pre; first <= rest; first++)
                {
                    ways += dp[first, rest - first];
                }
                dp[pre, rest] = ways;
            }
        }
        
        
        return dp[1, n];
    }
    
    public static int dp2(int n)
    {
        if (n < 0)
        {
            return 0;
        }

        if (n == 1)
        {
            return 1;
        }
        
        int[,] dp = new int[n + 1, n +1];
        //.. 
        for (int i = 0; i <= n; i++)
        {
            dp[i, 0] = 1;
        }

        for (int i = 1; i <= n; i++)
        {

            dp[i, i] = 1;

        }

        for (int pre = n - 1; pre >= 1; pre--)
        {
            for (int rest = pre + 1; rest <= n; rest++)
            {
                int ways = dp[pre + 1, rest] + dp[pre,rest - pre];

                dp[pre, rest] = ways;
            }
        }
        
        
        return dp[1, n];
    }
}