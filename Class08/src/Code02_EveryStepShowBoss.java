import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Code02_EveryStepShowBoss {

    public static class Customer
    {
        public int id;
        public int buy;
        public int enterTime;

        public Customer(int id, int buy,int enterTime) {
            this.id = id;
            this.buy = buy;
            this.enterTime = enterTime;
        }
    }

    public static class CandidateComparator implements Comparator<Customer>
    {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o2.buy - o1.buy == 0 ? o1.enterTime - o2.enterTime : o2.buy - o1.buy;
        }
    }

    public static class DaddyComparator implements Comparator<Customer>
    {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o2.buy - o1.buy == 0 ? o1.enterTime - o2.enterTime : o1.buy - o2.buy;
        }
    }

    public static List<Integer> getCurAns(List<Customer> customers)
    {
        List<Integer> ans = new ArrayList<>();
        for(Customer customer:customers)
        {
            ans.add(customer.id);
        }
        return ans;
    }

    public static void cleanZeroBuy(List<Customer> customers) {
        for(int i = 0; i < customers.size(); i++) {
            if(customers.get(i).buy <= 0) {
                customers.remove(i);
                i--;
            }
        }
    }

    public static void move(List<Customer> cands,List<Customer> daddy,int k, int time)
    {
        if(cands.isEmpty()) return ;

        if(daddy.size() < k)
        {
            Customer cand = cands.get(0);
            cand.enterTime = time;
            daddy.add(cand);
            cands.remove(0);
        }else {
            if(cands.get(0).buy > daddy.get(0).buy) {
                Customer cand = cands.get(0);
                Customer oldDaddy = daddy.get(0);
                daddy.remove(0);
                cands.remove(0);
                cand.enterTime = time;
                oldDaddy.enterTime = time;
                daddy.add(cand);
                cands.add(oldDaddy);
            }
        }
    }

    // 干完所有的事情不优化
    public static List<List<Integer>> TopK(int[] arr,boolean[] op,int k)
    {
        // 储存进入获奖区和候选区的用户
        HashMap<Integer,Customer> map = new HashMap<>();
        List<Customer> cands = new ArrayList<>();
        List<Customer> daddy = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0;i < arr.length;i++)
        {
            int id = arr[i];
            boolean buyOrRefund = op[i];
            if( !buyOrRefund && !map.containsKey(id))
            {
                ans.add(getCurAns(daddy));
                continue;
            }

            // 没有发生：用户购买数为0并且又退货了
            // 用户之前购买数是0，此时买货
            // 用户之前购买数>0，此时买货
            // 用户之前购买数>0，此时卖货
            if(!map.containsKey(id))
            {
                map.put(id, new Customer(id,0,0));
            }
            // 买，卖
            Customer c = map.get(id);
            if(buyOrRefund)
            {
                c.buy++;
            }else {
                c.buy--;
            }
            if(c.buy == 0)
            {
                map.remove(id);
            }

            if(!cands.contains(c) && !daddy.contains(c))
            {
                if(daddy.size() < k)
                {
                    c.enterTime = i;
                    daddy.add(c);
                }else{
                    c.enterTime = i;
                    cands.add(c);
                }
            }
            cleanZeroBuy(cands);
            cleanZeroBuy(daddy);
            cands.sort(new CandidateComparator());
            daddy.sort(new DaddyComparator());
            move(cands,daddy,k,i);
            ans.add(getCurAns(daddy));
        }
        return ans;
    }
}
