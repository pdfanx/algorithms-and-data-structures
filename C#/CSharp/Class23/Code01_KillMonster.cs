namespace Class23;

public class Code01_KillMonster
{
    public static float KillMonster(int N, int M, int K)
    {
        int n = (int)Math.Pow(M + 1, K);
        return process(N,M,K) / (float)n;
    }

    public static int process(int rest, int M, int K)
    {
        if(K == 0)
            return rest <= 0 ? 1 : 0;

        int ways = 0;
        for (int i = 0; i <= M; i++)
        {
            ways += process(rest - i, M, K - 1);
        }
        
        return ways;
    }
    
    
    
    public static float dp(int N, int M, int K)
    {
        int n = (int)Math.Pow(M + 1, K);
        
        int[,] dp = new int[K + 1, N + 1];
        dp[0, 0] = 1;
        for (int i = 1; i <= N; i++)
        {
            dp[0, i] = 0;
        }

        for (int i = 1; i <= K; i++)
        {
            dp[i, 0] = (int)Math.Pow(M + 1, i);
        }
        
        for (int i = 1; i <= K; i++)
        {
            for (int j = 1; j <= N; j++)
            {
                dp[i, j] = dp[i - 1, j] + dp[i, j - 1];
                if(j - 1 - M >= 0)
                    dp[i, j] -= dp[i - 1, j - 1 - M];
                else
                {
                    dp[i, j] -= (int)Math.Pow(M + 1, i - 1);
                }
            }
        }

        return dp[K, N] / (float)n;;
    }
}