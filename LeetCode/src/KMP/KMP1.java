package KMP;

public class KMP1 {
    public int KPM(String str1, String str2){
        if (str1 == null||str2 == null|| str1.length()<1||str2.length()<1){
            return -1;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();// 要查找的目标字符串
        int[] next = GetNearNext(chars2);
        int x = 0;
        int y = 0;
        for (int i = 0; i < chars1.length; i++) {
            while (x<str1.length()&&y<str2.length()){
                if (chars1[x]==chars2[y]){
                    x++;
                    y++;
                }else if (next[y] == -1){
                    x++;
                }else {
                    y = next[y];
                }
            }
        }
        return y == str2.length()?x-y:-1;
    }

    private int[] GetNearNext(char[] chars2) {
        if (chars2.length == 1){
            return new int[]{-1};
        }
        int[] next = new int[chars2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;//目前在哪个位置求next数组
        int cn = 0;//现在是哪个字符跟i-1比
        while (i < chars2.length){
            if (chars2[i-1] == chars2[cn]){
                next[i++] = ++cn;
            }else if (cn>0){
                cn = next[cn];
            }else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
