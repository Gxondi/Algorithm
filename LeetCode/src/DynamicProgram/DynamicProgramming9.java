package DynamicProgram;
//从左往右尝试模型
public class DynamicProgramming9 {
    public int coinWays(int[] arr , int aim){
        if (arr == null || arr.length == 0){
            return 0;
        }
        return process(arr,0,aim);
    }

    private int process(int[] arr, int index, int rest) {
        if (rest < 0){
            return 0;
        }
        if (index == arr.length){
            return rest == 0?1:0;
        }else {//要一个不要一个，两种截然不同的返回值相加
            return process(arr,index+1,rest) + process(arr,index+1,rest - arr[index]);
        }
    }
    public int coinWaysDp(int[] arr , int aim){
        if (arr == null || arr.length == 0){
            return 0;
        }
        if (aim == 0){
            return 1;
        }
        int n = arr.length;
        int dp[][] = new int[n+1][aim];
        dp[n][0] = 1;
        for (int index = 1; index >=0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index-1][rest] + (rest-arr[index]>=0?dp[index+1][rest-arr[index]]:0);
            }
        }
        return dp[0][aim];
    }

}
