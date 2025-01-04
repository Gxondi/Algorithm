package DynamicProgram;

import sun.java2d.marlin.DPathConsumer2D;

public class DynamicProgramming11 {
    public double KillMonster(int i, int m,int k){
        if (i<1||m<1||k<1){
            return 0;
        }
        long all = (long)Math.pow(m+1,k);
        long kill = process(i,m,k);
        return (double)((double)kill/(double) all);
    }
    //参数说明
    //hp怪物血量
    //0-m随机伤害
    //可以砍多少次
    private long process(int hp, int m, int times) {
        if (times == 0){
            return hp<=0?1:0;
        }
        if (hp<=0){
            return (long) Math.pow(m+1,times);
        }
        long way = 0;
        for (int i = 0; i <=m; i++) {
                way += process(hp-i,m,times-1);
        }
        return way;
    }
    public double KillMonsterDp(int n, int m,int k){
        if (n<1||m<1||k<1){
            return 0;
        }
        long all = (long)Math.pow(m+1,k);
        long[][] dp = new long[k+1][n+1];
        dp[0][0] = 1;
        for (int time = 1; time < k; time++) {
            dp[time][0] = (long) Math.pow(m+1,k);
            for (int hp = 1; hp <= n; hp++) {
                long way = 0;
                for (int i = 0; i <= m; i++) {
                    if (hp - i >= 0){
                        way += dp[time - 1][hp - i];
                    }
                    way += (long) Math.pow(m+1,time-1);
                }
                dp[time][n] = way;
            }
        }
        long kill = dp[k][n];
        return (double)((double)kill/(double) all);
    } public double KillMonsterDp1(int n, int m,int k){
        if (n<1||m<1||k<1){
            return 0;
        }
        long all = (long)Math.pow(m+1,k);
        long[][] dp = new long[k+1][n+1];
        dp[0][0] = 1;
        for (int time = 1; time < k; time++) {
            dp[time][0] = (long) Math.pow(m+1,k);
            for (int hp = 1; hp <= n; hp++) {
               dp[time][hp] = dp[time-1][hp] + dp[time][hp-1];
               if (hp - m - 1>=0){
                    dp[time][hp] -= dp[time-1][hp-1-m];
               }else {
                   dp[time][hp] -= Math.pow(m+1,time-1);
               }
            }
        }
        long kill = dp[k][n];
        return (double)((double)kill/(double) all);
    }
    public static void main(String[] args) {
    }
}
