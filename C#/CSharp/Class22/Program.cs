// See https://aka.ms/new-console-template for more information

using Class22;

class Program
{
    public static void Main()
    {
        int maxLen = 20;
        int maxValue = 30;
        int times = 30000;
        Random random = new Random();
        for (int i = 1; i <= times; i++)
        {
            int N = random.Next(maxLen);
            int[] arr = Code02_CoinsWayEveryPaperDifferent.randomArray(N, maxValue);
            int aim = random.Next(maxValue);
            int ans = Code02_CoinsWayEveryPaperDifferent.coinWays(arr, aim);
            int ans2 = Code02_CoinsWayEveryPaperDifferent.coinWays2(arr, aim);
            if (ans != ans2)
            {
                Console.WriteLine("ERROR");
                Console.Write(ans+" "+ans2);
                Console.WriteLine();
                Code02_CoinsWayEveryPaperDifferent.printArray(arr);
                Console.WriteLine(aim);
                break;
            }
        }
        Console.WriteLine("Done");
    }
}