namespace Class24;

public class Code01_SpiltSumClosed
{
    public static int right(int[] arr)
    {
        if (arr == null || arr.Length < 2)
        {
            return 0;
        }
        int sum = 0;
        foreach (int var in arr)
        {
            sum += var;
        }
        return process(arr,0,sum>>1);
    }

    // arr[i...]可以自由选择，请返回累加和尽量接近rest,但不能超过rest的情况下，最接近的累加和是多少？
    public static int process(int[] arr, int i, int rest)
    {
        if(i == arr.Length)
            return 0;
        else
        {
            int p1 = process(arr, i + 1, rest);
            int p2 = 0;
            if (arr[i] <= rest)
            {
                p2 = arr[i] + process(arr, i + 1, rest - arr[i]);
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
        foreach (int var in arr)
        {
            sum += var;
        }
        int N = arr.Length;
        int rest = sum >> 1;
        int[,] dp = new int[N + 1, rest + 1];
        
        for (int i = 0; i <= rest; i++)
        {
            dp[N, i] = 0;   // 越界，返回0，累加
        }

        for (int i = N - 1; i >= 0; i--)
        {
            for (int j = 0; j <= sum >> 1; j++)
            {
                int p1 = dp[i + 1,j];
                int p2 = 0;

                if (arr[i] <= j)
                {
                    p2 = arr[i] + dp[i + 1,j - arr[i]];
                }
                
                dp[i, j] = Math.Max(p1, p2);
            }
        }
        
        return dp[0, sum >> 1];
    }
}