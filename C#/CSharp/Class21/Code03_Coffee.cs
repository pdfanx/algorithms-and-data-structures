namespace Class21;

public class Code03_Coffee
{

    // drinks 所有杯子可以开始洗的时间
    // wash 单杯洗干净的时间
    // air 挥发干净的时间
    // free 洗的机器什么时候可用
    // dirnks
    // 业务限制模型 （限制） -- 样本对应模型 -- 范围约束模型
    public int bsetTime(int[] drinks, int wash, int air, int index, int free)
    {
        if (index == drinks.Length)
        {
            return 0;
        }

        // index 号杯子 决定洗
        int selfClean1 = Math.Max(drinks[index], free) + wash;
        int restClean1 = bsetTime(drinks, wash, air, index + 1, selfClean1);
        int p1 = Math.Max(selfClean1, restClean1);

        // index 号杯子 决定挥发
        int selfClean2 = drinks[index] + air;
        int restClean2 = bsetTime(drinks, wash, air, index + 1, free);
        int p2 = Math.Max(selfClean2, restClean2);

        return Math.Min(p1, p2);
    }

    public static int bestTimeDp(int[] drinks, int wash, int air)
    {
        int N = drinks.Length;
        int maxFree = 0;
        for (int i = 0; i < drinks.Length; i++)
        {
            maxFree = Math.Max(maxFree, drinks[i]) + wash;
        }
        
        int[,] dp = new int[N+1,maxFree+1  ];
        for (int index = N - 1; index >= 0; index--)
        {
            for (int free = 0; free <= maxFree; free++)
            {                
                // index 号杯子 决定洗
                int selfClean1 = Math.Max(drinks[index], free) + wash;
                if (selfClean1 > maxFree)
                {
                    continue;
                }
                
                int restClean1 = dp[index  + 1,selfClean1];
                int p1 = Math.Max(selfClean1, restClean1);

                // index 号杯子 决定挥发
                int selfClean2 = drinks[index] + air;
                int restClean2 =dp[index + 1 ,selfClean2];
                int p2 = Math.Max(selfClean2, restClean2);

                dp[index,free] = Math.Min(p2, p1);
            }
        }

        return dp[0, 0];
    }


    public class Machine
    {
        public int timePoint; // 可用时间点
        public int workTime; // 工作时间

        public Machine(int timePoint, int workTime)
        {
            this.timePoint = timePoint;
            this.workTime = workTime;
        }
    }


    // 比较器
    public class MachineComparer : IComparer<Machine>
    {
        public int Compare(Machine? x, Machine? y)
        {
            return (x.timePoint + x.workTime) - (y.timePoint + y.workTime);
        }
    }

    public static int[] minTime2(int[] arr, int n, int a, int b)
    {
        // PriorityQueue<TElement,TPriority> <存储元素，排序顺序>
        PriorityQueue<Machine, int> heap = new PriorityQueue<Machine, int>();

        for (int i = 0; i < arr.Length; i++)
        {
            Machine m = new Machine(0, arr[i]);
            heap.Enqueue(m, m.timePoint + m.workTime);
        }

        int[] drinks = new int[n];
        for (int i = 0; i < n; i++)
        {
            Machine cur = heap.Dequeue();
            cur.timePoint += cur.workTime;
            drinks[i] = cur.timePoint;
            heap.Enqueue(cur, cur.timePoint + cur.workTime);
        }

        return drinks;
    }
}

