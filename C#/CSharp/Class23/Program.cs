using Class23;

class Program
{
    public static void Main()
    {
        // Console.WriteLine(Code03_SplitNumber.dp2(13));
        // Console.WriteLine(Code03_SplitNumber.dp1(13));
        // Console.WriteLine(Code03_SplitNumber.ways1(13));

        Console.WriteLine(Code02_MinCoinsNoLimit.minCoins(new int[] { 1, 2, 5 }, 13));
        Console.WriteLine(Code02_MinCoinsNoLimit.dp2(new int[] { 1, 2, 5 }, 13));
    
        Console.WriteLine(Code02_MinCoinsNoLimit.dp1(new int[] { 1, 2, 5 }, 13));
    }
}