package DynamicProgram;

import sun.awt.SunHints;

import java.util.HashMap;

/*
* 贴纸问题给定一个字符串target，给一个字符串数组arr，每个字符代表一张贴纸（数量随意）
* arr随意里面贴纸随意剪切，拼出来str等于arr最终至少需要多少张贴纸。
* */
public class DynamicProgramming4 {
    public int GetStr(String[] stickers, String target) {
        if (stickers.length == 0 || stickers == null || target == null) {
            return 0;
        }
        int n = stickers.length;
        int[][] counts = new int[n][26];
        for (int i = 0; i < n; i++) {
            char[] str = stickers[i].toCharArray();
            for (char c : str) {
                counts[i][c - 'a']++;
            }
        }
        int ans = process(counts, target);
        return ans;
    }
    /*
    * 参数1 stickers = counts[i][c - 'a']++;已经初始化好，二维表中，位置的数值都代表了字符出现了几次
    * 参数2 target，每次传入的话都是剩余的字符串，如果长度变成0说明前面已经算出来了
    * */
    private int process(int[][] stickers, String t) {
        if (t.length() == 0){
            return 0;
        }
        char[] target = t.toCharArray();
        int[] tcount = new int[26];
        for (char c : target) {
            tcount[c - 'a']++;
        }
        //获取一维数组贴纸的长度
        int n = stickers.length;
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {//获取每一个以为数组
            int[] sticker = stickers[i];//遍历一维数组赋值给遍历sticker
            if (sticker[target[0] - 'a']>0){//如果一维数组中的target[0] - 'a'的位置大于0说明当前位置有值，说明target命中sticker
                //命中之后说明两个字符串有共同值，次判断可以减少分支
                StringBuilder sb = new StringBuilder();
                //当目标符串中的值命中贴纸中的值，开始遍历他们其他位置是否有相同的值
                for (int j = 0; j < 26; j++) {
                    if (tcount[j] > 0){//
                        int nums = tcount[j] - sticker[j];//（剩余的nums，说明没有抵消掉，在target中没有此字符）
                        for (int k = 0; k < nums; k++) {
                            //把没有被抵消的字符拼接起来，传入process中继续执行
                            sb.append((char)(j+'a'));
                        }
                    }
                }
                String rest = sb.toString();
                min = Math.min(min,process(stickers,rest));
            }
        }
        return min + (min == Integer.MIN_VALUE?0:1);
    }
    public int GetStr1(String[] stickers, String target) {
        if (stickers.length == 0 || stickers == null || target == null) {
            return 0;
        }
        int n = stickers.length;
        int[][] counts = new int[n][26];
        for (int i = 0; i < n; i++) {
            char[] str = stickers[i].toCharArray();
            for (char c : str) {
                counts[i][c - 'a']++;
            }
        }
        HashMap<String,Integer> dp = new HashMap<>();
        dp.put("",0);
        int ans = process1(counts,target,dp);
        return ans == Integer.MAX_VALUE?-1:ans;
    }
    private int process1(int[][] stickers, String t, HashMap<String,Integer> dp) {
        if (dp.containsKey(t)){
            return dp.get(t);
        }
        char[] target = t.toCharArray();
        int[] tcount = new int[26];
        for (char c : target) {
            tcount[c - 'a']++;
        }
        //获取一维数组贴纸的长度
        int n = stickers.length;
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {//获取每一个以为数组
            int[] sticker = stickers[i];//遍历一维数组赋值给遍历sticker
            if (sticker[target[0] - 'a']>0){//如果一维数组中的target[0] - 'a'的位置大于0说明当前位置有值，说明target命中sticker
                //命中之后说明两个字符串有共同值，次判断可以减少分支
                StringBuilder sb = new StringBuilder();
                //当目标符串中的值命中贴纸中的值，开始遍历他们其他位置是否有相同的值
                for (int j = 0; j < 26; j++) {
                    if (tcount[j] > 0){//
                        int nums = tcount[j] - sticker[j];//（剩余的nums，说明没有抵消掉，在target中没有此字符）
                        for (int k = 0; k < nums; k++) {
                            //把没有被抵消的字符拼接起来，传入process中继续执行
                            sb.append((char)(j+'a'));
                        }
                    }
                }
                String rest = sb.toString();
                min = Math.min(min,process1(stickers,rest,dp));
            }
        }
        int ans = min + (min == Integer.MIN_VALUE?0:1);
        dp.put(t,ans);
        return ans;
    }
}































