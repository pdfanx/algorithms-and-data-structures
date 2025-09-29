using System.Collections;
using System.Text;

namespace Class20___Dynamic_Programming;

public class Code03_StickersToSpellWord
{
    public static int minStickers1(string[] stickers, String target)
    {
        int ans = process1(stickers, target);
        return ans == Int32.MaxValue ? -1 : ans;
    }

    public static int process1(string[] stickers, String target)
    {
        if (target == String.Empty)
        {
            return 0;
        }
        
        int min = Int32.MaxValue;
        foreach (string sticker in stickers)
        {
            string rest = minus(target, sticker);
            if (rest.Length != target.Length)
            {
                min = Math.Min(min, process1(stickers, rest));
            }
        }
        return min + (min == Int32.MaxValue ? 0 : 1);
    }

    public static string minus(string target, string sticker)
    {
        char[] targetChar = target.ToCharArray();
        char[] stickerChar = sticker.ToCharArray();
        int[] count = new int[26];

        foreach (char c in targetChar)
        {
            count[c - 'a']++;
        }

        foreach (char c in stickerChar)
        {
            count[c - 'a']--;
        }
        
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 26; i++)
        {
            if(count[i] == 0)
                str.Append((char)(i + 'a'));
        }
        
        return str.ToString();
    }

    // stickers[i] 数组，当初i号贴纸统计 int[][] stickers -> 所有贴纸
    // 每种贴纸都有无穷张
    // 返回搞定target 的最小张数
    // 最小张数
    public static int process2(int[][] stickers, String target)
    {
        if(target.Length == 0)
            return 0;
        
        // 对target做词频统计
        char[] targetArray = target.ToCharArray();
        int[] tcount = new int[26];
        foreach (char c in targetArray)
        {
            tcount[c - 'a']++;
        }
        
        int N =  stickers.Length;
        int min = Int32.MaxValue;
        for (int i = 0; i < N; i++)
        {
            int[] sticker = stickers[i];
            // 关键优化  贪心算法
            if (sticker[targetArray[0] - 'a'] > 0)
            {
                StringBuilder str = new StringBuilder();
                for (int j = 0; j < 26; j++)
                {
                    if (tcount[j] > 0)
                    {
                        int nums = tcount[j] - sticker[j];
                        for (int k = 0; k < nums; k++)
                        {
                            str.Append((char)(j + 'a'));
                        }
                    }
                }
                string rest = str.ToString();
                min = Math.Min(min, process2(stickers, rest));
            }
        }
        return min + (min == Int32.MaxValue ? 0 : 1);
    }
    
    public static int process3(int[][] stickers, String target,Hashtable hashMap)
    {
        if(hashMap.ContainsKey(target))
            return (int)(hashMap[target] ?? Int32.MaxValue);
        
        if(target.Length == 0)
            return 0;
        
        // 对target做词频统计
        char[] targetArray = target.ToCharArray();
        int[] tcount = new int[26];
        foreach (char c in targetArray)
        {
            tcount[c - 'a']++;
        }
        
        int N =  stickers.Length;
        int min = Int32.MaxValue;
        for (int i = 0; i < N; i++)
        {
            int[] sticker = stickers[i];
            // 关键优化  贪心算法
            if (sticker[targetArray[0] - 'a'] > 0)
            {
                StringBuilder str = new StringBuilder();
                for (int j = 0; j < 26; j++)
                {
                    if (tcount[j] > 0)
                    {
                        int nums = tcount[j] - sticker[j];
                        for (int k = 0; k < nums; k++)
                        {
                            str.Append((char)(j + 'a'));
                        }
                    }
                }
                string rest = str.ToString();
                min = Math.Min(min, process3(stickers, rest, hashMap));
            }
        }
        hashMap.Add(target, min + (min == Int32.MaxValue ? 0 : 1));
        return (int)(hashMap[target] ?? Int32.MaxValue);
    }
}