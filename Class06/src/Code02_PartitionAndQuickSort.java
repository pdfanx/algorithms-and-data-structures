public class Code02_PartitionAndQuickSort {
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i]^arr[j];
        arr[j] = arr[i]^arr[j];
        arr[i] = arr[i]^arr[j];
    }

    // arr[L...R],以arr[R]来划分值
    // <= X > X
    // <= X X
    public static int partition(int[] arr, int L, int R) {
        if( L > R )
            return -1;
        if( L == R )
            return L;

        int lessEqual = L - 1;
        int index = L;
        while(index < R) {
            if(arr[index] < arr[R])
                swap(arr, index, ++lessEqual);
            index++;
        }
        swap(arr,++lessEqual,R);
        return lessEqual;
    }

    public static int[] netherlandsSort(int[] arr,int L, int R) {
        if(L > R)
            return new int[] { -1 , -1};
        if(L == R)
            return new int[] {L , L};

        int less = L - 1;
        int more = R;
        int index = L;

        while(index < more)
        {
            if(arr[index] == arr[R])
            {
                index++;
            }
            else if(arr[index] < arr[R])
                swap(arr, index++, ++less);
            else if(arr[index] > arr[R])
                swap(arr, index, --more);
        }

        swap(arr,more,R);
        return new int[] { less + 1,more };
    }

    // QuickSort--01
    public static void quickSort01(int[] arr) {
        if(arr == null || arr.length < 2)
            return;

        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int[] arr, int L, int R) {
        if( L >= R)
        {
            return;
        }
        int M = partition(arr, L, R);

        process1(arr, L, M - 1);
        process1(arr, M + 1, R);
    }

    // QuickSort -- 02
    public static void QuickSort02(int[] arr) {
        if(arr == null || arr.length < 2)
            return;

        process2(arr, 0, arr.length - 1);
    }

    public static void process2(int[] arr, int L, int R) {
        if(L >= R)
            return;

        int[] M = netherlandsSort(arr, L, R);

        process2(arr,L,M[0]-1);
        process2(arr,M[1]+1,R);
    }

    // QuickSort -- 03
    public static void quickSort(int[] arr) {
        if(arr == null || arr.length < 2)
            return;

        process(arr,0,arr.length-1);
    }

    public static void process(int[] arr, int L, int R) {
        if(L >= R)
            return;

        // 随机交换
        swap(arr, (int)(Math.random() * (R - L + 1)) + L, R);
        int[] M = netherlandsSort(arr, L, R);
        process(arr,L,M[0]-1);
        process(arr,M[1]+1,R);
    }


    // For Test

}
