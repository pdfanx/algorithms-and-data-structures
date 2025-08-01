import java.util.Arrays;

public class Code02_CountSort {

    // Only for 0~200 value
    public static void countSort(int[] arr)
    {
        if(arr == null || arr.length < 2)
            return;
        // 找arr的最大值
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++)
            max = Math.max(max, arr[i]);

        int[] bucket = new int[max+1];
        for(int i = 0; i < arr.length; i++)
            bucket[arr[i]]++;

        int i = 0;
        for(int j = 0; j < bucket.length; j++)
        {
            while (bucket[j]-- > 0)
            {
                arr[i++] = j;
            }
        }
    }

    public static void comparator(int[] arr)
    {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize,int maxValue)
    {
        int size = (int)(Math.random() * maxSize) + 1;
        int[] arr = new int[size];
        for(int i = 0;i<size;i++)
        {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static int[] copyArray(int[] arr)
    {
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            copy[i] = arr[i];

        return copy;
    }

    public static boolean isEqual(int[] arr1,int[] arr2)
    {
        if(arr1 == null && arr2 == null)
            return true;
        if(arr1 == null && arr2 != null)
            return false;
        if(arr1 != null && arr2 == null)
            return false;
        if(arr1.length != arr2.length)
            return false;
        for(int i = 0; i < arr1.length; i++)
        {
            if(arr1[i] != arr2[i])
                return false;
        }
        return true;
    }

    public static void printArray(int[] arr)
    {
        for(int a : arr)
        {
            System.out.print(a+" ");
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 150;
        boolean succeed = true;

        for(int i = 0;i<testTime;i++)
        {
            int[] arr1 = generateRandomArray(maxSize,maxValue);
            int[] arr2 = copyArray(arr1);
            countSort(arr1);
            comparator(arr2);
            if(!isEqual(arr1,arr2))
            {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
         System.out.println(succeed ? "Nice" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize,maxValue);
        printArray(arr);
        countSort(arr);
        printArray(arr);
    }
}
