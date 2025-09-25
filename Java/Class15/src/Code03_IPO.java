import java.util.Comparator;
import java.util.PriorityQueue;

public class Code03_IPO {

    public static class Program {
        public int p;
        public int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static int findMaximizedCapital(int K,int W,int[] Profits,int[] Capital)
    {
        PriorityQueue<Program> minCostQ = new PriorityQueue<Program>(new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o1.c - o2.c;
            }
        });

        PriorityQueue<Program> maxProfitQ = new PriorityQueue<Program>(new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o2.p - o1.p;
            }
        });

        for(int i = 0; i < Profits.length; i++)
        {
            minCostQ.add(new Program(Profits[i],Capital[i]));
        }

        for(int i = 0; i < K; i++)
        {
            while(!minCostQ.isEmpty() && minCostQ.peek().c <= W)
            {
                maxProfitQ.add(minCostQ.poll());
            }
            if(maxProfitQ.isEmpty())
                return W;


            W += maxProfitQ.poll().p;
        }

        return W;
    }
}
