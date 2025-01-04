package N皇后问题练习;

//8*8 N皇后练习
public class NQueenSolver {
    int N = 4; //表格长宽为4
    boolean[] row = new boolean[N + 1];//列
    boolean[] rup = new boolean[2 * N + 1];//右斜线
    boolean[] lup = new boolean[2 * N];//左斜线
    int[] line = new int[N + 1];//存当i ， j

    public void solve(int i) {
        //从第一列到最后一列开始尝试
        for (int j = 1; j <= N; j++) {
            //第一个if应该判断当前列，和左右斜线，是否可以放皇后,false代表可以放，true代表有皇后了
            //rup[i + j] 在一个 8x8 的棋盘上，位置 (2, 3) 和 (4, 5) 都在同一条右上对角线上，因为 2+3 = 5 和 4+5 = 9。
            //lup[j - i + N] 在一个 8x8 的棋盘上，位置 (3, 4) 和 (5, 6) 都在同一条左上对角线上，因为 4-3 = 1 和 6-5 = 1。
            if (!row[j] && !rup[i + j] && !lup[j - i + N]) {
                line[i] = j;//!false=true 放皇后
                //放完判断是否到N，表示全部循环结束
                if (i == N){
                    for (int k = 1; k <= N; k++) {
                        System.out.print(line[k] + " ");
                    }
                    System.out.println(" ");
                }else {
                    //没结束，把j列以及左右斜线设置true，代表放了皇后
                    row[j] = rup[i + j] = lup[j - i + N] = true;
                    //进入下一行
                    solve(i + 1);
                    //回溯，递归跳出来执行，递归为什么会跳出？因为当前列为true，放了皇后。并取消当前行j列的值，变成false，试下一列。
                    row[j] = rup[i + j] = lup[j - i + N] = false;
                }

            }
        }
    }

    public static void main(String[] args) {
        NQueenSolver nQueenSolver = new NQueenSolver();
        nQueenSolver.solve(1);//从第一行开始
    }
}
