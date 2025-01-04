package DynamicProgram;

import java.awt.font.NumericShaper;
public class DynamicProgramming8 {
    public int GetMinWay(int[][] nums){
        if (nums == null||nums.length == 0|| nums[0] == null|| nums[0].length == 0){
            return 0;
        }
        int row = nums.length; // 行
        int col = nums[0].length; // 列
        int[][] dp = new int[row][col];
        dp[0][0] = nums[0][0];
        for (int i = 1; i < col; i++) {
            dp[0][i] = dp[0][i-1] + nums[0][i];
        }
        for (int j = 1; j < row; j++) {
            dp[j][0] = dp[j-1][0] + nums[j][0];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i][j-1],dp[i-1][j])+ nums[i][j];
            }
        }
        return dp[row-1][col-1];
    }
    public int GetMinWay1(int[][] m){
        if (m == null||m.length == 0|| m[0] == null|| m[0].length == 0){
            return 0;
        }
        int row = m.length; // 行
        int col = m[0].length; // 列
        int[] dp = new int[col];
        dp[0] = m[0][0];
        for (int i = 1; i < col; i++) {
            dp[i] = dp[i-1] + m[0][i];
        }
        for (int i = 1; i < row; i++) {
            dp[0] += m[i][0];
            for (int j = 1; j < col; j++) {
                dp[j] = Math.min(dp[j-1],dp[j]) + m[i][j];
            }
        }
        return dp[col-1];
    }

    public static void main(String[] args) {
        int[][] arr = {{1,3,5,9},{8,1,3,4},{5,0,6,1},{8,8,4,0}};
        DynamicProgramming8 dp = new DynamicProgramming8();
        int i = dp.GetMinWay(arr);
        int i1 = dp.GetMinWay1(arr);
        System.out.println(i);
        System.out.println(i1);
    }
}
