package 第四天KMP.A练习;

public class text {
    public static void main(String[] args) {
        int[] ints = build_good_table("abcdab.");
        for (int anInt : ints) {
            System.out.print(anInt+" ");
        }
    }
    public static int[] build_good_table(String pattern) {
        int pLen = pattern.length();//模式串长度
        int[] good_table = new int[pLen];//创建一个数组，存好后缀数值
        //用于记录最新前缀的相对位置，初始化为模式串长度，因为意思就是当前后缀字符串为空
        //要明白lastPrefixPosition 的含义
        int lastPrefixPosition = pLen;
        //前缀
        for (int i = pLen - 1; i >= 0; --i) {
            if (isPrefix(pattern, i + 1)) {
                //如果当前的位置存在前缀匹配，那么记录当前位置
                lastPrefixPosition = i + 1;
            }
            good_table[pLen - 1 - i] = lastPrefixPosition - i + pLen - 1;
        }
        //后缀
        for (int i = 0; i < pLen - 1; ++i) { //小于6
            //计算出指定位置匹配的后缀的字符串长度
            int slen = suffixLength(pattern, i);
            good_table[slen] = pLen - 1 - i + slen;
        }
        return good_table;
    }

    //前缀匹配
    private static boolean isPrefix(String pattern, int p) {
        int patternLength = pattern.length();//模式串长度
        //这里j从模式串第一个字符开始，i从指定的字符位置开始，通过循环判断当前指定的位置p
        //之后的字符串是否匹配模式串前缀
        for (int i = p, j = 0; i < patternLength; ++i, ++j) {
            if (pattern.charAt(i) != pattern.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    //后缀匹配
    private static int suffixLength(String pattern, int p) { //p0-5
        int pLen = pattern.length();
        int len = 0;
        for (int i = p, j = pLen - 1; i >= 0 && pattern.charAt(i) == pattern.charAt(j); i--, j--) {
            len += 1;
        }
        return len;
    }
}
