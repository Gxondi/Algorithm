package DynamicProgram;

public class DynamicProgramming12 {
    public int GetMinCoin(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    //参数说明：
    //arr数组内的元素代表货币面值
    //index代表arr下标
    //rest代表剩下钱（组成目标aim，最小用多少长钱）
    //返回值:张数
    private int process(int[] arr, int index, int rest) {
        if (index == arr.length) {//钱到底了
            return rest == 0 ? 0 : Integer.MAX_VALUE;//钱到底了，并且目标钱也等于0了返回0张
        }
        //开始尝试,0张，1张，2张
        int ans = Integer.MAX_VALUE;
        for (int zhangs = 0; zhangs * arr[index] <= rest; zhangs++) {
            int next = process(arr, index + 1, rest - zhangs * arr[index]);//下一张开始尝试
            if (next != Integer.MAX_VALUE) {//下一张不等于无效值的话，加上当前值的张数。
                ans = Math.min(ans, next + zhangs);
            }
        }
        return ans;
    }
    public int GetMinCoinDp(int[] arr, int aim) {
        if (aim == 0){
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n+1][aim+1];
        dp[n][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0 ; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ans = Integer.MAX_VALUE;
                for (int zhangs = 0; zhangs * arr[index] <= rest; zhangs++) {
                    int next =dp[index + 1][rest - zhangs * arr[index]];//下一张开始尝试
                    if (next != Integer.MAX_VALUE) {//下一张不等于无效值的话，加上当前值的张数。
                        ans = Math.min(ans, next + zhangs);
                    }
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][aim];
    }
    public int GetMinCoinDp1(int[] arr, int aim) {
        if (aim == 0){
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n+1][aim+1];
        dp[n][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0 ; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index+1][rest];
                //rest - arr[index]>=0 保证左边有各自   dp[index][rest-arr[index]]!=Integer.MAX_VALUE保证左边各自数值正常
               if (rest - arr[index]>=0&&dp[index][rest-arr[index]]!=Integer.MAX_VALUE){
                   dp[index][rest] = Math.min(dp[index][rest-arr[index]]+1,dp[index][rest]);
               }
            }
        }
        return dp[0][aim];
    }
}
