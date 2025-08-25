import java.time.Period;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code03_MaxHappy {

    public static class Employee
    {
        public int happy;
        public List<Employee> children;

        public Employee(int happy)
        {
            this.happy = happy;
            this.children = new ArrayList<>();
        }
    }

    public static class Info
    {
        public int no;
        public int yes;

        public Info(int no, int yes)
        {
            this.no = no;
            this.yes = yes;
        }
    }

    public static int maxHappy1(Employee head)
    {
        return Math.max(process(head).yes,process(head).no);
    }

    public static Info process(Employee x)
    {
        if(x == null)
            return new Info(0,0);

        int yes = x.happy;
        int no = 0;

        for(Employee child : x.children)
        {
            no += Math.max(process(child).yes,process(child).no);
            yes += process(child).no;
        }

        return new Info(no,yes);
    }

    public static int maxHappy2(Employee boss)
    {
        if(boss == null)
            return 0;

        return process2(boss, false);
    }

    // 当前节点叫cur
    // up表示cur的上级来不来
    public static int process2(Employee cur, boolean up)
    {
        if(up)
        {
            int ans = 0;
            for(Employee child : cur.children)
            {
                ans += process2(child,false);
            }
            return ans;
        }
        else
        {
            int p1 = cur.happy;
            int p2 = 0;
            for(Employee child : cur.children)
            {
                p1 += process2(child,true);
                p2 += process2(child,false);
            }
            return Math.max(p1,p2);
        }
    }

    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts ,int maxhappy)
    {
        if(Math.random() < 0.02)
        {
            return null;
        }

        Employee boss = new Employee((int)(Math.random()*maxhappy) + 1 );
        generateNexts(boss,1,maxLevel,maxNexts,maxhappy);
        return boss;

    }

    public static void generateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy)
    {
        if(level > maxLevel)
        {
            return;
        }

        int maxSize = (int)(Math.random() * maxNexts) + 1;
        for(int i = 0; i < maxSize; i++)
        {
            Employee child = new Employee((int)(Math.random()*maxHappy) + 1);
            e.children.add(child);
            generateNexts(child,level+1,maxLevel,maxNexts,maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy1(boss) != maxHappy2(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
