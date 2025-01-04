package GreedyAlogorithm;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ForkJoinPool;

//贪心算法
//分金条，{10，20，30} 怎么分可以花最小铜板分给这三人
/*
* 思路：
* 先排序，装进堆中，弹出两个相加，弹出两个相机，堆剩一个的时候返回
* */
public class GreedyAlogorithm2 {
    //贪心算法
    public int lessMoney(int[] arr){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while (queue.size()>1){
            cur = queue.poll() + queue.poll();
            sum += cur;
            queue.add(cur);
        }
        return sum;
    }
    //暴力求解
    public int lessMoney1(int[] arr){
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        return process(arr,0);
    }
    public int process(int[] arr,int pre){
        if (arr.length == 1){
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {

                ans =  Math.min(ans,process(CopyAndMergeTwo(arr,i,j),pre+arr[i]+arr[j]));
            }
        }
        return ans;
    }

    private int[] CopyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length-1];
        int index = 0;
        for (int k = 0; k < arr.length; k++) {
           if (k!=i&&k!=j){
               ans[index++] = arr[k];
           }
        }
        ans[index] = arr[i] + arr[j];
        return ans;
    }

    public static void main(String[] args) {
        int[] a = {3,2,4};
        GreedyAlogorithm2 g = new GreedyAlogorithm2();
        int i = g.lessMoney1(a);
        System.out.println( g.lessMoney(a));
        System.out.println(i);
    }
}
