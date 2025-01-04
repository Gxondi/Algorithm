package DynamicProgram;

/*
* 给定一个数组w代表重量
* 给定一个数组v代表价值
* 给定一个bag，在不超过bag重量限制下，返回最大价值
* */
public class DynamicProgramming2 {
    public int GetMaxValue(int[] w,int[] v,int bag){
        if (bag<=0||w==null||v==null||w.length!=v.length){
            return 0;
        }
        return process(0,w,v,bag);
    }
    /*
    * 返回最大价值
    * */
    private int process(int index, int[] w, int[] v, int rest) {
        if (rest<0){
            return -1;
        }
        if (index == w.length){
            return 0;
        }
        //不选择当前的
        int p1 = process(index+1,w,v,rest);
        //选择当前的
        int p2 = 0;
        //获取当前的下一个，返回-1说明当前bag的重量已经满了 参数：index+1下一个
        //                                             参数：rest-w[index]：bag重量-当前重量
        int next = process(index+1,w,v,rest - w[index]);
        if (next!=-1){
            //   获取当前价值
            p2 = v[index]+next;
        }
        return Math.max(p1,p2);
    }
    public int DPGetMaxValue(int[] w,int[] v,int bag){
        int n = w.length;
        int[][] dp = new int[n+1][bag+1];
        for (int index = n-1; index >=0 ; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index+1][rest];
                int p2 = 0;
                int next = rest - w[index] < 0?-1:dp[index+1][rest-w[index]];
                if (next!=-1){
                    p2 = v[index] + next;
                }
                dp[index][rest] = Math.max(p1,p2);
            }
        }
        return dp[0][bag];
    }
    public static void main(String[] args) {
        int[] w = {3,2,4,7};
        int[] v = {5,6,3,19};
        int bag = 11;
        DynamicProgramming2 dp = new DynamicProgramming2();
        int i1 = dp.DPGetMaxValue(w, v, bag);
        int i = dp.GetMaxValue(w, v, bag);
        System.out.println(i);
        System.out.println(i1);
    }
}
