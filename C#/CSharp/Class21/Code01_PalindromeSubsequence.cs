namespace Class21;

public class Code01_PalindromeSubsequence
{
    public static int lspl1(string s)
    {
        if(s == null || s.Length == 0)
            return 0;
        
        char[] arr = s.ToCharArray();
        return f(arr, 0, arr.Length - 1);
    }
    
    // 范围尝试模型：开头和结尾
    public static int f(char[] str, int L, int R)
    {
        if(L == R)
            return 1;
        if (L == R - 1)
        {
            return str[L] == str[R] ? 2 : 1;
        }
        
        // 即不以L开头，也不以R开头
        int p1 = f(str, L + 1, R - 1);
        int p2 = f(str, L , R - 1);
        int p3 = f(str, L + 1, R );
        int p4 = str[L] == str[R] ? 2 + f(str,L + 1, R - 1) : 0;
        return Math.Max(Math.Max(p1,p4), Math.Max(p2, p3));
    }
    
    // 动态规划
    public static int lspl2(string s)
    {
        if (s == null || s.Length == 0)
            return 0;
        int N = s.Length;
        char[] arr = s.ToCharArray();
        int[,] dp = new int[N, N];
        dp[N - 1, N - 1] = 1;

        for (int i = 0; i < N - 1; i++)
        {
            dp[i, i] = 1;
            dp[i, i + 1] = arr[i] == arr[i + 1] ? 2 : 1;
        }

        for (int i = N - 3; i >= 0; i--)
        {
            for (int j = i + 2; j < N; j++)
            {
                int p1 = dp[i + 1, j - 1];
                int p2 = dp[i, j - 1];
                int p3 = dp[i + 1, j];
                int p4 = arr[i] == arr[j] ? 2 + dp[i + 1, j - 1] : 0;
                return Math.Max(Math.Max(p1, p4), Math.Max(p2, p3));
            }
        }
        return dp[0, N - 1];
    }
}