package MonotonousStack;

import java.util.Stack;

public class GetSubArrMax {
    int GetSubArrMax(int[] arr){
        int size = arr.length;
        int[] sum = new int[size];
        sum[0] = arr[0];
        //此for循环计算出前戳和
        for (int i = 1; i < size; i++) {
            sum[i] = sum[i-1]+arr[i];
        }
        int max = Integer.MAX_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty()&&arr[stack.peek()]>=arr[i]){
                Integer pop = stack.pop();
                //sum[stack.peek()]此数值已经在栈中，说明在i的左边，用i-1的位置减去sum[stack.peek()]得到前戳数，
                // 乘以pop（pop为当前数组中最小值）
                max = Math.max(max,(stack.isEmpty()?sum[i-1]:(sum[i-1]-sum[stack.peek()]))*arr[pop]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            Integer pop = stack.pop();
            max = Math.max(max,(stack.isEmpty()?sum[size-1]:(sum[size-1]-sum[stack.peek()]))*arr[pop]);
        }
        return max;
    }
}
