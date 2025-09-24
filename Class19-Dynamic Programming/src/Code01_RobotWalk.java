public class Code01_RobotWalk {
    public static int ways1(int n, int start,int aim,int K)
    {
        return process1(start,n,aim,K);
    }

    // 机器人当前来到的位置是cur
    // 机器人还有rest步要走
    // 最终目标是aim
    // 有哪些位置? 1~N
    // 返回：机器人从cur出发，走过rest步之后，最终停在aim的方法数，是多少？
    // cur,rest是状态key ,出现重复解的暴力递归可以优化
    public static int process1(int cur,int rest,int aim,int N)
    {
        if(rest == 0)
        {
            return cur == aim ? 1 : 0;
        }

        // rest > 0
        if(cur == 1)
        {
            return process1(2,rest - 1, aim , N);
        }

        if(cur == N)
        {
            return process1(N - 1,rest - 1, aim , N);
        }

        return process1(cur - 1,rest - 1, aim , N)
                + process1(cur + 1,rest - 1, aim , N);
    }

    public static int ways2(int N, int start,int aim,int K)
    {
        int[][] dp = new int[N+1][K+1];
        for(int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++)
            {
                dp[i][j] = -1;
            }
        }

        // dp是缓存表
        // dp[cur][rest] == -1 => process1(cur,rest)之前没算过
        // dp[cur][rest] != -1 => process1(cur,rest)之前算过
        // N+1 * K+1

        return process2(start,N,aim,K,dp);
    }

    // 从顶向下的动态规划，记忆化搜索
    // cur      :     1~N
    // rest     :     0~K
    public static int process2(int cur,int rest,int aim,int N,int[][] dp)
    {
        if(dp[cur][rest] != -1)
        {
            return dp[cur][rest];
        }

        int ans = 0;
        if(rest == 0)
        {
            ans = cur == aim ? 1 : 0;
        }else if(cur == 1)
        {
            ans = process2(2,rest - 1, aim , N, dp);
        }else if(cur == N)
        {
            ans = process2(N - 1,rest - 1, aim , N, dp);
        } else {
            ans = process2(cur - 1,rest - 1, aim , N, dp) + process2(cur + 1,rest - 1, aim , N, dp);
        }
        // 记缓存
        dp[cur][rest] = ans;
        return ans;
    }

    public static int ways3(int N, int start,int aim,int K)
    {
        int[][] dp = new int[N+1][K+1];
        dp[aim][0] = 1;


        for(int rest = 1; rest <= K; rest++)    // 列
        {
            dp[1][rest] = dp[2][rest-1];
            for(int cur = 2; cur < N; cur++)
            {
                dp[cur][rest] = dp[cur-1][rest -1] + dp[cur+1][rest-1];
            }
            dp[N][rest] = dp[N-1][rest -1];
        }


        return dp[start][K];
    }

    public static void main(String[] args) {
        System.out.println(ways1(4,2,4,4));
        System.out.println(ways2(4,2,4,4));
    }
}
