namespace Class23;

public class Code02_MinCoinsNoLimit
{
    public static Int32 minCoins(int[] arr, int aim)
    {
        return process(arr, 0, aim);
    } 
    
    // arr[index...]面值，每种面值张数自由选择，
    // 搞出rest正好这么多钱，返回最小张数
    public static Int32 process(int[] arr, int index, int rest)
    {
        if (index == arr.Length)
        {
            return rest == 0 ? 0 : Int32.MaxValue;
        }
        else
        {
            Int32 ans = Int32.MaxValue;
            for (int i = 0; i * arr[index] <= rest; i++)
            {
                if(process(arr, index + 1, rest-i * arr[index]) != Int32.MaxValue)
                    ans = Math.Min(ans,process(arr, index + 1, rest-i * arr[index]) + i);
            }
            return ans;
        }
        
    }

    public static int dp1(int[] arr, int aim)
    {
        int N = arr.Length;
        Int32[,] dp = new Int32[N + 1, aim + 1];

        for (int i = 0; i <= N; i++)
        {
            dp[i, 0] = 0;
        }

        for (int i = 1; i <= aim; i++)
        {
            dp[N,i] = Int32.MaxValue;
        }
        
        
        for (int i = N - 1; i >= 0; i--)
        {
            for (int j = 0; j <= aim; j++)
            {
                Int32 ans = Int32.MaxValue;
                for (int zhang = 0; zhang * arr[i] <= j; zhang++)
                {
                    if(dp[i+1,j - zhang * arr[i]] != Int32.MaxValue)
                        ans = Math.Min(ans,dp[i+1,j - zhang * arr[i]] + zhang);
                }
                dp[i, j] = ans;
            }
        }
        return dp[0,aim];
    }
    
    public static int dp2(int[] arr, int aim)
    {
        int N = arr.Length;
        Int32[,] dp = new Int32[N + 1, aim + 1];

        for (int i = 0; i <= N; i++)
        {
            dp[i, 0] = 0;
        }

        for (int i = 1; i <= aim; i++)
        {
            dp[N,i] = Int32.MaxValue;
        }
        
        
        for (int i = N - 1; i >= 0; i--)
        {
            for (int j = 0; j <= aim; j++)
            {
                Int32 ans = dp[i+1,j];
                if(j - arr[i] >=0
                   && dp[i,j-arr[i]] != Int32.MaxValue)
                    ans = Math.Min(dp[i, j - arr[i]] + 1, dp[i + 1, j]);
                dp[i, j] = ans;
            }
        }
        return dp[0,aim];
    }
}