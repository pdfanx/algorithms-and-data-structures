import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code01_CoverMax {

    // O(N^2)
    public static int coverMax02(int[][] m)
    {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < m.length; i++)
        {
            max = Math.max(max, m[i][1]);
            min = Math.min(min, m[i][0]);
        }
        int ans = 0;
        for(double p = min + 0.5; p < max; p += 1)
        {
            int cur = 0;
            for(int i = 0; i < m.length; i++)
            {
                if(m[i][0] < p && m[i][1] > p)
                    cur++;
            }
            ans = Math.max(ans, cur);
        }
        return ans;
    }


    // O(N * logN)
    public static int coverMax(int[][] m)
    {
        Line[] lines = new Line[m.length];
        for(int i = 0; i < m.length; i++)
        {
            lines[i] = new Line(m[i][0], m[i][1]);
        }

        Arrays.sort(lines, new StartComparator());

        PriorityQueue<Line> heap = new PriorityQueue<>();
        int max = 0;
        for(int i = 0; i < lines.length; i++)
        {
            while(!heap.isEmpty() && heap.peek().end <= lines[i].start)
            {
                heap.poll();
            }

            heap.add(lines[i]);
            max = Math.max(max, heap.size());
        }

        return max;
    }

    public static class Line
    {
        public int start;
        public int end;

        public Line(int start, int end)
        {
            this.start = start;
            this.end = end;
        }
    }

    public static class StartComparator implements Comparator<Line>
    {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

}
