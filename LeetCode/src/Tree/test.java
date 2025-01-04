package Tree;

import java.util.TreeSet;

public class test {
    public static String lowestString1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        TreeSet<String> ans = process(strs);
        return ans.size() == 0 ? "" : ans.first();
    }

    // strs中所有字符串全排列，返回所有可能的结果
    public static TreeSet<String> process(String[] strs) {
        TreeSet<String> ans = new TreeSet<>();
        if (strs.length == 0) {
            ans.add("");
            return ans;
        }
        for (int i = 0; i < strs.length; i++) {
            String first = strs[i];
            String[] nexts = removeIndexString(strs, i);
            TreeSet<String> next = process(nexts);
            for (String cur : next) {
                ans.add(first + cur);
            }
        }
        return ans;
    }

    // {"abc", "cks", "bct"}
    // 0 1 2
    // removeIndexString(arr , 1) -> {"abc", "bct"}
    public static String[] removeIndexString(String[] arr, int index) {
        int N = arr.length;
        String[] ans = new String[N - 1];
        int ansIndex = 0;
        for (int i = 0; i < N; i++) {
            if (i != index) {
                ans[ansIndex++] = arr[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] a = {"a","b","c"};
        test t = new test();
        String s = t.lowestString1(a);
        System.out.println( s);;
    }
}
