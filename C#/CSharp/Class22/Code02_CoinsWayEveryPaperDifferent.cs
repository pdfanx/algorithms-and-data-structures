namespace Class22;

public class Code02_CoinsWayEveryPaperDifferent
{
    public static int coinWays(int[] arr, int aim)
    {
        return process(arr, 0, aim);
    }
    
    // arr[index...]面值，每种面值张数自由选择
    // 搞出rest正好这么多钱，返回最小张数
    // 拿Integer.Max标记怎么都搞不定
    public static int process(int[] arr, int index, int rest)
    {
        if (index == arr.Length)
        {
            return rest == 0 ? 0 : Int32.MaxValue;
        }
        else
        {
            int ans = Int32.MaxValue;
            for (int i = 0; i * arr[index] <= rest; i++)
            {
                int next = process(arr, index + 1, rest - i * arr[index]);
                if(next != Int32.MaxValue)
                    ans = Math.Min(ans,next+ i);
            }
            return ans;
        }
    }

    public static int coinWays2(int[] arr, int aim)
    {
        if(aim == 0)
            return 0;
        
        // 索引 剩余金钱
        int N = arr.Length;
        int[,] dp = new int[N + 1, aim + 1];
        
        dp[N, 0] = 0;

        for (int i = 1; i <= aim; i++)
        {
            dp[N,i] = Int32.MaxValue;
        }

        for (int i = N - 1; i >= 0; i--)
        {
            for (int rest = 0; rest <= aim; rest++)
            {
                dp[i,rest] = dp[i + 1, rest];
                if (rest - arr[i] >= 0
                    && dp[i,rest-arr[i]] != Int32.MaxValue)
                {
                    int next = dp[i,rest-arr[i]];
                    if(next != Int32.MaxValue)
                        dp[i,rest] = Math.Min(dp[i,rest],next+ 1);
                }
            }
        }

        return dp[0, aim];
    }
    
    // for test
    public static int[] randomArray(int maxLen, int maxValue)
    {
        Random r = new Random();
        int len = r.Next(maxLen);
        
        int[] arr = new int[len];
        for (int i = 0; i < len; i++)
        {
            arr[i] = r.Next(maxValue);
        }
        return arr;
    }

    public static void printArray(int[] arr)
    {
        for (int i = 0; i < arr.Length; i++)
        {
            Console.Write(arr[i] + " ");
        }
        Console.WriteLine();
    }
    
    
}