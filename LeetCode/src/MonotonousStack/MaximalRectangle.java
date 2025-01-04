package MonotonousStack;

import java.sql.SQLClientInfoException;
import java.util.Stack;
//给定一个二维数组，不是0就是1，找到最大由1组成的最大子矩形，里面有多少个1
public class MaximalRectangle {
    public int MaximalRectangle(int[][] map){
        if (map.length == 0 || map[0].length == 0||map == null){
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == '0'?0:height[j]+1;
            }
            maxArea = Math.max(maxArea,GetLargestRectangle(height));
        }
        return maxArea;
    }
    public int GetLargestRectangle(int[] height){
        if (height.length == 0||height == null){
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty()&&height[stack.peek()]>=height[i]){
                int j = stack.pop();
                int k = stack.isEmpty()?-1:stack.peek();
                int curArea = (i-k-1)*height[i];
                maxArea = Math.max(maxArea,curArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int j = stack.pop();
            int k = stack.isEmpty()?-1:stack.peek();
            int curArea = (height.length-k-1)*height[j];
            maxArea = Math.max(maxArea,curArea);
        }
        return maxArea;
    }
}
