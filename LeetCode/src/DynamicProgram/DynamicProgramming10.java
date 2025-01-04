package DynamicProgram;

import sun.java2d.marlin.DPathConsumer2D;

import java.util.HashMap;
import java.util.Map;

public class DynamicProgramming10 {
    public class info{
        public  int[] coins;
        public int[] zhangs;

        public info(int[] coins, int[] zhangs) {
            this.coins = coins;
            this.zhangs = zhangs;
        }

        public info() {
        }

    }
    public info GetInfo(int[] arr){
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int vaule : arr) {
            if (!hashMap.containsKey(vaule)){
                hashMap.put(vaule,1);//集合中不存在v，把v加入（key = v），并且词频更新为1
            }else {
                hashMap.put(vaule,hashMap.get(vaule)+1);
            }
        }
        int N = hashMap.size();
        int[] coin = new int[N];
        int[] zhangs = new int[N];
        int index = 0;
        for (Map.Entry<Integer, Integer> Entry : hashMap.entrySet()) {
             coin[index] = Entry.getKey();
             zhangs[index] = Entry.getValue();
        }
        return new info(coin,zhangs);
    }
    public int coinWays(int[] arr, int aim){
        if (arr == null||arr.length == 0|| aim<0){
            return 0;
        }
        info info = GetInfo(arr);
        return process(info.coins,info.zhangs,0,arr,aim);
    }

    private int process(int[] coins, int[] zhangs, int index, int[] arr, int rest) {
        if (index == arr.length){
            return rest == 0?1:0;
        }
        int way = 0;
        for (int zhang = 0; zhang*coins[index] <= rest - coins[index] && zhang <= zhangs[index]; zhang++) {
            way += process(coins,zhangs,index+1,arr,rest - (zhang * arr[index]));
        }
        return way;
    }
    public int coinWaysDp(int[] arr, int aim){
        if (arr == null||arr.length == 0|| aim<0){
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n+1][aim+1];
        info info = GetInfo(arr);
        int[] coins  = info.coins;//面值
        int[] zhangs = info.zhangs;//张数
        dp[n][0] = 1;
        for (int index = n-1;index >= 0; index--) {
            for (int rest = 0; rest <= aim ; rest++) {
                int way = 0;
                for (int zhang = 0; zhang*coins[index] <= rest - coins[index] && zhang <= zhangs[index]; zhang++) {
                    way += dp[index+1][rest - (zhang * arr[index])];
                }
                dp[index][rest] = way;
            }
        }
        return dp[0][aim];
    }
    public int coinWaysDp1(int[] arr, int aim){//空间优化
        if (arr == null||arr.length == 0|| aim<0){
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n+1][aim+1];
        info info = GetInfo(arr);
        int[] coins  = info.coins;//面值
        int[] zhangs = info.zhangs;//张数
        dp[n][0] = 1;
        for (int index = n-1;index >= 0; index--) {
            for (int rest = 0; rest <= aim ; rest++) {
                int way = 0;
                dp[index][rest] = dp[index+1][rest];
                if (rest-coins[index] >= 0){
                    dp[index][rest] += dp[index][rest - coins[index]];
                }
                if (rest-coins[index]*(zhangs[index]+1)>=0){
                    dp[index][rest] -= dp[index+1][rest-coins[index]*(zhangs[index]+1)];
                }
            }
        }
        return dp[0][aim];
    }
}
