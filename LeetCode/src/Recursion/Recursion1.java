package Recursion;
//汉诺塔最左边移动到最右边
public class Recursion1 {

    public  void Hanoi(int n){
        if (n > 0){
            process(n," left "," right "," mid ");
        }
    }
    //汉诺塔，从左移动到最右边
    //可变参数
    //首先“ 1 ”从左移动到右边，“ 2 ”从左移动到中间，“ 1 ”从右移动到中间 “ 3 ”从左移动到右
    //使用from to other的思想，可以变换参数。
    private  void process(int n, String from, String to, String other) {
        if (n == 1){
            System.out.println(" Move 1 from " + from + " to " + to);
        }else {
            process(n-1,from,other,to);
            System.out.println(" Move " + n + from + " to " + to);
            process(n-1,other,to,from);
        }
    }
    public static void main(String[] args) {
        Recursion1 r = new Recursion1();
        r.Hanoi(3);
    }
}

