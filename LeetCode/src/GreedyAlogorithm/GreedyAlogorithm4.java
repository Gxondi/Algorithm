package GreedyAlogorithm;

/*
 * 给定一个字符串 只有x 和 .
 * x表示墙，.表示马路
 * .需要放灯，i位置放灯i-1和i+2都被点亮
 * */
public class GreedyAlogorithm4 {
    public int minLight(String strs) {
        if (strs == null) {
            return 0;
        }
        char[] s = strs.toCharArray();
        int i = 0;
        int light = 0;
        while (i < s.length) {
            if (s[i] == 'X') {
                return light;
            } else {
                light++;
                if (i + 1 == s.length) {
                    break;
                } else {
                    if (s[i + 1] == 'X') {
                        i = i + 2;
                    } else {
                        i = i + 3;
                    }
                }
            }
        }
        return light;
    }
}
