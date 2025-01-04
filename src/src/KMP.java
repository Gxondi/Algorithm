package src;

public class KMP {
    public int KMP(String str1,String str2){
        if (str1 == null||str2==null||str1.length()<1||str2.length()<2){
            return -1;
        }
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int x = 0;//记录字符串下标
        int y = 0;//记录子串下标
        int[] next = getNextArray(s2);//求得next数组
        //y超出长度代表完全匹配，返回x-y
        //如果x超出长度代表无法匹配返回-1
        while(x<s1.length && y<s2.length){
            if (s1[x] == s2[y]){
                x++;
                y++;
            }else if (next[y] == -1){
                x++;
            }else {
                y = next[y];//往前跳，next[y]记录的是前面数字下标
            }
        }
        return y == s2.length?x-y:-1;
    }

    private int[] getNextArray(char[] s2) {
        if (s2.length == 1){
            return new int[] {-1};
        }
        int[] next = new int[s2.length];
        next[0] = -1;
        next[1] = 1;
        int cn = 0;//跟i-1位置的比
        int i = 2;//目前在哪个位置上求next
        while (i < next.length){
            if (s2[i-1] == s2[cn]){
                next[i++] = ++cn;
            }else if(cn > 0){//没配成功cn往左跳
                cn = next[cn];
            }else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str = "asddsaqwef";
        String str2 = "dsa";
        KMP kmp = new KMP();
        int kmp1 = kmp.KMP(str, str2);
        System.out.println(kmp1);
        System.out.println("asdasd");
    }
}
