import java.util.Comparator;
import java.util.PriorityQueue;

public class Code02_Heap {

    public static class MyMaxHeap
    {
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit)
        {
            this.heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty()
        {
            return heapSize == 0;
        }

        public boolean isFull()
        {
            return heapSize == limit;
        }

        public void push(int value)
        {
            if(heapSize == limit)
            {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = value;

            heapInsert(heap,heapSize++);
        }

        public int pop()
        {
            int ans = heap[0];
            swap(heap,0,--heapSize);
            heapify(heap,0,heapSize);
            return ans;
        }

        private void heapInsert(int[] arr,int index)
        {
            while (arr[index] > arr[(index-1) / 2])
            {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int[] arr,int index,int size)
        {
            int left = index * 2 + 1;
            while(left < size) {
                // 使用三目运算符要细心
                int largest = left + 1 < size && arr[left] < arr[left + 1] ? left + 1 : left;
                largest = arr[index] > arr[largest] ? index : largest;
                if(largest == index) {
                    break;
                }
                swap(arr, index, largest);
                index = largest;
                left = index * 2 + 1;
            }
        }

        private void swap(int[] arr,int index1,int index2)
        {
            int temp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = temp;
        }
    }

    public static class RightMaxHeap{
        private int[] heap;
        private final int limit;
        private int heapSize;

        public RightMaxHeap(int limit)
        {
            this.heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty()
        {
            return heapSize == 0;
        }

        public boolean isFull()
        {
            return heapSize == limit;
        }

        public void push(int value)
        {
            if(heapSize == limit)
            {
                throw new RuntimeException("heap is full");
            }

            heap[heapSize++] = value;
        }

        public int pop()
        {
            int maxIndex = 0;
            for(int i = 0;i<heapSize;i++)
            {
                if(heap[i] > heap[maxIndex])
                {
                    maxIndex = i;
                }
            }
            int ans = heap[maxIndex];
            // 要将maxIndex的值去除掉
            heap[maxIndex] = heap[--heapSize];
            return ans;
        }

    }

    public static class MyComparator implements Comparator<Integer>
    {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static void main(String[] args)
    {
        // 小根堆 / 大跟堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(new MyComparator());
        pq.add(5);
        pq.add(5);
        pq.add(3);
        pq.add(3);
        // 5 , 3
        System.out.println(pq.peek());
        pq.add(8);
        pq.add(7);
        pq.add(0);
        pq.add(2);
        System.out.println(pq.peek());
        while(!pq.isEmpty())
        {
            System.out.println(pq.poll());
        }

        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;

        for(int i = 0;i < testTimes; i++)
        {
            int curLimit = (int)(Math.random() * limit) + 1;
            MyMaxHeap myMaxHeap = new MyMaxHeap(curLimit);
            RightMaxHeap rightMaxHeap = new RightMaxHeap(curLimit);
            int curOpTimes = (int)(Math.random() * limit);
            for(int j = 0;j < curOpTimes; j++)
            {
                if(myMaxHeap.isEmpty() != rightMaxHeap.isEmpty())
                {
                    System.out.println("oops!");
                }
                if(myMaxHeap.isFull() != rightMaxHeap.isFull())
                {
                    System.out.println("oops!");
                }
                if(myMaxHeap.isEmpty())
                {
                    int curValue = (int)(Math.random() * value);
                    myMaxHeap.push(curValue);
                    rightMaxHeap.push(curValue);
                }else if(myMaxHeap.isFull())
                {
                    if(myMaxHeap.pop() != rightMaxHeap.pop())
                    {
                        System.out.println("oops!");
                    }
                }else {
                    if(Math.random() < 0.5)
                    {
                        int curValue = (int)(Math.random() * value);
                        myMaxHeap.push(curValue);
                        rightMaxHeap.push(curValue);
                    }else {
                        if(myMaxHeap.pop() != rightMaxHeap.pop())
                        {
                            // System.out.println(myMaxHeap.pop() + " " + rightMaxHeap.pop());
                            System.out.println("oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }

}
