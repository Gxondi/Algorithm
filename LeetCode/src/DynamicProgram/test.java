package DynamicProgram;

public class test {
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
        test dp = new test();

        System.out.println(dp.process3(arr));
    }
}
