package Recursion;

import Tree.IsFullAndCBTree;

import java.util.Stack;

//逆序栈，不申请额外数据结构
public class Recursion4 {
    //弹出栈顶，不为空继续，此功能是弹出栈底值
    public void reverse(Stack<Integer> stack){
        if (stack == null){
            return;
        }else {
            int i = process(stack);
            reverse(stack);
            stack.push(i);
        }
    }
    public Integer process(Stack<Integer> stack){
        Integer result = stack.pop();
        if (stack.isEmpty()){
            return result;
        }else {
            //last = process的返回值（result）
            int last = process(stack);
            stack.push(result);
            return last;
        }
    }
}
