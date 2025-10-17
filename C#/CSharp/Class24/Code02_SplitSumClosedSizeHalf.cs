namespace Class24;

public class Code02_SplitSumClosedSizeHalf
{
    // arr[i...] <= rest 最近的返回 
    // arr[i...]自由选择，挑选的个数一定要是picks个，列加和<= rest, 离rest最近的返回
    public static int right(int[] arr)
    {
        if (arr == null || arr.Length < 2)
        {
            return 0;
        }

        int sum = 0;
        foreach (int x in arr)
        {
            sum += x;
        }

        if ((arr.Length & 1) == 0)
        {
            return process(arr, 0, arr.Length / 2, sum / 2);
        }
        else
        {
            int p1 = process(arr, 0, arr.Length / 2, sum / 2);
            int p2 = process(arr, 0, arr.Length / 2 + 1, sum / 2);
            return Math.Max(p1, p2);
        }
    }
    
    
    public static int process(int[] arr, int i, int picks, int rest)
    {
        if(i == arr.Length)
            return picks == 0 ? 0 : -1;
        else
        {
            int p1 = process(arr, i+1, picks, rest);
            int p2 = -1;
            int next = -1;
            if (rest >= arr[i])
            {
                next = process(arr, i + 1, picks - 1, rest - arr[i]);
            }

            if (next != -1)
            {
                p2 = next + arr[i];
            }
            
            return Math.Max(p1, p2);
        }
    }

    public static int dp(int[] arr)
    {
        if (arr == null || arr.Length < 2)
        {
            return 0;
        }

        int sum = 0;
        foreach (int x in arr)
        {
            sum += x;
        }

        sum /= 2;
        int N = arr.Length;
        int M = (N + 1) / 2;
        int[,,] dp = new int[N + 1, M + 1, sum + 1];

        for (int i = 0; i <= N; i++)
        {
            for (int j = 0; j <= M; j++)
            {
                for (int k = 0; k <= sum; k++)
                {
                    dp[i, j, k] = -1;
                }
            }
        }

        for (int rest = 0; rest <= sum; rest++)
        {
            dp[N,0, rest] = 0;
        }

        for (int i = N - 1; i >= 0; i--)
        {
            for (int j = 0; j <= M; j++)
            {
                for (int rest = 0; rest <= sum; rest++)
                {
                    int p1 = dp[i + 1, j, rest];
                    int p2 = -1;
                    int next = -1;
                    if (arr[i] <= rest && j - 1 >=0)
                    {
                        next = dp[i+1,j - 1, rest -arr[i]];
                    }

                    if (next != -1)
                    {
                        p2 = arr[i] + next;
                    }
                    
                    dp[i,j,rest] = Math.Max(p1, p2);
                }
            }
        }
        
        
        if ((arr.Length & 1) == 0)
        {
            return dp[0,arr.Length / 2,sum];
        }
        else
        {
            int p1 = dp[0,arr.Length / 2,sum];
            int p2 = dp[0,arr.Length / 2 + 1, sum];
            return Math.Max(p1, p2);
        }
    }
} 