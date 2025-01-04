package DynamicProgram;

import sun.applet.Main;

//下象棋，马走日，x 9 y 10 出发点是0，0 目标a,b 走k步，有几种走法
public class DynamicProgramming7 {
    public int getMaxWays(int a,int b, int k){
        return process(a,b,k,0,0);
    }
    //目标A，B, rest步数，出发点x,y
    private int process(int a, int b, int restWays, int x, int y) {
        if (x < 0 || x > 9 || y < 0 || y > 8){
            return 0;
        }
        if (restWays == 0){
            return (x == a&&y == b)?1:0;
        }
        int ways = process(a,b,restWays-1,x+2,y+1);
        ways += process(a,b,restWays-1,x+1,y+2);
        ways += process(a,b,restWays-1,x-1,y+2);
        ways += process(a,b,restWays-1,x-2,y+1);
        ways += process(a,b,restWays-1,x-2,y-1);
        ways += process(a,b,restWays-1,x-1,y-2);
        ways += process(a,b,restWays-1,x+1,y-2);
        ways += process(a,b,restWays-1,x+2,y-1);
        return ways;
    }
    public int getMaxWaysDp(int a,int b, int k){
        int[][][] dp = new int[10][9][k+1];
        dp[a][b][0] = 1;
        for (int rest = 1; rest <=k; rest++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    int ways = pick(dp,x+2,y+1,rest-1);
                    ways += pick(dp,x+1,y+2,rest-1);
                    ways += pick(dp,x-1,y+2,rest-1);
                    ways += pick(dp,x-2,y+1,rest-1);
                    ways += pick(dp,x-2,y-1,rest-1);
                    ways += pick(dp,x-1,y-2,rest-1);
                    ways += pick(dp,x+1,y-2,rest-1);
                    ways += pick(dp,x+2,y-1,rest-1);
                    dp[x][y][rest] = ways;
                }
            }
        }
        return dp[0][0][k];
    }

    private int pick(int[][][] dp, int x, int y, int i) {
        if (x < 0 || x > 9 || y < 0 || y > 8){
            return 0;
        }
        return dp[x][y][i];
    }

    public static void main(String[] args) {
        DynamicProgramming7 dp = new DynamicProgramming7();
        int maxWays = dp.getMaxWays(7, 7, 10);
        int maxWaysDp = dp.getMaxWaysDp(7, 7, 10);
        System.out.println(maxWays);
        System.out.println(maxWaysDp);
    }
}
