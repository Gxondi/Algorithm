package 第三天异或运算.例题.B把一个INT类型数字最后边1提取出来;

public class FindMostRightNum {
    public int FindMostRightNum(int num){
        return num & (~num + 1);// (~num + 1)== (-num)是补码
    }
    public static void main(String[] args) {
        int num = 10;
        FindMostRightNum f = new FindMostRightNum();
        System.out.println(f.FindMostRightNum(num));
    }
}
