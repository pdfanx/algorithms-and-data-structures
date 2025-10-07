namespace Class22;

public class Code01_MinPathSum
{
    public static int minPathSum2(int[][] m)
    {
        if (m == null || m.Length == 0 || m[0] == null || m[0].Length == 0)
        {
            return 0;
        }

        int row = m.Length;
        int col = m[0].Length;
        int[] arr = new int[col];
        arr[0] = m[0][0];
        for (int j = 1; j < col; j++)
        {
            arr[j] = arr[j - 1] + m[0][j];
        }
        
        // dp[0][...] -> arr
        for (int i = 1; i < row; i++)
        {
            arr[0] += m[i][0];
            for (int j = 1; j < col; j++)
            {
                // arr[j - 1] -> 左侧
                // arr[j]    -> 上
                arr[j] = Math.Min(arr[j - 1], arr[j]) +  m[i][j];
            }
        }

        return arr[col - 1];
    }
}