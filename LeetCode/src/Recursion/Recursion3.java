package Recursion;

import Tree.Tree;
import com.sun.org.apache.bcel.internal.generic.SWAP;

import java.util.ArrayList;
import java.util.List;

//打印一个字符串全排列
public class Recursion3 {
    public List<String> PrintStr(String str){
        char[] chars = str.toCharArray();
        ArrayList<Character> rest = new ArrayList<>();
        for (char aChar : chars) {
            rest.add(aChar);
        }
        List<String> ans = new ArrayList<>();
        String path = "";
        //process(path,rest,ans);
        process2(chars,0,ans);
        return ans;
    }

    private void process(String path, ArrayList<Character> rest,List<String> ans) {
        if (rest.isEmpty()){
            ans.add(path);
        }else {
            int n = rest.size();
            boolean[] visited = new boolean[256];
            for (int i = 0; i < n; i++) {
                Character cur = rest.get(i);
                rest.remove(cur);
                process(path+cur,rest,ans);
                rest.add(i,cur);
            }
        }
    }
    private void process2(char[] chars , int index , List<String> ans) {
       if (index == chars.length){
           ans.add(String.valueOf(chars));
       }else {
           boolean[] visited = new boolean[256];
           for (int i = index; i < chars.length ; i++) {
               if (!visited[chars[i]]){
                   visited[chars[i]] = true;
                   swap(chars,index,i);
                   process2(chars,index+1,ans);
                   swap(chars,index,i);
               }

           }
       }
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        String a = "abc";
        String b = "abb";
        Recursion3 r3 = new Recursion3();
        System.out.println( r3.PrintStr(a));
        System.out.println( r3.PrintStr(b));
    }
}
