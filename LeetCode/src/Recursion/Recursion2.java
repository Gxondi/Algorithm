package Recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//打印字符串全部子序列
public class Recursion2 {
    public List<String> PrintSubStr(String str){
        char[] chars = str.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        process(chars,0,set,path);
        for (String s : set) {
            ans.add(s);
        }
        return ans;
    }

    private void process(char[] chars, int index,   HashSet<String> set,String path) {
        if (index == chars.length){
            set.add(path);
            return;
        }
        String no = path;
        process(chars,index+1,set,no);
        String yes = path+String.valueOf(chars[index]);
        process(chars,index+1,set,yes);
    }

    public static void main(String[] args) {
        String a = "abc";
        String b = "accc";
        Recursion2 r2 = new Recursion2();

        System.out.print( r2.PrintSubStr(a));
        System.out.print( r2.PrintSubStr(b));
    }
}
