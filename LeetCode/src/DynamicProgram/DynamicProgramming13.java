package DynamicProgram;

import Graph.Kalgorithm;
import sun.print.SunMinMaxPage;
//给定一个数组arr 把数组分成两个集合，尽可能让两个集合累加和接近，返回最接近的情况下较小集合的的累加和
public class DynamicProgramming13 {
    public int right(int[] arr){
        if (arr == null|| arr.length <2){
            return 0;
        }
        int sum = 0;
        for (int i : arr) {
            sum+=i;
        }

        return process(arr,0,sum>>1);
    }

    private int process(int[] arr, int index, int rest) {
        if (index == arr.length){
            return 0;
        }
        int p1 = process(arr,index+1,rest);
        int p2 = 0;
        if (arr[index]<=rest){
            p2 = arr[index] + process(arr,index+1,rest - arr[index]);
        }
        return Math.max(p1,p2);
    }
    public int rightDp(int[] arr){
        if (arr == null|| arr.length <2){
            return 0;
        }
        int sum = 0;
        for (int i : arr) {
            sum+=i;
        }
        sum/=2;
        int n = arr.length;
        int[][] dp = new int[n+1][sum+1];
        for (int i = n-1; i >=0 ; i--) {
            for (int rest = 0; rest <= sum; rest++) {
                int p1 = dp[i+1][rest];
                int p2 = 0;
                if (arr[i]<=rest){
                    p2 = arr[i] + dp[i+1][rest - arr[i]];
                }
               dp[i][rest] = Math.max(p1,p2);
            }
        }
        return dp[0][sum];
    }

    public static void main(String[] args) {
        int[] arr = {4,3,5,1};
        DynamicProgramming13 dp = new DynamicProgramming13();
        int right = dp.right(arr);
        System.out.println(right);
    }
}
