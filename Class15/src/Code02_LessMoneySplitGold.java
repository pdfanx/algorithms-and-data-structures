import java.util.Comparator;
import java.util.PriorityQueue;

public class Code02_LessMoneySplitGold {

    public static int lessMoney(int[] arr)
    {
        if(arr == null || arr.length == 0)
        {
            return 0;
        }

        return process(arr , 0);
    }

    // 等待合并的数都在arr里，pre之前的合并行为产生了多少总代价
    // arr 中只剩余一个数字的时候，停止合并，返回产生的最小的总代价
    public static int process(int[] arr, int pre)
    {
        if(arr.length == 1)
        {
            return pre;
        }

        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = i+1; j < arr.length; j++)
                ans = Math.min(ans,process(copyAndMergeTwo(arr,i,j),pre + arr[i]+arr[j]));
        }
        return ans;
    }

    public static int[] copyAndMergeTwo(int[] arr,int i,int j)
    {
        int a = arr[i] + arr[j];
        int[] ans = new int[arr.length - 1];
        ans[0] = a;
        int index = 1;
        for(int m =0; m < arr.length; m++)
        {
            if(m == i || m == j)
            {
                continue;
            }
            ans[index++] = arr[m];
        }

        return ans;
    }

    public static int lessMoney2(int[] arr)
    {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for(int i = 0; i < arr.length; i++)
            pq.add(arr[i]);

        int sum = 0;
        int cur = 0;
        while(pq.size() > 1)
        {
            cur = pq.poll() + pq.poll();
            sum += cur;
            pq.add(cur);
        }
        return sum;
    }
}
