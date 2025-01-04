package DynamicProgram;
/*
* 规定1对应A 2对应B
* 字符串111转化成 AAA KA AK 有几种转换方式
* */
public class DynamicProgramming3 {
    public int GetMaxCount(String s,int index){
        if (s ==null||s.length()==0){
            return 0;
        }
        return process(s.toCharArray(),0);
    }

    private int process(char[] str, int index) {
        if (index == str.length){
            return 1;
        }
        if (str[index] == '0'){
            return 0;
        }
        /*
        * 问题点：
        * 1.肯定是两两结合，不会是三个因为三个必然超过27个字符大小
        * 可能是1个 2个 1个 2个
        * */
        //可能性1 单转
        int p1 = process(str,index+1);
        //可能性2 i+1（两个字符串结合）因为要组合当前的i和i+1的字母所以两个要相加不大于27
        //所以这个地方要乘10，成为十位数。
        if(index+1<str.length&&(str[index]- '0')*10  + str[index+1] -'0'<27){
            p1+=process(str,index+2);
        }
        return p1;
    }
    public int DPGetMaxCount(String str){
        char[] chars = str.toCharArray();
        int length = chars.length;
        int[] dp = new int[length+1];
        dp[length] = 1;
        for (int i = length-1; i >=0 ; i--) {
            if (chars[i]!='0'){
                int p1 = dp[i+1];
               if ( i+1<length&&(dp[i]-'0')*10 + dp[i+1]-'0'<27){
                   p1 += dp[i+2];
               }
               dp[i] = p1; // 记录！！！！！把新的到值记录到表中
            }
        }
        return dp[0];
    }
    public static void main(String[] args) {
        DynamicProgramming3 dp = new DynamicProgramming3();
        String str = "111";
        int i = dp.GetMaxCount(str,0);
        int i1 = dp.DPGetMaxCount(str);
        System.out.println(i1);
        System.out.println(i);
    }
}
