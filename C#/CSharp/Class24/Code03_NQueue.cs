namespace Class24;

public class Code03_NQueue
{
    public static int num1(int n)
    {
        if(n < 1)
            return 0;
        
        int[] record = new int[n];
        return process(0,record,n);
    }

    public static int process(int i, int[] record, int n)
    {
        if(i == n)
            return 1;

        int res = 0;
        for (int j = 0; j < n; j++)
        {
            if (isValid(record, i, j))
            {
                record[i] = j;
                res += process(i + 1, record, n);
            }
        }
        
        return res;
    }

    private static bool isValid(int[] record, int i, int j)
    {
        for (int k = 0; k < i; k++)
        {
            if(record[k] == j || Math.Abs(record[k] - j) == - k + i)
                return false;
        }
        return true;
    }
    
    // 优化常数时间
    public static int num2(int n)
    {
        if(n < 1 || n > 32)
            return 0;

        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process1(limit, 0, 0, 0);
    }
    
    // limit : 0 1 1 1
    // 
    public static int process1(int limit, int colLimit, int leftLim, int rightLim)
    {
        if (colLimit == limit)
            return 1;
        
        int res = 0;
        int pos = limit & (~(colLimit | leftLim | rightLim));
        int mostRightOne = 0;
        while (pos != 0)
        {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process1(limit, colLimit | mostRightOne, (leftLim | mostRightOne) << 1,
                (rightLim | mostRightOne) >> 1);
        }
        
        return res;
    }
}