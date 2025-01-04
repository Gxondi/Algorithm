package MonotonousStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//单调栈，在数组中拿到当前数组左边，右边最靠近自己的的最小值
public class GetNearLessRepeat {
    //方法返回一个二维数组,此方法只能解决无重复值，如果有重复值，Integer换成list链表来处理
    int[][] GetNearLessRepeat(int[] arr){
        int[][] res = new int[arr.length][2];
        //单调栈存储下标
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty()&&arr[stack.peek()]>arr[i]){
                Integer popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty()?-1:stack.peek();
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            Integer popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty()?-1:stack.peek();
            res[popIndex][0] = leftLessIndex;
            res[popIndex][1] = -1;
        }
        return res;
    }
    int[][] GetNearLessRepeat_list(int[] arr){
        int[][] res = new int[arr.length][2];
        //单调栈存储下标
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty()&&arr[stack.peek().get(0)]>arr[i]){
                List<Integer> popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty()?-1:stack.peek().get(stack.peek().size() - 1);
                for (Integer popi : popIndex) {
                    res[popi][0] = leftLessIndex;
                    res[popi][1] = i;
                }
            }
            if (!stack.isEmpty()&&arr[stack.peek().get(0)]==arr[i]){
               stack.peek().add(Integer.valueOf(i));
            }else {
                ArrayList<Integer> arrayList = new ArrayList<>();
                arrayList.add(i);
                stack.push(arrayList);
            }
        }
        while (!stack.isEmpty()){
            List<Integer> popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty()?-1:stack.peek().get(stack.peek().size() - 1);
            for (Integer popi : popIndex) {
                res[popi][0] = leftLessIndex;
                res[popi][1] = -1;
            }
        }
        return res;
    }

}
