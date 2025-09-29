namespace Class20___Dynamic_Programming;

public class Code04_LongestCommonSubsquence
{
    public static int LongestCommonSubsequence(string s, string t)
    {
        if(s == null || t == null || s.Length == 0 ||  t.Length == 0)
            return 0;
        char[] str1 = s.ToCharArray();
        char[] str2 = t.ToCharArray();

        return process1(str1, str2, str1.Length - 1, str2.Length - 1);
    }
    
    // str1[0...i]与str2[0...j]位置的公共子序列有多长
    // 返回
    public static int process1(char[] str1, char[] str2,int i,int j)
    {
        if (i == 0 && j == 0)
        {
            return str1[i] == str2[j] ? 1 : 0;
        }
        else if (i == 0)
        {
            if(str2[j] == str1[i])
                return 1;
            else
            {
                return process1(str1, str2, i, j - 1);
            }
        }
        else if (j == 0)
        {
            if(str2[j] == str1[i])
                return 1;
            else
            {
                return process1(str1, str2, i - 1, j);
            }
        }
        else
        {
            // 样本对应模型
            int p1 = process1(str1, str2, i - 1, j);
            int p2 = process1(str1, str2, i , j - 1);
            int p3 = str1[i] == str2[j] ? (1 + process1(str1, str2, i - 1, j - 1)) : 0; 
            
            return Math.Max(p1, Math.Max(p2, p3));
        }
    }
    
    public static int LongestCommonSubsequence2(string s, string t)
    {
        if(s == null || t == null || s.Length == 0 ||  t.Length == 0)
            return 0;
        char[] str1 = s.ToCharArray();
        char[] str2 = t.ToCharArray();
        int[,] dp = new int[str1.Length, str2.Length];
        
        for (int i = 0; i < str1.Length; i++)
        {
            for (int j = 0; j < str2.Length; j++)
            {
                dp[i, j] = -1;
            }
        }
            
        return process2(str1, str2, str1.Length - 1, str2.Length - 1,dp);
    }
    
    public static int process2(char[] str1, char[] str2,int i,int j,int[,] dp)
    {
        if(dp[i,j] != -1)
            return dp[i,j];
        
        int ans = 0;
        if (i == 0 && j == 0)
        {
            ans = str1[i] == str2[j] ? 1 : 0;
        }
        else if (i == 0)
        {
            if (str2[j] == str1[i])
            {
                ans = 1;
            }
            else
            {
                ans = process2(str1, str2, i, j - 1,dp);
            }
        }
        else if (j == 0)
        {
            if(str2[j] == str1[i])
                ans = 1;
            else
            {
                ans = process2(str1, str2, i - 1, j,dp);
            }
        }
        else
        {
            // 样本对应模型
            int p1 = process2(str1, str2, i - 1, j,dp);
            int p2 = process2(str1, str2, i , j - 1,dp);
            int p3 = str1[i] == str2[j] ? (1 + process2(str1, str2, i - 1, j - 1,dp)) : 0; 
            
            ans = Math.Max(p1, Math.Max(p2, p3));
        }
        dp[i, j] = ans;
        return dp[i, j];
    }
    
    // 动态规划
    public static int LongestCommonSubsequence3(string s, string t)
    {
        if(s == null || t == null || s.Length == 0 ||  t.Length == 0)
            return 0;
        char[] str1 = s.ToCharArray();
        char[] str2 = t.ToCharArray();
        int[,] dp = new int[str1.Length, str2.Length];
        
        dp[0, 0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < str2.Length; i++)
        {
            dp[0, i] = str1[1] == str2[i] ? 1 : dp[0, i - 1];
        }

        for (int i = 1; i < str1.Length; i++)
        {
            dp[i, 0] = str1[i] == str2[i] ? 1 : dp[i - 1, 0];
        }

        for (int i = 1; i < str1.Length; i++)
        {
            for (int j = 1; j < str2.Length; j++)
            {
                int p1 = dp[i-1,j];
                int p2 = dp[i,j-1];
                int p3 = str1[i] == str2[j] ? (1 + dp[i-1,j-1]) : 0; 
                
                dp[i, j] = Math.Max(p1, Math.Max(p2, p3));
            }
        }
            
        return dp[str1.Length-1, str2.Length-1];
    }
}