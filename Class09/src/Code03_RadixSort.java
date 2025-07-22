import java.util.Arrays;

public class Code03_RadixSort {


    // only for no-negative value
    public static void radixSort(int[] arr)
    {
        if(arr == null || arr.length < 2) return;

        radixSort(arr,0,arr.length-1,maxbits(arr));
    }

    public static int maxbits(int[] arr)
    {
        int max = Integer.MIN_VALUE;
        for(int i = 0; i<arr.length; i++)
        {
            max = Math.max(max,arr[i]);
        }

        int res = 0;
        while(max!=0)
        {
            res++;
            max/=10;
        }
        return res;
    }

    // arr[L...R]排序 ， 最大值的十进制位数digit
    public static void radixSort(int[] arr, int L, int R, int digit)
    {
        final int radix =10;
        int i=0,j=0;
        // 有多少个数准备多少个辅助空间
        int[] help = new int[R - L + 1];
        for(int d = 1; d<= digit; d++ )
        {
            // 10个空间
            // count[0] 当前位数是0的数字有多少个
            // count[1] 当前位数是(0,1)的数字有多少个
            // count[2] 当前位数是(0,1和2)的数字有多少个
            // count[i] 当前位数是(0~i)的数字有多少个
            int[] count = new int[radix];
            for(i = L; i <= R; i++ ){
                // 103 1  3
                // 209 1  9
                j = getDigit(arr[i],d);
                count[j]++;
            }

            for(i= 1;i<radix;i++){
                count[i] = count[i-1] + count[i];
            }

            for(i = R; i >= L; i--){
                j = getDigit(arr[i],d);
                help[count[j] - 1] = arr[i];
                count[j]--;
            }

            for(i=L,j=0;i<=R;i++,j++){
                arr[i] = help[j];
            }
        }
    }

    public static int getDigit(int x,int d)
    {
        return ((x / ((int)Math.pow(10,d-1))) % 10);
    }

    // for test
    public static void comparator(int[] arr)
    {
        Arrays.sort(arr);
    }

    public static int[] generateRandomArray(int maxSize,int maxValue)
    {
        int size = (int)(Math.random() * maxSize) + 1;
        int[] arr = new int[size];
        for(int i = 0; i < size; i++)
            arr[i] = (int)(Math.random() * maxValue);
        return arr;
    }

    public static int[] copyArray(int[] arr)
    {
        int[] copy = new int[arr.length];
        for(int i = 0; i < arr.length; i++)
            copy[i] = arr[i];
        return copy;
    }

    public static Boolean isEqual(int[] arr1, int[] arr2)
    {
        if(arr1 == null && arr2 == null) return true;
        if(arr1 == null || arr2 == null) return false;
        if(arr1.length != arr2.length) return false;
        for(int i = 0; i < arr1.length; i++)
            if(arr1[i] != arr2[i]) return false;
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
        int testTimes = 500000;
        int maxSize = 100;
        int maxValue = 100000;
        boolean succeed = true;
        for(int i = 0;i<testTimes;i++)
        {
            int[] arr1 = generateRandomArray(maxSize,maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if(!isEqual(arr1,arr2))
            {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }

        System.out.println(succeed ? "Nice" : "Func");

        int[] arr =  generateRandomArray(maxSize,maxValue);
        printArray(arr);
        radixSort(arr);
        printArray(arr);
    }
}
