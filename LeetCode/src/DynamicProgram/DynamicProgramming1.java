package DynamicProgram;
/*
* 给定一个数组代表数值不同的支派，排成一排
* 玩家a，b以此拿走一张牌
* 玩家只能最左最右拿牌
* 玩家ab都绝顶聪明，返回最后获胜者分数
* */
public class DynamicProgramming1 {
    public int process(int[] arr){
        int n = arr.length;
        int first = f(arr,0,n-1);
        int second = g(arr,0,n-1);
        return Math.max(first,second);
    }
    public int f(int[] arr, int L, int R){
        if (L == R){
            return arr[L];
        }
        int p = arr[L] + g(arr,L+1,R);
        int p1 = arr[R] + g(arr,L,R-1);
        return Math.max(p,p1);
    }

    public int g(int[] arr, int L, int R) {
        if (L == R){
            return 0;
        }
        int p = f(arr,L+1,R);
        int p1 = f(arr,L,R-1);
        return Math.min(p,p1);
    }
    //加入缓存dp
    public int process1(int[] arr){
        int n = arr.length;
        int[][] fmap = new int[n][n];
        int[][] gmap = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }
        int first = f1(arr,0,n-1,fmap,gmap);
        int second = g1(arr,0,n-1,fmap,gmap);
        return Math.max(first,second);
    }
    public int f1(int[] arr, int L, int R,int[][] fmap,int[][] gmap){
       if (fmap[L][R]!=-1){
           return fmap[L][R];
       }
       int ans = 0;
       if (L == R){
           ans = arr[L];
       }else {
           int p = arr[L] + g1(arr,L+1,R,fmap,gmap);
           int p1 = arr[R] + g1(arr,L,R-1,fmap,gmap);
           ans =  Math.max(p,p1);
       }
       fmap[L][R] = ans;
        return ans;
    }

    public int g1(int[] arr, int L, int R,int[][] fmap,int[][] gmap) {
        if (gmap[L][R]!=-1){
            return gmap[L][R];
        }
        int ans = 0;
        if (L!=R){
            int p = f1(arr,L+1,R,fmap,gmap);
            int p1 = f1(arr,L,R-1,fmap,gmap);
            ans = Math.min(p,p1);
        }
       gmap[L][R] = ans;
        return ans;
    }
    //继续优化，动态规划
    public int process3(int[] arr){
        int n = arr.length;
        int[][] fmap = new int[n][n];
        int[][] gmap = new int[n][n];
        for (int i = 0; i < n; i++) {
            fmap[i][i] = arr[i];
        }
        for (int startCol = 1; startCol < n; startCol++) {
            int L = 0;
            int R = startCol;
            while (R < n){
               fmap[L][R] = Math.max(arr[L] + gmap[L+1][R],arr[R] + gmap[L][R-1]);
               gmap[L][R] = Math.min(fmap[L+1][R],fmap[L][R-1]);
                L++;
                R++;
            }

        }
        return Math.max(fmap[0][n-1] , gmap[0][n-1]);
    }
    public static void main(String[] args) {
        int[] arr = {5,7,4,5,8,1,6,0,3,4,6,1,7};
        DynamicProgramming1 dp = new DynamicProgramming1();
        int process = dp.process(arr);
        int process1 = dp.process1(arr);
        int i = dp.process3(arr);
        System.out.println(process1);
        System.out.println(process);
        System.out.println(i);
    }
}
