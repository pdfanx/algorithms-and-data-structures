using System.Collections;

namespace Class22;

public class Code04_CoinsWaySameValueSamePapper
{
    public static int coinWay(int[] arr, int aim)
    {
        if(arr == null || arr.Length == 0 || aim < 0)
            return 0;
        
        Dictionary<int,int> map = new Dictionary<int,int>();

        for (int i = 0; i < arr.Length; i++)
        {
            if (map.ContainsKey(arr[i]))
                map[arr[i]] += 1;
            map.Add(arr[i], 1);
        }
        
        int[] value = map.Keys.ToArray();
        int[] zhang = map.Values.ToArray();
        
        return process(value,zhang, 0,aim);
    }

    private static int process(int[] arr, int[] zhang, int index, int rest)
    {
        if (index == arr.Length)
            return rest == 0 ? 1 : 0;
        int ways = 0;
        for (int number = 0; number <= zhang[index]; number++)
        {
            if(number * arr[index] <= rest)
                ways += process(arr, zhang, index + 1, rest -  number * arr[index]);
        }
        
        return ways;
    }

    private static int dp(int[] arr, int aim)
    {
        if(arr == null || arr.Length == 0 || aim < 0)
            return 0;
        Dictionary<int, int> map = new Dictionary<int, int>();
        for (int i = 0; i < arr.Length; i++)
        {
            if (map.ContainsKey(arr[i]))
                map[arr[i]] += 1;
            map.Add(arr[i], 1);
        }
        
        int[] momey = map.Keys.ToArray();
        int[] zhang = map.Values.ToArray();
        
        int N = momey.Length;
        int[,] dp = new int[N + 1, aim + 1];

        dp[N, 0] = 1;
        for (int i = 1; i <= aim; i++)
            dp[N, i] = 0;
        

        for (int index = N - 1; index >= 0; index--)
        {
            for (int rest = 0; rest <= aim; rest++)
            {
                int ways = dp[index + 1, rest];
                // for (int number = 0; number <= zhang[index]; number++)
                // {
                //     if (number * momey[index] <= rest)
                //         ways += dp[index + 1, rest - number * momey[index]];
                // }
                if (rest - momey[index] >= 0)
                {
                    ways += dp[index, rest - momey[index]];
                    if (rest - (zhang[index] + 1) * momey[index] > 0)
                    {
                        ways -= dp[index + 1, rest - (zhang[index] + 1) * momey[index]];
                    }
                }
                
                dp[index, aim] = ways;
            }
        }

        return dp[0, aim];
    }
    
}