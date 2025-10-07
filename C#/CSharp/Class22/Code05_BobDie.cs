namespace Class22;

public class Code05_BobDie
{
    // 给定5个参数，N，M，row，col，k
    // 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
    // Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
    // 任何时候Bob只要离开N*M的区域，就直接死亡
    // 返回k步之后，Bob还在N*M的区域的概率

    public static float BobDie(int N, int M, int row, int col, int k)
    {
        if (N < 0 || M < 0 || row < 0 || col < 0 || k < 0 || row > N || col > M)
        {
            return 0.0f;
        }
        
        int[,] arr = new int[N, M];
        
        return process(arr, row, col, k) / (float)Math.Pow(4,k);
    }

    public static int process(int[,] arr, int row, int col, int k)
    {
        if (k == 0)
        {
            return arr.GetLength(0) > row && arr.GetLength(1) > col ? 1 : 0 ;
        }
        
        if (row >= arr.GetLength(0) || col >= arr.GetLength(1))
        {
            return 0;
        }

        int ways = 0;
        ways += process(arr, row + 1,col, k -1);
        ways += process(arr, row,col + 1, k -1);
        ways += process(arr, row - 1,col, k -1);
        ways += process(arr, row,col - 1, k -1);
        
        return ways;
    }
    
    public static float BobDie2(int N, int M, int row, int col, int k)
    {
        if (N < 0 || M < 0 || row < 0 || col < 0 || k < 0 || row > N || col > M)
        {
            return 0.0f;
        }
        
        int[,,] arr = new int[N + 1, M + 1,k + 1];
        for (int i = 1; i <= N; i++)
        {
            for (int j = 1; j <= M; j++)
            {
                arr[i, j, 0] = 1;
            }
        }

        for (int K = 1; K <= k; K++)
        {
            for (int i = 1; i <= N; i++)
            {
                for (int j = 1; j <= M; j++)
                {
                    int ways = 0;
                    if (i + 1 <= N)
                        ways += arr[i, j, K - 1];
                    if (j + 1 <= M)
                        ways += arr[i, j + 1, K - 1];
                    if (j - 1 >= 1)
                        ways += arr[i, j - 1, K - 1];
                    if (i - 1 >= 1)
                        ways += arr[i - 1, j, K - 1];

                    arr[i, j, K] = ways;
                }
            }
        }

        return arr[row, col, k];

    }

}