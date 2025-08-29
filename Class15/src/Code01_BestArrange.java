import java.util.Arrays;
import java.util.Comparator;

public class Code01_BestArrange {
    public static class Program
    {
        public int start;
        public int end;

        public Program(int start, int end)
        {
            this.start = start;
            this.end = end;
        }
    }

    public static int bestArrange1(Program[] programs)
    {
        if(programs == null || programs.length == 0)
            return 0;

        return process(programs,0,0);
    }

    public static int process(Program[] programs, int done, int timeLine)
    {
        if(programs.length == 0)
        {
            return done;
        }

        int max = done;

        for(int i = 0 ; i < programs.length ; i++)
        {
            if(programs[i].start >= timeLine)
            {
                Program[] next = copyButExcept(programs,i);
                max = process(next,done+1,programs[i].end);
            }
        }
        return max;
    }

    public static Program[] copyButExcept(Program[] programs, int i)
    {
        Program[] copy = new Program[programs.length - 1];
        int index = 0;
        for(int j = 0 ; j < programs.length ; j++)
        {
            if(index == i)
                continue;

            copy[index++] = programs[j];
        }

        return copy;
    }

    public static int bestArrange2(Program[] programs)
    {
        // 按结束时间 从小到大排序
        Arrays.sort(programs, new Comparator<Program>() {

            @Override
            public int compare(Program o1, Program o2) {
                return o1.end - o2.end;
            }
        });

        int timeLine = 0;
        int result = 0;

        for (Program program : programs) {
            if(timeLine <= program.start)
            {
                timeLine = program.end;
                result++;
            }
        }
        return result;
    }
}
