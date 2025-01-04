package DynamicProgram;

import com.sun.xml.internal.bind.v2.model.runtime.RuntimeAttributePropertyInfo;
import sun.applet.Main;

public class DynamicProgramming {
    /*
    * 机器人走路
    * 返回值是，到达目的地有几次方法
    * start机器人开始的位置
    * rest剩余几步可以走
    * n 机器人可以活动的范围
    * target目标
    * */
    public int ways(int start , int rest , int n , int target){
        return process(start,rest,n,target);
    }

    public int process(int cur, int rest, int n, int target) {
        if (rest == 0){
            return cur == target?1:0;
        }
        if (cur == 1){
            return process(cur+1,rest-1,n,target);
        }
        if (cur == n){
            return process(cur-1,rest-1,n,target);
        }
        return process(cur-1,rest-1,n,target) + process(cur+1,rest-1,n,target);
    }
    public int ways2(int start , int k , int n , int target){
        int[][] dp = new int[n+1][k+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(start,k,n,target,dp);
    }
    public int process2(int cur, int rest, int n, int target,int[][] dp){
        if (dp[cur][rest] != -1){
            return dp[cur][rest];
        }
        int ans = 0;
       if (rest == 0){
           ans = cur == target?1:0;
       }else if (cur == 1){
           ans = process2(2,rest-1,n,target,dp);
       }else if (cur == n){
           ans = process2(cur-1,rest-1,n,target,dp);
       }else {
           ans = process2(cur-1,rest-1,n,target,dp) + process2(cur+1,rest-1,n,target,dp);
       }
       return ans;
    }
    //n是cur活动范围
    //k是 rest剩余步数活动范围
    //n是行，k是列
    public int ways3(int n , int start , int target , int k){
        int[][] dp = new int[n+1][k+1];
        dp[target][0] = 1; //target行0列
        for (int rest = 1; rest <= k; rest++) {//第一列开始
            dp[1][rest] = dp[2][rest-1];//第一行参考左下
            for (int cur = 2; cur < n; cur++) {
                dp[cur][rest] = dp[cur-1][rest-1]+dp[cur+1][rest-1];//参考左上左下
            }
            dp[n][rest] = dp[n-1][rest-1];//最后一行参考左上
        }
        return dp[start][k];
    }
    public static void main(String[] args) {
        DynamicProgramming dp = new DynamicProgramming();
        //从2 走到 4，可以走四步，1，2，3，4；
        int ways = dp.ways(2, 6, 5, 4);
        int ways2 = dp.ways2(2, 6, 5, 4);

        int ways3 = dp.ways3(5, 2, 4, 6);
        System.out.println(ways);
        System.out.println(ways2);
        System.out.println(ways3);
    }
}
