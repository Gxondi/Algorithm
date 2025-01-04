package Manacher;
//求最长回文子序列
public class Manacher {
    public int LargestPalindromeStr(String str){
        if (str == null || str.length() == 0){
            return 0;
        }
        char[] chars = Manacherchars(str);
        int[] pArr = new int[chars.length];
        //R和C是组合，c代表r拓展出去后，此时的点，两个变量绑定一块变动
        int R = -1;
        int C = -1;
        int max = Integer.MIN_VALUE;
        //C 是0-R的中心点，i一定再C的左边
        //为什么呢？在C点的时候拓展出去很多，然后i继续从c往右开始加加，i'一定在c左边，所以i’被求证过，对应i
        //选择小的进入parr数组中
        //总结，大的回文数组以c中心点，对应相等，那么i与i‘必然相等
        for (int i = 0; i < chars.length; i++) {
            pArr[i] = R>i?Math.min(pArr[2*C-i],R-i):1;
            while (i+pArr[i]<chars.length&&i-pArr[i]>-1){
                if (chars[i + pArr[i]]<chars.length&&chars[i-pArr[i]]>-1){
                    pArr[i]++;
                }else {
                    break;
                }
            }
            if (i+pArr[i]>R){
                R = i+pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    private char[] Manacherchars(String str) {
        char[] chars = str.toCharArray();
        char[] res = new char[str.length()*2+1];
        for (int i = 0; i !=str.length(); i++) {
            res[i] = (i&1) == 0?'#':chars[i++];
        }
        return res;
    }
}
