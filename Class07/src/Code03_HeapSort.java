import java.util.Arrays;

public class Code03_HeapSort {
    public static void HeapSort(int[] arr)
    {
        if(arr == null || arr.length < 2)
            return;
        // 1.创建堆
        // 自下而上 O(N)
        for(int i = arr.length -1;i>=0;i--)
        {
            heapify(arr,i,arr.length);
        }

        // 自上而下 O(N * logN)
        /*
        for(int i = 0;i < arr.length;i++)
        {
            heapInsert(arr,i);
        }
        */

        // 2.排序
        int heapSize = arr.length;
        // O(N * logN)
        while(heapSize > 0)     // O(N)
        {
            swap(arr,0,--heapSize);
            heapify(arr,0,heapSize);    // O(logN)
        }
    }

    public static void heapInsert(int[] arr,int index)
    {
        while(arr[index] > arr[(index -1) / 2])
        {
            swap(arr,index,(index -1) / 2);
            index = (index -1) / 2;
        }
    }

    public static void heapify(int[] arr,int index,int heapSize)
    {
        int left = 2 * index + 1;
        while(left < heapSize)
        {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if(largest == index)
                break;

            swap(arr,index,largest);
            index = largest;
            left = 2 * index + 1;
        }
    }


    public static void swap(int[] arr,int index1,int index2)
    {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    // for test

    public static int[] generateRandomArray(int maxSize,int maxValue)
    {
        int size = (int) (Math.random() * maxSize) + 1;
        int[] arr = new int[size];
        for(int i =0;i<arr.length;i++)
        {
            arr[i] = (int)(Math.random() * maxValue);
        }
        return arr;
    }

    public static int[] copyArr(int[] arr)
    {
        int[] Array =  new int[arr.length];
        for(int i=0;i<arr.length;i++)
        {
            Array[i] = arr[i];
        }
        return Array;
    }

    public static void comparator(int[] arr)
    {
        Arrays.sort(arr);
    }

    public static boolean isEqual(int[] arr1,int[] arr2)
    {
        if((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null))
            return false;
        if(arr1 == null && arr2 == null)
            return true;
        if(arr1.length != arr2.length)
            return false;
        for(int i=0;i<arr1.length;i++)
        {
            if(arr1[i] != arr2[i])
                return false;
        }
        return true;
    }

    public static void printArray(int[] arr)
    {
        for(int i=0;i<arr.length;i++)
        {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        int testTimes = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for(int i =0;i<testTimes;i++)
        {
            int[] arr1= generateRandomArray(maxSize,maxValue);
            int[] arr2 = copyArr(arr1);
            HeapSort(arr1);
            comparator(arr2);
            if(!isEqual(arr1,arr2))
            {
                succeed = false;
                break;
            }
        }

        System.out.println(succeed ? "Nice!" : "Fucking Fucked!");

        int[] arr =  generateRandomArray(maxSize,maxValue);
        printArray(arr);
        HeapSort(arr);
        printArray(arr);
    }
}
