package MonotonousStack;

import java.util.Stack;

public class GetLargestRectangle {
    int GetLargestRectangle(int[] arr){
        int N = arr.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty()&&arr[stack.peek()]>=arr[i]){
                int j = stack.pop();
                int k = stack.isEmpty()?-1:stack.peek();
                int curArea = (i-k-1)*arr[i];
                maxArea = Math.max(curArea,maxArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int j = stack.pop();//下标当前数值
            int k = stack.isEmpty()?-1:stack.peek();//当前数值左边的数值
            int curArea = (arr.length-k-1)*arr[j];
            maxArea = Math.max(curArea,maxArea);
        }
        return maxArea;
    }
}
