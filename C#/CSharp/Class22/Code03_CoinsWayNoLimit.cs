namespace Class22;

public class Code03_CoinsWayNoLimit
{
    public static int process(int[] arr, int index, int rest)
    {
        if (index == arr.Length)
        {
            return rest == 0 ? 1 : 0;
        }

        int ways = 0;
        for (int i = 0; i * arr[index] <= rest; i++)
        {
            ways += process(arr, index + 1, rest -  i * arr[index]);
        }
        
        return ways;
    }

    public static int dp(int[] arr, int aim)
    {
        if (arr == null || arr.Length == 0 || aim < 0)
            return 0;
        
        int N = arr.Length;
        int[,] dp = new int[N + 1, aim + 1];
        dp[N, 0] = 1;

        for (int i = 1; i <= aim; i++)
        {
            dp[N, i] = 0;
        }
        
        for (int index = N - 1; index >= 0; index--)
        {
            for (int rest = 0; rest <= aim; rest++)
            {
                int ways = dp[index + 1, rest];

                if (rest - arr[index] >= 0)
                    ways += dp[index, rest - arr[index]];
                dp[index, rest] = ways;
            }
        }

        return dp[0, aim];
    }
}