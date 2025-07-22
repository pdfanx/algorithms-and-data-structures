import java.util.Arrays;
import java.util.PriorityQueue;

public class Code04_SortArrayDistanceLessK {

    // O(N * logK)
    public static void sortArrayDistanceLessK(int[] arr,int k)
    {
        if( k == 0)
            return;
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        // 0...K-1
        for(;index <= Math.min(arr.length - 1,k - 1);index++)
        {
            heap.add(arr[index]);
        }
        int i = 0;
        for(;index < arr.length;index++)
        {
            heap.add(arr[index]);
            arr[i++] = heap.poll();
        }
        while(!heap.isEmpty())
        {
            arr[i++] = heap.poll();
        }
    }

    public static int[] randomArrayNoMoreK(int maxSize,int maxValue,int k)
    {
        int[] arr = new int[(int)((maxSize+1)*Math.random())];
        for(int i = 0;i<arr.length;i++)
        {
            arr[i] = (int)((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
        }

        Arrays.sort(arr);
        boolean[] isSwap = new boolean[arr.length];
        for(int i = 0;i<arr.length;i++)
        {
            int j = Math.min(i + (int)(Math.random() * (k + 1)),arr.length-1);
            if(!isSwap[i] && !isSwap[j])
            {
                isSwap[i] = true;
                isSwap[j] = true;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        return arr;
    }

    public static int[] copyArray(int[] arr)
    {
        if(arr == null)
            return null;

        int[] copy = new int[arr.length];
        for(int i = 0;i<arr.length;i++)
        {
            copy[i] = arr[i];
        }

        return copy;
    }

    public static void comparator(int[] arr,int k)
    {
        if(arr == null)
            return;
        Arrays.sort(arr);
    }

    public static boolean isEqual(int[] arr1,int[] arr2 )
    {
        if(arr1 == null && arr2 != null || arr1 != null && arr2 == null)
            return false;
        if(arr1 == null && arr2 == null)
            return true;
        if(arr1.length != arr2.length)
            return false;
        for(int i = 0;i<arr1.length;i++)
        {
            if(arr1[i] != arr2[i])
                return false;
        }
        return true;
    }

    // for test
    public static void main(String[] args)
    {
        System.out.println("test begin");
        int testTimes = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for(int i =0;i<testTimes;i++)
        {
            int k = (int)(Math.random() * maxSize) + 1;
            int[] arr = randomArrayNoMoreK(maxSize, maxValue, k);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            sortArrayDistanceLessK(arr1,k);
            comparator(arr2,k);
            if(!isEqual(arr1,arr2))
            {
                succeed = false;
                System.out.println("K:" + k);
            }
        }

        System.out.println(succeed ? "Nice" : "Fucking fucked");
    }


}
