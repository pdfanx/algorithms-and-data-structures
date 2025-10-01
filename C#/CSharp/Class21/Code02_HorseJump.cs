namespace Class21;

public class Code02_HorseJump
{
    public static int jump(int a, int b, int k)
    {
        return process(0, 0, k, a, b);
    }
    
    // 当前来到的位置（x,y）
    // 还剩下rest步需要跳
    // 跳完rest步，正好跳到a,b的方法数是多少？
    public static int process(int x, int y,int rest,int a,int b)
    {
        // 越界
        if (x < 0 || x > 9 || y < 0 || y > 8)
        {
            return 0;
        }
        
        if (rest == 0)
        {
            return (a == x && b == y) ? 1 : 0;
        }

        int ways = process(x + 2, y + 1, rest - 1, a, b);
        ways += process(x + 1, y + 2, rest - 1, a, b);
        ways += process(x - 1, y + 2, rest - 1, a, b);
        ways += process(x - 2, y + 1, rest - 1, a, b);
        ways += process(x - 2, y - 1, rest - 1, a, b);
        ways += process(x - 1, y - 2, rest - 1, a, b);
        ways += process(x + 1, y - 2, rest - 1, a, b);
        ways += process(x + 2, y - 1, rest - 1, a, b);
        
        return ways;
    }

    public static int jump1(int a, int b, int k)
    {
        int[,,] dp = new int[10, 9, k + 1];

        dp[a, b, 0] = 1;
        for (int rest = 1; rest <= k; rest++)
        {
            for (int x = 0; x < 10; x++)
            {
                for (int y = 0; y < 9; y++)
                {
                    int ways = pick(dp,x + 2, y + 1, rest - 1);
                    ways += pick(dp,x + 1, y + 2, rest - 1);
                    ways += pick(dp,x - 1, y + 2, rest - 1);
                    ways += pick(dp,x - 2, y + 1, rest - 1);
                    ways += pick(dp,x - 2, y - 1, rest - 1);
                    ways += pick(dp,x - 1, y - 2, rest - 1);
                    ways += pick(dp,x + 1, y - 2, rest - 1);
                    ways += pick(dp,x + 2, y - 1, rest - 1);
                    
                    dp[x,y,rest] = ways;
                }
            }
        }
        
        return dp[0, 0, k];
    }

    public static int pick(int[,,] dp, int x, int y, int rest)
    {
        if (x < 0 || x > 9 || y < 0 || y > 8)
        {
            return 0;
        }
        
        return dp[x, y, rest];
    }
}