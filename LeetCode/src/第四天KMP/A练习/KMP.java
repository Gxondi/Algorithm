package 第四天KMP.A练习;

import java.util.ArrayList;

public class KMP {
    public static void main(String[] args) {
        String str = "dddddsassssssssssssssssssxxdddaaaadddddsaasssdsa";
        String str2 = "dddddsa";
        KMP kmp = new KMP();

        long startTime = System.nanoTime();
        // 执行您要测试的方法
        int kmp1 = kmp.KMP(str, str2);
        System.out.println(kmp1);
        long endTime = System.nanoTime();
        double executionTime = endTime - startTime;
        System.out.println("方法执行时间：" + executionTime / 1000000 + "毫秒");


    }

    public int KMP(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < 1 || str2.length() < 1) {
            return -1;
        }
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int[] nextArray = getNextArray(s2);
        int p1 = 0;
        int p2 = 0;
        while (p1 < s1.length && p2 < s2.length) {
            if (s1[p1] == s2[p2]) { //相等++
                p1++;
                p2++;
            }else if(nextArray[p2] == -1){ // 子串遍历结束，从下一个接着遍历
                p1++;
            }
            else {
                p2 = nextArray[p2];
            }
        }
        return p2 == s2.length ? p1 - p2 : -1;
    }

    private int[] getNextArray(char[] s2) {
        if (s2.length == 1) {
            return new int[] { -1 };
        }
        int len = s2.length;
        int[] next = new int[len];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while(i < len){
            if (s2[i-1] == s2[cn]){
                next[i++] = ++cn;
            }else if(cn > 0){//cn和i-1位置没配成功，往前跳
                cn = next[cn];
            }else {
                next[i++] = 0;
            }
        }
        return next;

    }


}
