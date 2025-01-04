package 第四天KMP.A练习;

public class BM {
    public static void main(String[] args) {
        BM bm = new BM();
        String s = "HERE IS A SIMPLE EXAMPLE";
        String s2 = "EXAMPLX";
        long startTime = System.nanoTime();
        int i = bm.BM(s, s2);
        System.out.println(i);
        long endTime = System.nanoTime();
        double executionTime = endTime - startTime;
        System.out.println("方法执行时间：" + executionTime / 1000000 + "毫秒");
    }

    public int BM(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < 1 || s2.length() < 1) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int s = s1.length();
        int p = s2.length();

        int[] BW = getBadWord(str2);//传进去字符串数组,构建坏字符
        int[] GS = getGoodSuffix(str2);

        for (int i = p - 1, j=0; i < s; ) {//主串的循环
            for (j = p - 1; str1[i] == str2[j]; i--, j--) {//模式串的循环，比对
                //指向模式串的首字符，说明匹配成功，直接返回就可以了
                if (j == 0) {
                    return i;
                }
            }
            //如果出现坏字符，那么这个时候比较坏字符以及好后缀的数组，哪个大用哪个
            int max = Math.max(BW[str1[i]], GS[p - 1 - j]);
            i += max;
        }
        return -1;
    }

    /**
     * 坏字符
     *
     * @param str2
     * 构建起来很简单原理如下：
     * 首先填充好表格，每一个表格内都是字符串大小。因为如果没有匹配的字符，那么就是直接跳过整个字符串
     * 其次假设字符串是"THAT"，那么这四个字母都可能是坏字符，所以每一个字符都要计算跳转距离
     * 坏字符跳转距离如何计算呢？
     * 当前坏字符在模式串中当前的位置 - 这个坏字符之前出现过的位置
     * 如果该字符串之前没有出现过那么就是跳过整个字符串
     * 坏字符表构建出来的就是要移动的距离也就是相对距离
     * @return
     */
    private int[] getBadWord(char[] str2) {
        //构建坏字符串数组
        final int table_size = 256;
        int[] bad_table = new int[table_size];
        int plen = str2.length;
        for (int i = 0; i < bad_table.length; i++) {
            bad_table[i] = plen;//初始化为模式串的长度
        }
        for (int i = 0; i < plen - 1; i++) {
            //数组str[i] = "T H A T"
            int k = str2[i];//k是什么，k是模式串中，字符的ascii码 a = 97
            //找到bad_table中的a
            //找到table中的了，原本a位置的数值是什么？是上面初始化的值模式串的长度 plen
            //既然找到了，就要计算位移多少。
            //bad_table第97个位置的值是多少？plen-1-i
            bad_table[k] = plen - 1 - i;//如何计算的？plen-1是尾部减去循环到那个位置的i
        }
        return bad_table;
    }

    /**
     * 好后缀
     *
     * @param str2
     * 好后辍的构建思路：
     * lastPrefixLength = len; 没有匹配的时候，直接跳过整个字符串
     * 两种分析：
     *  判断前戳匹配：
     *      双指针，最开始一个右指针指向模式串的最后一个字符，左指针指向0，然后左指针递增，右指针递增如果匹配，那么记录当前位置
     *      rightPoint外层循环值越来越小
     *      for (int i = rightPoint, j = leftPoint; i < length; ++i, ++j)
     *  判断后辍匹配：
     *      双指针，最开始一个右指针指向模式串的最后一个字符，左指针指向0，然后左指针递减，右指针递减如果匹配，那么记录当前位置
     *      leftPoint外层循环值越来越大
     *      for (int i = leftPoint, j =rightPoint; i >= 0 && str2[i] == str2[j]; i--, j--)
     * @return
     */
    private int[] getGoodSuffix(char[] str2) {
        //goodTable 存的是当前字符要跳多少步
        int[] goodTable = new int[str2.length];

        int len = str2.length;
        //初始值为模式字符串的长度 len，表示初始状态下整个字符串可以被认为是前缀匹配的。
        //bcabab
        int lastPrefixLength = len;
        //构建好后辍表格的两种分析
        //前戳下标0不动，后辍动，由小到大递增
        for (int i = len - 1; i >= 0; --i) { // 判断边界：0-5 六个数字，长度是6，
            if (isPrefix(str2, i + 1)) {     //从下标六开始判断
                //如果当前的位置存在前缀匹配，那么记录当前位置
                lastPrefixLength = i + 1;
            }
            //要移动多少？
            //求相对位置，len-1-i是该字符串在模式串中当前位置，lastPrefixLength是前缀长度
            goodTable[len - 1 - i] = lastPrefixLength + len - 1 - i;
        }
        //bcabab
        //后辍下标len不动，前戳动，由大到小递减
        for (int i = 0; i < len - 1; i++) {
            int slen = suffix(str2, i);
            goodTable[slen] = slen + len - 1 - i;
        }
        return goodTable;
    }

    //前戳下标0不动，后辍动由小到大递增
    //第一种->     -> 方向
    //abcdefa     abcdefa     abcdafa
    //↑     ↑     ↑    ↑      ↑   ↑
    //j = 0，i =  p
    private boolean isPrefix(char[] str2, int p) {
        int length = str2.length;
        for (int i = p, j = 0; i < length; ++i, ++j) {
            if (str2[i] != str2[j]) {
                return false;
            }
        }
        return true;
    }

    //后辍下标len不动，前戳动，由大到小递减
    //第二种<-     <- 方向
    //abcdefg     abcdefg     abcdefg
    //↑     ↑      ↑    ↑       ↑   ↑
    //i =  p（0开始） , j =（length递减）
    private int suffix(char[] str2, int p) {
        int length = str2.length;
        int len = 0;
        for (int i = p, j = length - 1; i >= 0 && str2[i] == str2[j]; i--, j--) {
            len += 1;
        }
        return len;
    }


}
