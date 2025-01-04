package DynamicProgram;

import sun.applet.Main;

import javax.lang.model.element.VariableElement;
import java.time.chrono.MinguoChronology;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
	// str1[0...i]和str2[0...j]，这个范围上最长公共子序列长度是多少？
	// 可能性分类:
	// a) 最长公共子序列，一定不以str1[i]字符结尾、也一定不以str2[j]字符结尾
	// b) 最长公共子序列，可能以str1[i]字符结尾、但是一定不以str2[j]字符结尾
	// c) 最长公共子序列，一定不以str1[i]字符结尾、但是可能以str2[j]字符结尾
	// d) 最长公共子序列，必须以str1[i]字符结尾、也必须以str2[j]字符结尾
	// 注意：a)、b)、c)、d)并不是完全互斥的，他们可能会有重叠的情况
	// 但是可以肯定，答案不会超过这四种可能性的范围
	// 那么我们分别来看一下，这几种可能性怎么调用后续的递归。
	// a) 最长公共子序列，一定不以str1[i]字符结尾、也一定不以str2[j]字符结尾
	//    如果是这种情况，那么有没有str1[i]和str2[j]就根本不重要了，因为这两个字符一定没用啊
	//    所以砍掉这两个字符，最长公共子序列 = str1[0...i-1]与str2[0...j-1]的最长公共子序列长度(后续递归)
	// b) 最长公共子序列，可能以str1[i]字符结尾、但是一定不以str2[j]字符结尾
	//    如果是这种情况，那么我们可以确定str2[j]一定没有用，要砍掉；但是str1[i]可能有用，所以要保留
	//    所以，最长公共子序列 = str1[0...i]与str2[0...j-1]的最长公共子序列长度(后续递归)
	// c) 最长公共子序列，一定不以str1[i]字符结尾、但是可能以str2[j]字符结尾
	//    跟上面分析过程类似，最长公共子序列 = str1[0...i-1]与str2[0...j]的最长公共子序列长度(后续递归)
	// d) 最长公共子序列，必须以str1[i]字符结尾、也必须以str2[j]字符结尾
	//    同时可以看到，可能性d)存在的条件，一定是在str1[i] == str2[j]的情况下，才成立的
    //    所以，最长公共子序列总长度 = str1[0...i-1]与str2[0...j-1]的最长公共子序列长度(后续递归) + 1(共同的结尾)
	// 综上，四种情况已经穷尽了所有可能性。四种情况中取最大即可
	// 其中b)、c)一定参与最大值的比较，
	// 当str1[i] == str2[j]时，a)一定比d)小，所以d)参与
	// 当str1[i] != str2[j]时，d)压根不存在，所以a)参与
	// 但是再次注意了！
	// a)是：str1[0...i-1]与str2[0...j-1]的最长公共子序列长度
	// b)是：str1[0...i]与str2[0...j-1]的最长公共子序列长度
	// c)是：str1[0...i-1]与str2[0...j]的最长公共子序列长度
	// a)中str1的范围 < b)中str1的范围，a)中str2的范围 == b)中str2的范围
	// 所以a)不用求也知道，它比不过b)啊，因为有一个样本的范围比b)小啊！
	// a)中str1的范围 == c)中str1的范围，a)中str2的范围 < c)中str2的范围
	// 所以a)不用求也知道，它比不过c)啊，因为有一个样本的范围比c)小啊！
	// 至此，可以知道，a)就是个垃圾，有它没它，都不影响最大值的决策
	// 所以，当str1[i] == str2[j]时，b)、c)、d)中选出最大值
	// 当str1[i] != str2[j]时，b)、c)中选出最大值
* */
public class DynamicProgramming5 {
    public int GetSubStr(String str1,String str2){
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        return process(chars1,chars2,chars1.length-1,chars2.length-1);
    }
    private int process(char[] str1, char[] str2, int i, int j) {
        // str1[0..0]和str2[0..0]，都只剩一个字符了
        // 那如果字符相等，公共子序列长度就是1，不相等就是0
        // 这显而易见
        if (i==0&&j==0){
            return str1[i] == str2[j]?1:0;
        } else if (i == 0){
            // 这里的情况为：
            // str1[0...0]和str2[0...j]，str1只剩1个字符了，但是str2不只一个字符
            // 因为str1只剩一个字符了，所以str1[0...0]和str2[0...j]公共子序列最多长度为1
            // 如果str1[0] == str2[j]，那么此时相等已经找到了！公共子序列长度就是1，也不可能更大了
            // 如果str1[0] != str2[j]，只是此时不相等而已，
            // 那么str2[0...j-1]上有没有字符等于str1[0]呢？不知道，所以递归继续找
            if (str2[j] == str1[i]){
                return 1;
            }else {
                return process(str1,str2,i,j-1);
            }
        }else if (j == 0){
            if (str2[j] == str1[i]){
                return 1;
            }else {
                return process(str1,str2,i-1,j);
            }
        }else {
            int p1 = process(str1,str2,i,j-1);
            int p2 = process(str1,str2,i-1,j);
            int p3 = str1[i] == str2[j] ? (1+process(str1,str2,i-1,j-1)):0;
            return Math.max(p1,Math.max(p2,p3));
        }
    }
    public int GetSubStrDp(String s1,String s2){
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length; // 行
        int M = str2.length; // 列
        int dp[][] = new int[N][M];
        dp[0][0] = str1[0] == str2[0]?1:0;
        for (int j = 1; j < M; j++) {
            dp[0][j] = str1[0] == str2[j]?1:dp[0][j-1];
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = str1[i] == str2[0]?1:dp[i-1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[N-1][M-1];
    }

    public static void main(String[] args) {
        String str1 = "4512qw";
        String str2 = "4512ab";
        DynamicProgramming5 dp = new DynamicProgramming5();
        int i = dp.GetSubStr(str1, str2);
        System.out.println(i);
        int i1 = dp.GetSubStrDp(str1, str2);
        System.out.println(i1);
    }
}
