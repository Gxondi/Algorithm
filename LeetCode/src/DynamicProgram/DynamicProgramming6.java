package DynamicProgram;

import javax.lang.model.element.VariableElement;

//最长回文子序列
public class DynamicProgramming6 {
    public int GetLPSSubStr(String str){
        if (str == null || str.length() == 0){
            return 0;
        }
        char[] chars = str.toCharArray();
        return process(chars,0,chars.length-1);
    }

    private int process(char[] chars, int l, int r) {
        if (l == r){
            return 1;
        }
        if (l == r-1){
            return chars[l] == chars[r]?2:1;
        }
        //可能性1   i也不要j也不要
        int p1 = process(chars,l+1,r-1);
        //可能性2   i要，j不要
        int p2 = process(chars,l,r-1);
        //可能性3  i不要，j要
        int p3 = process(chars,l+1,r);
        //可能性4  i要j也要
        int p4 = chars[l] == chars[r]?(2+process(chars,l+1,r-1)):0;
        return Math.max(Math.max(p1,p2),Math.max(p3,p4));
    }
    /*
    * 动态规划
    * */
    public int GetLPSSubStrDp(String str){
        if (str == null || str.length() == 0){
            return 0;
        }
        char[] chars = str.toCharArray();
        int length = chars.length;
        int[][] dp = new int[length][length];
        dp[length - 1][length - 1] = 1;
        for (int i = 0; i < length - 1; i++) {
            dp[i][i] = 1;
            dp[i][i+1] = chars[i] == chars[i+1]?2:1;
        }
        for (int i = length-3; i >=0 ; i--) {
            for (int j = i + 2; j < length; j++) {
                //可能性1   i也不要j也不要
                int p1 = dp[i+1][j-1];
                //可能性2   i要，j不要
                int p2 = dp[i][j-1];
                //可能性3  i不要，j要
                int p3 = dp[i+1][j];
                //可能性4  i要j也要
                int p4 = chars[i] == chars[j]?(2+dp[i+1][j-1]):0;
                dp[i][j] = Math.max(Math.max(p1,p2),Math.max(p3,p4));
            }
        }
        return dp[0][length-1];
    }

    public static void main(String[] args) {
        String str = "123abcd321";
        DynamicProgramming6 dp = new DynamicProgramming6();
        int i = dp.GetLPSSubStr(str);
        int i1 = dp.GetLPSSubStrDp(str);
        System.out.println(i);
        System.out.println(i1);
    }
}






























