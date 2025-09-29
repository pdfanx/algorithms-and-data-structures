class Code01_Knapsack
{
    // 所有的货，质量和价值，都在w和v数组中
    // 为了方便，其中没有负数
    // bag背包容量，不能超过这个载重
    // 返回：B超重的情况下的最大价值
    public static int maxValue(int[] w, int[] v, int bag)
    {
        if (w == null || v == null || w.Length != v.Length || w.Length == 0)
        {
            return 0;
        }

        return process(w, v, 0, bag);
    }
    
    // 当前考虑到了index号货物，index...所有货物可以自由选择
    // 做的选择不能超过最大容量，
    // 返回最大价值
    public static int process(int[] w, int[] v, int index, int bag)
    {
        if(bag < 0)
            return -1;
        
        if(index == w.Length)
            return 0;
        
        // index没到最后 
        // bag有空间
        
        // 1.不要当前的货物
        int p1 = process(w, v, index + 1, bag);
        
        // 2.要当前的货物
        
        // 处理无效解
        int next =  process(w, v, index + 1, bag - w[index]);
        int p2 = 0;
        if (next != -1)
        {
            p2 = v[index] + next;
        }
        
        return Math.Max(p1, p2);
    }

    public static int dp(int[] w, int[] v, int bag)
    {
        if (w == null || v == null || w.Length != v.Length || w.Length == 0)
        {
            return 0;
        }
        
        // index : 0 ~ N
        // rest  : 0 ~ bag
        int N = w.Length;
        int[,] dp = new int[N + 1,bag + 1];
        
        // 填表
        for (int index = N - 1; index >= 0; index--)
        {
            for (int rest = 0; rest <= bag; rest++)
            {
                int p1 = dp[index + 1, rest];
                int p2 = 0;
                int next = rest - w[index] < 0 ? -1 : dp[index + 1, rest - w[index]];

                if (next != -1)
                {
                    p2 =v[index] + dp[index + 1, rest - w[index]];
                }
                
                dp[index,rest] = Math.Max(p1, p2);
            }
        }
        
        return dp[0, bag];
    }

    // public static void Main(string[] args)
    // {
    //     int[] weights = { 3, 2, 4, 7 };
    //     int[] values = { 5, 6, 3, 19 };
    //     int bag = 11;
    //     System.Console.WriteLine(maxValue(weights, values, bag));
    //     System.Console.WriteLine(dp(weights, values, bag));
    // }
    
}